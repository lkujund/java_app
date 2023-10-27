/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.moviesapp.model;

import hr.algebra.model.Genre;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class GenreTableModel extends AbstractTableModel{
    private List<Genre> genres;

    private static final String[] COLUMN_NAMES = {
        "Id",
        "Name"
    };

    public GenreTableModel(List<Genre> genres) {
        this.genres = genres;
    }

    public GenreTableModel() {
    }
    
    public void setGenres(List<Genre> g)
    {
        genres = g;
    }
    
    
    @Override
    public int getRowCount() {
        return genres.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return genres.get(rowIndex).getId();
            case 1:
                return genres.get(rowIndex).getName();
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
