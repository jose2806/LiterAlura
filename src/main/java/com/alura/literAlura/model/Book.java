package com.alura.literAlura.model;

import jakarta.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String title;

    @ManyToOne
    private Author author;

    private List<String> languages;

    private Double downloadCount;

    public Book() {
    }

    public Book(DataBook book){
       this.title = book.title();
       this.author = book.authors().getFirst();
       this.downloadCount = book.downloadCount();
       this.languages = book.languages();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthors() {
        return author;
    }

    public void setAuthors(Author authors) {
        this.author = authors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public Double getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Double downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "----- LIBRO -----" + "\n" +
                "Titulo:" + title + "\n" +
                "Autor: " + (author != null ? author.getName() : "null") + "\n" +
                "Idioma: " + languages + "\n" +
                "Numero de descargas: " + downloadCount + "\n" +
                "-----------------" + "\n";
    }
}
