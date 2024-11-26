package com.alura.literAlura.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Author {

    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;*/

    @Id
    @Column(unique = true)
    private String name;

    @JsonProperty("birth_year")
    private Integer birthYear;

    @JsonProperty("death_year")
    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Book> books ;


    public Author() {
    }

    public Author(DataAuthor author){
        this.name = author.name();
        this.birthYear = author.birthYear();
        this.deathYear = author.deathYear();
        this.books = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "----- Autor -----"+ "\n" +
                "Autor: " + name + "\n" +
                "Fecha de nacimiento: " + birthYear + "\n" +
                "Fecha de fallecimiento: " + deathYear + "\n" +
                "Libros: " + books.stream().map(Book::getTitle)
                .collect(Collectors.joining(", ")) + "\n" +
                "-----------------" + "\n";
    }
}
