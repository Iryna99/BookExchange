package bookshare.api.repositories;

import bookshare.api.entities.UserEntity;

public interface UserRepository  {

    UserEntity insert(UserEntity user) throws ClassNotFoundException, Exception;
}