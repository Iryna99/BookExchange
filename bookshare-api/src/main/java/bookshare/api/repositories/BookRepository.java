package bookshare.api.repositories;

import bookshare.api.entities.BookEntity;

import java.util.List;

public interface BookRepository  {

    BookEntity insert(BookEntity book) throws ClassNotFoundException, Exception;
    public List<BookEntity> selectAll() throws Exception;

}