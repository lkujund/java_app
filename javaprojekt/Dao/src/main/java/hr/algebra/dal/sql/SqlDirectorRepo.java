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
public class SqlDirectorRepo implements Repository<Person>{
    
    private static final String ID_DIRECTOR = "IdDirector";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";


    private static final String CREATE_DIRECTOR = "{ CALL CreateDirector (?,?,?) }";
    private static final String UPDATE_DIRECTOR = "{ CALL UpdateDirector (?,?,?) }";
    private static final String DELETE_DIRECTOR = "{ CALL DeleteDirector (?) }";
    private static final String SELECT_DIRECTOR = "{ CALL SelectDirector (?) }";
    private static final String SELECT_DIRECTORS = "{ CALL SelectDirectors }";
    
    
    @Override
    public int createOne(Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR);)
        {
            stmt.setString(NAME, person.getName());
            stmt.setString(SURNAME, person.getSurname());
            stmt.registerOutParameter(ID_DIRECTOR, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(ID_DIRECTOR);
        }
    }

    @Override
    public void createMany(List<Person> persons) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_DIRECTOR);)
        {
            for (Person person : persons) {
                stmt.setString(NAME, person.getName());
                stmt.setString(SURNAME, person.getSurname());
                stmt.registerOutParameter(ID_DIRECTOR, Types.INTEGER);

                stmt.executeUpdate();
            }

        }
    }

    @Override
    public void updateOne(int id, Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_DIRECTOR);)
        {
            stmt.setInt(ID_DIRECTOR, id);
            stmt.setString(NAME, person.getName());
            stmt.setString(SURNAME, person.getSurname());

            
            stmt.executeUpdate();
        }        
    }

    @Override
    public void deleteOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_DIRECTOR);) {

            stmt.setInt(ID_DIRECTOR, id);

            stmt.executeUpdate();
        }}

    @Override
    public Optional<Person> selectOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_DIRECTOR);) {

            stmt.setInt(ID_DIRECTOR, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(
                            new Person(
                                    rs.getInt(ID_DIRECTOR),
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
                CallableStatement stmt = con.prepareCall(SELECT_DIRECTORS); 
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                persons.add(
                        new Person(
                                    rs.getInt(ID_DIRECTOR),
                                    rs.getString(NAME),
                                    rs.getString(SURNAME)
                        )
                );
            }
        }

        return persons;    
    }
    
}
