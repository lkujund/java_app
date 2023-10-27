package hr.algebra.moviesapp.parsers;

import hr.algebra.factory.ParserFactory;
import hr.algebra.factory.UrlConnectionFactory;
import hr.algebra.model.Genre;
import hr.algebra.model.Movie;
import hr.algebra.model.Person;
import hr.algebra.utilities.FileUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Luka
 */
public class Parser {
    private static final String URL = "https://www.blitz-cinestar-bh.ba/rss.aspx?id=2682"; 
    
    private static final String DIRECTORY = "assets";
    private static final String EXTENSION = ".jpg";
    
public static List<Movie> parse() throws IOException, XMLStreamException {
    List<Movie> movies = new ArrayList<>();
    HttpURLConnection con = UrlConnectionFactory.getHttpUrlConnection(URL);

    try (InputStream is = con.getInputStream()) { // stream will close the connection
        XMLEventReader reader = ParserFactory.createStaxParser(is);

        Optional<TagType> tagType = Optional.empty();
        Movie movie = null;
        StartElement startElement = null;
            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                switch (event.getEventType()) {
                    case XMLStreamConstants.START_ELEMENT -> {
                        startElement = event.asStartElement();
                        String qName = startElement.getName().getLocalPart();
                        tagType = TagType.from(qName);

                        if (tagType.isPresent() && tagType.get().equals(TagType.ITEM)) {
                            movie = new Movie();
                            movies.add(movie);
                        }
                    }
                    case XMLStreamConstants.CHARACTERS -> {
                        if (tagType.isPresent() && movie != null) {
                            Characters characters = event.asCharacters();
                            String data = characters.getData().trim();
                            switch (tagType.get()) {
                                case TITLE -> {
                                    if (!data.isEmpty()) {
                                        movie.setTitle(data);
                                    }
                                }                                
                                case DESCRIPTION -> {
                                    if (!data.isEmpty()) {
                                        Document document = Jsoup.parse(data);
                                        String description = document.text();
                                        movie.setDescription(description);
                                    }
                                }
                                case ORIGNAZIV -> {
                                    if (!data.isEmpty()) {
                                        movie.setOriginalTitle(data);
                                    }
                                }
                                case REDATELJ -> {
                                    if (!data.isEmpty()) {
                                        Set<Person> directors = handlePersons(data);
                                        movie.setDirectors(directors);

                                    }
                                }
                                case GLUMCI -> {
                                    if (!data.isEmpty()) {
                                        Set<Person> actors = handlePersons(data);
                                        movie.setActors(actors);

                                    }
                                }
                                case TRAJANJE -> {
                                    if (!data.isEmpty()) {
                                        movie.setDurationMinutes(Integer.parseInt(data));
                                    }
                                }
                                case GODINA -> {
                                    if (!data.isEmpty()) {
                                        movie.setYear(Integer.parseInt(data));
                                    }
                                }
                                case ZANR -> {
                                    if (!data.isEmpty()) {
                                        Set<Genre> genres = handleGenres(data);
                                        movie.setGenres(genres);

                                    }
                                }
                                case PLAKAT -> {
                                    if (!data.isEmpty() && startElement != null && movie.getImageUrl()== null) {
                                        handlePicture(movie, data);
                                    }

                                }
                            }
                        }
                    }
                }
            }
    }

    return movies;
}

    private static Set<Person> handlePersons(String persons) {
        try {
            return Arrays.stream(persons.split(","))
                    .map(String::trim)
                    .map(pair -> pair.split(" ", 2))
                    .map(pair -> new Person(pair[0], pair[1]))
                    .collect(Collectors.toSet());
        } catch (Exception e) {
            return new HashSet<Person>();
        }
    }

    private static Set<Genre> handleGenres(String genres) {
        return Arrays.stream(genres.split(","))
                .map(String::trim)
                .map(Genre::new)
                .collect(Collectors.toSet());
    }

    private static void handlePicture(Movie movie, String pictureUrl) {

        try {
            String ext = pictureUrl.substring(pictureUrl.lastIndexOf("."));
            if (ext.length() > 4) {
                ext = EXTENSION;
            }
            String pictureName = UUID.randomUUID() + ext;
            String localPicturePath = DIRECTORY + File.separator + pictureName;

            FileUtils.copyFromUrl(pictureUrl, localPicturePath);
            movie.setImageUrl(localPicturePath);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

private enum TagType {

    ITEM("item"),
    TITLE("title"),
    DESCRIPTION("description"),
    ORIGNAZIV("orignaziv"),
    REDATELJ("redatelj"),
    GLUMCI("glumci"),
    TRAJANJE("trajanje"),
    GODINA("godina"),
    ZANR("zanr"),
    PLAKAT("plakat");




    private final String name;

    private TagType(String name) {
        this.name = name;
    }

    private static Optional<TagType> from(String name) {
        for (TagType value : values()) {
            if (value.name.equals(name)) {
                return Optional.of(value);
            }
        }
        return Optional.empty();
    }
}
}
