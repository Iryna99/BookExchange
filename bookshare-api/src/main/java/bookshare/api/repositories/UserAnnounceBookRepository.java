package bookshare.api.repositories;

import bookshare.api.entities.UserAnnounceBookEntity;

import java.sql.SQLException;
import java.util.List;

public interface UserAnnounceBookRepository {
    List<UserAnnounceBookEntity> selectAll() throws SQLException;
    public UserAnnounceBookEntity findById(Integer _id) throws SQLException;
}