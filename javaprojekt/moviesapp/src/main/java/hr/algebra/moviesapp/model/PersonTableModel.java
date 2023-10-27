/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.moviesapp.model;


import hr.algebra.model.Person;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Luka
 */
public class PersonTableModel extends AbstractTableModel{

    private List<Person> persons;
    
    private static final String[] COLUMN_NAMES = {
        "Id",
        "Name",
        "Surname"
    };

    public PersonTableModel(List<Person> persons) {
        this.persons = persons;
    }

    public PersonTableModel() {
    }
    
    public void setPersons(List<Person> p)
    {
        persons = p;
    }
    
    
    
    @Override
    public int getRowCount() {
        return persons.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return persons.get(rowIndex).getId();
            case 1:
                return persons.get(rowIndex).getName();
            case 2:
                return persons.get(rowIndex).getSurname();
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
