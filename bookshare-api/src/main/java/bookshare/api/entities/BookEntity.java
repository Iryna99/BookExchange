package bookshare.api.entities;

import java.time.LocalDate;

public class BookEntity {
    private Integer id;
    private String name;
    private String author;
    private String genre;
    private LocalDate year;
    private String description;

    public BookEntity(Integer id, String name, String author, String genre, LocalDate year, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookEntity() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }
}