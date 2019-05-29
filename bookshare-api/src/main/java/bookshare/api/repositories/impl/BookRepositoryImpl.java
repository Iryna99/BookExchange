package bookshare.api.repositories.impl;

import bookshare.api.ConnectionManager;
import bookshare.api.entities.AnnounceBoardEntity;
import bookshare.api.entities.BookEntity;
import bookshare.api.repositories.BookRepository;
import java.sql.*;
import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import java.util.ArrayList;
import java.util.List;


public class BookRepositoryImpl implements BookRepository {

    private static final String INSERT_Book_SQL = "INSERT INTO \"book\" (name, author, genre, year, description) VALUES (?, ?, ?, ?, ?) RETURNING id";
    private static final String SELECT_ALL = "SELECT * FROM public.book";

    public BookEntity insert(BookEntity book) throws Exception {

        Connection dbConnection = ConnectionManager.getConnection();

            try (PreparedStatement statement = dbConnection.prepareStatement(INSERT_Book_SQL)) {
                statement.setString(1, book.getName());
                statement.setString(2, book.getAuthor());
                statement.setString(3, book.getGenre());
                statement.setDate(4, Date.valueOf(book.getYear()));
                statement.setString(5, book.getDescription());

                try (ResultSet generatedKeys = statement.executeQuery()) {
                    if (generatedKeys.next()) {
                        book.setId(generatedKeys.getInt(1));
                    }
                }
            }
            return book;
        }

    public List<BookEntity> selectAll() throws SQLException {
        List<BookEntity> entities = new ArrayList<>();
        try (
                Connection connection = ConnectionManager.getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SELECT_ALL)
        ) {
            while (resultSet.next()) {
                entities.add(parseResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entities;
    }

    private BookEntity parseResultSet(ResultSet resultSet) throws SQLException {
        Integer id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String author=resultSet.getString("author");
        String genre=resultSet.getString("genre");
        LocalDate year=resultSet.getDate("year").toLocalDate();
        String description=resultSet.getString("description");
        return new BookEntity(id, name, author, genre,year,description);
    }

    public static void main(String[] args) throws Exception {
        /*new BookRepositoryImpl().insert(new BookEntity(0l,"The song of ice and fire","R. Martin","fantasy",
                LocalDate.ofYearDay(2007,1),""));
        new BookRepositoryImpl().insert(new BookEntity(1l,"Harry Potter","J.K. Rowling","fantasy",
                LocalDate.ofYearDay(1997,1),""));
        new BookRepositoryImpl().insert(new BookEntity(2l,"Sherlock Holmes"," Sir Arthur Conan Doyle","Detective",
                LocalDate.ofYearDay(1887,1),""));
        new BookRepositoryImpl().insert(new BookEntity(3l,"Lord of the rings","J. R. R. Tolkien","fantasy",
                LocalDate.ofYearDay(1949,1),""));*/

    }
}
