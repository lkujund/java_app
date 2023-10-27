package hr.algebra.dal;

import hr.algebra.dal.sql.DataSourceSingleton;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RepositoryFactory {

    private static final String PATH = "/config/repository.properties";

    public enum RepositoryType
    {
        ACTOR_REPO,
        DIRECTOR_REPO,
        GENRE_REPO,
        MOVIE_REPO,
        USER_REPO;
    }
    
    private static final Properties properties = new Properties();


    
    public static void main(String[] args) {
        System.out.println(repository);
    }
    
    private static Repository repository;

    private RepositoryFactory() {
    }

    public static Repository getRepository(RepositoryType repoType) {
        try(InputStream is = DataSourceSingleton.class
                .getResourceAsStream(PATH)) {
            properties.load(is);
            
            repository = (Repository)
                    Class.forName(properties.getProperty(repoType.name()))
                    .getDeclaredConstructor()
                    .newInstance();
            return repository;
            
        } catch (Exception ex) {
            Logger.getLogger(DataSourceSingleton.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
