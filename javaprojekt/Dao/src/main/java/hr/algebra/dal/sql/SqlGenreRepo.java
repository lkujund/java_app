package hr.algebra.dal.sql;

import hr.algebra.dal.Repository;
import hr.algebra.model.Genre;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

/**
 *
 * @author Luka
 */
public class SqlGenreRepo implements Repository<Genre>{

    private static final String ID_GENRE = "IdGenre";
    private static final String NAME = "Name";


    private static final String CREATE_GENRE = "{ CALL CreateGenre (?,?) }";
    private static final String UPDATE_GENRE = "{ CALL UpdateGenre (?,?) }";
    private static final String DELETE_GENRE = "{ CALL DeleteGenre (?) }";
    private static final String SELECT_GENRE = "{ CALL SelectGenre (?) }";
    private static final String SELECT_GENRES = "{ CALL SelectGenres }";
    
    
    @Override
    public int createOne(Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_GENRE);)
        {
            stmt.setString(NAME, genre.getName());
            stmt.registerOutParameter(ID_GENRE, Types.INTEGER);
            
            try {
                stmt.executeUpdate();
            } catch (SQLException sQLException) {
                return -1;
            }
            return stmt.getInt(ID_GENRE);
        }
    }

    @Override
    public void createMany(List<Genre> genres) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_GENRE);)
        {
            for (Genre genre : genres) {
                stmt.setString(NAME, genre.getName());
                stmt.registerOutParameter(ID_GENRE, Types.INTEGER);

                stmt.executeUpdate();
            }

        }
    }

    @Override
    public void updateOne(int id, Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_GENRE);)
        {
            stmt.setInt(ID_GENRE, id);
            stmt.setString(NAME, genre.getName());
            
            
            stmt.executeUpdate();
        }        
    }

    @Override
    public void deleteOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_GENRE);) {

            stmt.setInt(ID_GENRE, id);

            stmt.executeUpdate();
        }}

    @Override
    public Optional<Genre> selectOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_GENRE);) {

            stmt.setInt(ID_GENRE, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(
                            new Genre(
                                    rs.getInt(ID_GENRE),
                                    rs.getString(NAME)
                            )
                    );
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public List<Genre> selectMany() throws Exception {
        List<Genre> genres = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(SELECT_GENRES); 
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                genres.add(
                        new Genre(
                                    rs.getInt(ID_GENRE),
                                    rs.getString(NAME)
                        )
                );
            }
        }

        return genres;    
    }
        
}
