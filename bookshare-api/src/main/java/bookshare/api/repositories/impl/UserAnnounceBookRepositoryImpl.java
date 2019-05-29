package bookshare.api.repositories.impl;

import bookshare.api.ConnectionManager;
import bookshare.api.entities.BookEntity;
import bookshare.api.entities.UserAnnounceBookEntity;
import bookshare.api.entities.UserEntity;
import bookshare.api.repositories.UserAnnounceBookRepository;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserAnnounceBookRepositoryImpl implements UserAnnounceBookRepository {

    private static final String SELECT_ALL = " SELECT\n" +
            "a.id AS announce_id,\n" +
            "a.user_id,\n" +
            "a.book_id,\n" +
            "a.announce_timestamp,\n" +
            "u.first_name,\n" +
            "u.last_name,\n" +
            "u.email,\n" +
            "u.phone,\n" +
            "u.password,\n" +
            "u.is_active,\n" +
            "b.name,\n" +
            "b.genre,\n" +
            "b.description,\n" +
            "b.author,\n" +
            "b.year\n" +
            "FROM public.announce_board a JOIN public.\"user\" u ON  a.user_id=u.id JOIN  public.\"book\" b ON a.book_id = b.id";

    @Override
    public List<UserAnnounceBookEntity> selectAll() {
        List<UserAnnounceBookEntity> entities = new ArrayList<>();
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

    private UserAnnounceBookEntity parseResultSet(ResultSet rs) throws SQLException {

        UserAnnounceBookEntity entity = new UserAnnounceBookEntity();
        entity.setId(rs.getInt("announce_id"));

        UserEntity userEntity = new UserEntity();
        userEntity.setId(rs.getInt("user_id"));
        userEntity.setFirstName(rs.getString("first_name"));
        userEntity.setLastName(rs.getString("last_name"));
        userEntity.setEmail(rs.getString("email"));
        userEntity.setPhone(rs.getString("phone"));
        userEntity.setPassword(rs.getString("password"));
        userEntity.setActive(rs.getBoolean("is_active"));
        entity.setUserEntity(userEntity);

        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(rs.getInt("book_id"));
        bookEntity.setName(rs.getString("name"));
        bookEntity.setGenre(rs.getString("genre"));
        bookEntity.setDescription(rs.getString("description"));
        bookEntity.setAuthor(rs.getString("author"));
        bookEntity.setYear(rs.getDate("year").toLocalDate());
        entity.setBookEntity(bookEntity);

        entity.setAnnounceTimeStamp(rs.getTimestamp("announce_timestamp").toLocalDateTime());

        return entity;
    }

    public static void main(String[] args) {
        UserAnnounceBookRepositoryImpl  userAnnounceBookRepository = new UserAnnounceBookRepositoryImpl();
        userAnnounceBookRepository.selectAll().forEach(System.out::println);
    }


}
