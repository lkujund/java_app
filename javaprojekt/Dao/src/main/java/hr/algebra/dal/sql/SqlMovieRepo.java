package hr.algebra.dal.sql;

import hr.algebra.dal.MovieObjectBinder;
import hr.algebra.dal.Repository;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.sql.DataSource;

/**
 *
 * @author Luka
 */
public class SqlMovieRepo implements Repository<Movie>, MovieObjectBinder{


    private static final String ID_MOVIE = "IdMovie";
    private static final String TITLE = "Title";
    private static final String DESCRIPTION = "Description";
    private static final String ORIGINAL_TITLE = "OriginalTitle";
    private static final String DURATION_MINUTES = "DurationMinutes";
    private static final String YEAR = "Year";
    private static final String IMAGE_URL = "ImageUrl";
    
    private static final String ID_ACTOR = "IdActor";
    private static final String ID_DIRECTOR = "IdDirector";
    private static final String ID_GENRE = "IdGenre";
    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";


    private static final String CLEAR_DATA = "{ CALL ClearData () }";
    
    private static final String CREATE_MOVIE = "{ CALL CreateMovie (?,?,?,?,?,?,?) }";
    private static final String UPDATE_MOVIE = "{ CALL UpdateMovie (?,?,?,?,?,?,?) }";
    private static final String DELETE_MOVIE = "{ CALL DeleteMovie (?) }";
    private static final String SELECT_MOVIE = "{ CALL SelectMovie (?) }";
    private static final String SELECT_MOVIES = "{ CALL SelectMovies }";
    private static final String GET_ACTORS = "{ CALL GetActorsForMovie (?) }";
    private static final String GET_DIRECTORS = "{ CALL GetDirectorsForMovie(?) }";
    private static final String GET_GENRES = "{ CALL GetGenresForMovie (?) }";
    private static final String ADD_ACTOR = "{ CALL AddActorToMovie (?,?,?) }";
    private static final String ADD_DIRECTOR = "{ CALL AddDirectorToMovie (?,?,?) }";
    private static final String ADD_GENRE = "{ CALL AddGenreToMovie (?,?) }";
    
