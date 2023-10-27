package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Person;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Luka
 */
public class SqlActorRepo implements Repository<Person>{

    private static final String ID_ACTOR = "IdActor";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";


    private static final String CREATE_ACTOR = "{ CALL CreateActor (?,?,?) }";
    private static final String UPDATE_ACTOR = "{ CALL UpdateActor (?,?,?) }";
    private static final String DELETE_ACTOR = "{ CALL DeleteActor (?) }";
    private static final String SELECT_ACTOR = "{ CALL SelectActor (?) }";
    private static final String SELECT_ACTORS = "{ CALL SelectActors }";
    
    
    @Override
    public int createOne(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ACTOR);)
        {
            stmt.setString(NAME, person.getName());
            stmt.setString(SURNAME, person.getSurname());
            stmt.registerOutParameter(ID_ACTOR, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(ID_ACTOR);
        }
    }

    @Override
    public void createMany(List<Person> persons) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_ACTOR);)
        {
            for (Person person : persons) {
                stmt.setString(NAME, person.getName());
                stmt.setString(SURNAME, person.getSurname());
                stmt.registerOutParameter(ID_ACTOR, Types.INTEGER);

                stmt.executeUpdate();
            }

        }
    }

    @Override
    public void updateOne(int id, Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_ACTOR);)
        {
            stmt.setInt(ID_ACTOR, id);
            stmt.setString(NAME, person.getName());
            stmt.setString(SURNAME, person.getSurname());
            
            stmt.executeUpdate();
        }        
    }

    @Override
    public void deleteOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_ACTOR);) {

            stmt.setInt(ID_ACTOR, id);

            stmt.executeUpdate();
        }}

    @Override
    public Optional<Person> selectOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_ACTOR);) {

            stmt.setInt(ID_ACTOR, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(
                            new Person(
                                    rs.getInt(ID_ACTOR),
                                    rs.getString(NAME),
                                    rs.getString(SURNAME)
                            )
                    );
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public List<Person> selectMany() throws Exception {
        List<Person> persons = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(SELECT_ACTORS); 
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                persons.add(
                        new Person(
                                    rs.getInt(ID_ACTOR),
                                    rs.getString(NAME),
                                    rs.getString(SURNAME)
                        )
                );
            }
        }

        return persons;    
    }
    
}
