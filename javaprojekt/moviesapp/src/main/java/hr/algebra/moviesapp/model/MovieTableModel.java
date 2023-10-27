/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.moviesapp.model;

import hr.algebra.model.Movie;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class MovieTableModel extends AbstractTableModel{

    private List<Movie> movies;
    
    private static final String[] COLUMN_NAMES = {
        "Id",
        "Title",
        "Description",
        "Original title",
        "Directors",
        "Actors",
        "Duration",
        "Year",
        "Genres",
        "Image"
    };
    
    //NAPRAVITI MOVIE KLASU U DAO MODELU private List<Movie> movies;
    
    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }
    
    public void setMovies(List<Movie> moviesList)
    {
        List<Movie> uniqueMovies = new ArrayList<>(new HashSet<>(moviesList));
        this.movies = uniqueMovies;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getDescription();
            case 3:
                return movies.get(rowIndex).getOriginalTitle();
            case 4:
                return movies.get(rowIndex).getDirectors();
            case 5:
                return movies.get(rowIndex).getActors();
            case 6:
                return movies.get(rowIndex).getDurationMinutes();
            case 7:
                return movies.get(rowIndex).getYear();
            case 8:
                return movies.get(rowIndex).getGenres();
            case 9:
                return movies.get(rowIndex).getImageUrl();                
            default:
                throw new RuntimeException("No such column");
        }
    }
    
    @Override
    public String getColumnName(int column)
    {
        return COLUMN_NAMES[column];
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex); 
    }
}
