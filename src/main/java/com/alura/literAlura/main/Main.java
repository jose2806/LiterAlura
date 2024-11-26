package com.alura.literAlura.main;

import com.alura.literAlura.model.*;
import com.alura.literAlura.repository.AuthorRepository;
import com.alura.literAlura.repository.BookRepository;
import com.alura.literAlura.service.ConsumeAPI;
import com.alura.literAlura.service.ConvertData;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private Scanner keyboard = new Scanner(System.in);
    private ConsumeAPI api = new ConsumeAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvertData converter = new ConvertData();
    private BookRepository repository;
    private AuthorRepository authorRepository;

    public Main(BookRepository repository, AuthorRepository authorRepository) {
        this.repository = repository;
        this.authorRepository = authorRepository;
    }

    public void showMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = keyboard.nextInt();
            keyboard.nextLine();

            switch (opcion) {
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    showRegisteredBooks();
                    break;
                case 3:
                    showRegisteredAuthors();
                    break;
                case 4:
                    showLiveAuthorsByYear();
                    break;
                case 5:
                    showBooksByLanguage();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicacion...");
                    break;
            }
        }
    }

    private DataBook getDataBook() {
        try {
            System.out.println("Por favor escribe el nombre del libro que deseas buscar: ");
            var bookName = keyboard.nextLine();
            var json = api.getData(URL_BASE + bookName.replace(" ", "%20"));
            GeneralData data = converter.getData(json, GeneralData.class);
            DataBook dataBook = converter.getDataBook(data, DataBook.class);
            return dataBook;
        } catch (Exception e){
            System.out.println("Error al obtener datos: " + e.getMessage());
            return  null;
        }
    }

    private void searchBookByTitle() {
        DataBook dataBook = getDataBook();
        if (dataBook == null) {
            System.out.println("No se encontró ningún libro con el título especificado.\n") ;
        } else {
            Optional<Book> existingBook = repository.findByTitleContainsIgnoreCase(dataBook.title());
            if (existingBook.isPresent()){
                System.out.println("El libro con el título '" + dataBook.title() + "' ya existe en la base de datos.\n");
                return;
            }
            DataAuthor dataAuthor = converter.getDataAuthor(dataBook, DataAuthor.class);
            Author author = new Author(dataAuthor);
            Optional<Author> existingAuthor = authorRepository.findByName(author.getName());
            if (existingAuthor.isPresent()) {
                author = existingAuthor.get();
            } else {
                authorRepository.save(author);
            }
            Book book = new Book(dataBook);
            repository.save(book);
            System.out.println("Libro guardado: " + book.getTitle());
        }
    }

    private void showRegisteredBooks() {
        List<Book> books = new ArrayList<>();
        books = repository.findAll();
        books.stream().forEach(System.out::println);
    }

    private void showRegisteredAuthors() {
        List<Author> authors = new ArrayList<>();
        authors= authorRepository.findAll();
        authors.stream().forEach(System.out::println);
    }

    private void showLiveAuthorsByYear() {
        System.out.println("Ingrese el año en el cual desea buscar: ");
        int year = keyboard.nextInt();
        keyboard.nextLine();
        List<Author> authors = authorRepository.findAll().stream()
                .filter(a -> (a.getBirthYear() <= year) &&
                        (a.getDeathYear() == null || a.getDeathYear() >= year))
                .collect(Collectors.toList());

        if (authors.isEmpty()){
            System.out.println("No se encontraron autores vivos en el año " + year);
        } else {
            System.out.println("Autores vivos en el año " + year + ":");
            authors.stream().forEach(System.out::println);
        }

    }

    private void showBooksByLanguage() {
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es - español 
                en - ingles
                fr - frances
                pt - portugues
                """);
        var language =  keyboard.nextLine();

        List<Book> books = repository.findAll().stream()
                .filter(b -> b.getLanguages().contains(language))
                .collect(Collectors.toList());

        if (books.isEmpty()){
            System.out.println("No se encontraron libros en el idioma " + language);
        } else {
                System.out.println("Libros en el idioma " + language + ":");
                books.stream().forEach(System.out::println);
            }
    }
}


