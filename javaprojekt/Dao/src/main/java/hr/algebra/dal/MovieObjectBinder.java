package hr.algebra.dal;

import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import java.util.Set;

/**
 *
 * @author Luka
 */
public interface MovieObjectBinder {
    Set<Person> getActorsForMovie(Movie movie) throws Exception;
    Set<Person> getDirectorsForMovie(Movie movie) throws Exception;
    Set<Genre> getGenresForMovie(Movie movie) throws Exception;
    void addActorToMovie(Movie movie, Person person) throws Exception;
    void addDirectorToMovie(Movie movie, Person person) throws Exception;
    void addGenreToMovie(Movie movie, Genre genre) throws Exception;
    void clearData() throws Exception;
}