    //Repo metode
    @Override
    public int createOne(Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE);)
        {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setString(ORIGINAL_TITLE, movie.getOriginalTitle());
            stmt.setString(DURATION_MINUTES, String.valueOf(movie.getDurationMinutes()));
            stmt.setString(YEAR, String.valueOf(movie.getYear()));
            stmt.setString(IMAGE_URL, movie.getImageUrl());
            stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);
            
            stmt.executeUpdate();
            return stmt.getInt(ID_MOVIE);
        }
    }

    @Override
    public void createMany(List<Movie> movies) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CREATE_MOVIE);)
        {
            for (Movie movie : movies) {
                stmt.setString(TITLE, movie.getTitle());
                stmt.setString(DESCRIPTION, movie.getDescription());
                stmt.setString(ORIGINAL_TITLE, movie.getOriginalTitle());
                stmt.setString(DURATION_MINUTES, String.valueOf(movie.getDurationMinutes()));
                stmt.setString(YEAR, String.valueOf(movie.getYear()));
                stmt.setString(IMAGE_URL, movie.getImageUrl());
                stmt.registerOutParameter(ID_MOVIE, Types.INTEGER);

                stmt.executeUpdate();   
            }

        }        
    }

    @Override
    public void updateOne(int id, Movie movie) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(UPDATE_MOVIE);)
        {
            stmt.setInt(ID_MOVIE, id);
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(DESCRIPTION, movie.getDescription());
            stmt.setString(ORIGINAL_TITLE, movie.getOriginalTitle());
            stmt.setString(DURATION_MINUTES, String.valueOf(movie.getDurationMinutes()));
            stmt.setString(YEAR, String.valueOf(movie.getYear()));
            stmt.setString(IMAGE_URL, movie.getImageUrl());


            stmt.executeUpdate();   
        }   
    }

    @Override
    public void deleteOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(DELETE_MOVIE);)
        {
            stmt.setInt(ID_MOVIE, id);

            stmt.executeUpdate();   
        }  
    }

    @Override
    public Optional<Movie> selectOne(int id) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(SELECT_MOVIE);) {

            stmt.setInt(ID_MOVIE, id);
            try (ResultSet rs = stmt.executeQuery();) {
                if (rs.next()) {
                    return Optional.of(
                            new Movie(
                                    rs.getInt(ID_MOVIE),
                                    rs.getString(TITLE),
                                    rs.getString(DESCRIPTION),
                                    rs.getString(ORIGINAL_TITLE),
                                    rs.getInt(DURATION_MINUTES),
                                    rs.getInt(YEAR),
                                    rs.getString(IMAGE_URL)
                            )
                    );
                }
            }

        }
        return Optional.empty();
    }

    @Override
    public List<Movie> selectMany() throws Exception {
        List<Movie> movies = new ArrayList<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(SELECT_MOVIES); 
                ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                movies.add(
                        new Movie(
                                    rs.getInt(ID_MOVIE),
                                    rs.getString(TITLE),
                                    rs.getString(DESCRIPTION),
                                    rs.getString(ORIGINAL_TITLE),
                                    rs.getInt(DURATION_MINUTES),
                                    rs.getInt(YEAR),
                                    rs.getString(IMAGE_URL)
                        )
                );
            }
        }

        return movies;
    }

    
    //Metode za movie object binding
    @Override
    public Set<Person> getActorsForMovie(Movie movie) throws Exception{
        Set<Person> actors = new HashSet<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(GET_ACTORS);)
        {
            stmt.setInt(ID_MOVIE, movie.getId());
            try(ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    actors.add(
                            new Person(
                                        rs.getInt(ID_ACTOR),
                                        rs.getString(NAME),
                                        rs.getString(SURNAME)
                            )
                    );
                }
            }
        }


        return actors;
    }

    @Override
    public Set<Person> getDirectorsForMovie(Movie movie) throws Exception {
        Set<Person> directors = new HashSet<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(GET_DIRECTORS);)
        {
            stmt.setInt(ID_MOVIE, movie.getId());
            try(ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    directors.add(
                            new Person(
                                        rs.getInt(ID_DIRECTOR),
                                        rs.getString(NAME),
                                        rs.getString(SURNAME)
                            )
                    );
                }
            }
        }

        return directors;
    }

    @Override
    public Set<Genre> getGenresForMovie(Movie movie) throws Exception {
        Set<Genre> genres = new HashSet<>();

        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); 
                CallableStatement stmt = con.prepareCall(GET_GENRES);)
        {
            stmt.setInt(ID_MOVIE, movie.getId());
            try(ResultSet rs = stmt.executeQuery();) {
                while (rs.next()) {
                    genres.add(
                            new Genre(
                                        rs.getInt(ID_GENRE),
                                        rs.getString(NAME)
                            )
                    );
                }
            }
        }

        return genres;
    }

    @Override
    public void addActorToMovie(Movie movie, Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(ADD_ACTOR);)
        {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(NAME, person.getName());
            stmt.setString(SURNAME, person.getSurname());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void addDirectorToMovie(Movie movie, Person person) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(ADD_DIRECTOR);)
        {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(NAME, person.getName());
            stmt.setString(SURNAME, person.getSurname());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void addGenreToMovie(Movie movie, Genre genre) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(ADD_GENRE);)
        {
            stmt.setString(TITLE, movie.getTitle());
            stmt.setString(NAME, genre.getName());
            
            stmt.executeUpdate();
        }
    }

    @Override
    public void clearData() throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection(); CallableStatement stmt = con.prepareCall(CLEAR_DATA);)
        {            
            stmt.executeUpdate();
        }
    }
}
