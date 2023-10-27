package hr.algebra.model;

import java.util.Objects;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 *
 * @author Luka
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id" ,"title", "description", "originalTitle", "durationMinutes", "actors", "directors", "genres", "year", "imageUrl"})
public class Movie {

    @XmlElement(name = "id")    
    private int id;
    @XmlElement(name = "title")
    private String title;
    @XmlElement(name = "description")
    private String description;
    @XmlElement(name = "originalTitle")
    private String originalTitle;
    @XmlElement(name = "durationMinutes")
    private int durationMinutes;
    @XmlElement(name = "year")
    private int year;
    @XmlElement(name = "imageUrl")
    private String imageUrl;
    @XmlElementWrapper
    @XmlElement(name = "actors")
    private Set<Person> actors;
    @XmlElementWrapper
    @XmlElement(name = "directors")
    private Set<Person> directors;
    @XmlElementWrapper
    @XmlElement(name = "genres")
    private Set<Genre> genres;

    
    public Movie() {
    }

    public Movie(Set<Person> actors, Set<Person> directors, Set<Genre> genres, int id, String title, String description, String originalTitle, int durationMinutes, int year, String imageUrl) {
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
        this.id = id;
        this.title = title;
        this.description = description;
        this.originalTitle = originalTitle;
        this.durationMinutes = durationMinutes;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    public Movie(Set<Person> actors, Set<Person> directors, Set<Genre> genres, String title, String description, String originalTitle, int durationMinutes, int year, String imageUrl) {
        this.actors = actors;
        this.directors = directors;
        this.genres = genres;
        this.title = title;
        this.description = description;
        this.originalTitle = originalTitle;
        this.durationMinutes = durationMinutes;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    


    public Movie(int id, String title, String description, String originalTitle, int durationMinutes, int year, String imageUrl) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.originalTitle = originalTitle;
        this.durationMinutes = durationMinutes;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    public Movie(String title, String description, String originalTitle, int durationMinutes, int year, String imageUrl) {
        this.title = title;
        this.description = description;
        this.originalTitle = originalTitle;
        this.durationMinutes = durationMinutes;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Person> getActors() {
        return actors;
    }

    public void setActors(Set<Person> actors) {
        this.actors = actors;
    }

    public Set<Person> getDirectors() {
        return directors;
    }

    public void setDirectors(Set<Person> directors) {
        this.directors = directors;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    
    @Override
    public String toString() {
        return id + " - " + title;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.title);
        hash = 47 * hash + Objects.hashCode(this.originalTitle);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Movie other = (Movie) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        return Objects.equals(this.originalTitle, other.originalTitle);
    }


    
    
    
    
    
}
