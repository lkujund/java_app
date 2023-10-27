/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.algebra.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luka
 */
    @XmlRootElement(name="moviecollection")
    @XmlAccessorType(XmlAccessType.FIELD)
public class MovieLibrary {

    private List<Movie> movies;

    public MovieLibrary() {
    }

    
    
    public MovieLibrary(List<Movie> movies) {
        this.movies = movies;
    }

    

    
    
}
