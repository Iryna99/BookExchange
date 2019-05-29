package bookshare.api.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AnnounceDataResponse {
    private Integer id;
    private String firstName;
    private String lastName;
    private String bookName;
    private String bookGenre;
    private String author;
    private LocalDate year;
    private LocalDateTime announceTimestamp;

    public AnnounceDataResponse(Integer id, String firstName, String lastName, String bookName, String bookGenre, String author, LocalDate year, LocalDateTime announceTimestamp) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookName = bookName;
        this.bookGenre = bookGenre;
        this.author = author;
        this.year = year;
        this.announceTimestamp = announceTimestamp;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstNname() {
        return firstName;
    }

    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
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

    public LocalDateTime getAnnounceTimestamp() {
        return announceTimestamp;
    }

    public void setAnnounceTimestamp(LocalDateTime announceTimestamp) {
        this.announceTimestamp = announceTimestamp;
    }
}