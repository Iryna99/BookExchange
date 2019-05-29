package bookshare.api.models;

import java.time.LocalDate;

public class BookData {
    private String name;
    private String author;
    private String genre;
    private LocalDate year;
    private String description;

    public BookData(String name, String author, String genre, LocalDate year, String description) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
