package bookshare.api.controllers;

import bookshare.api.entities.BookEntity;

import bookshare.api.models.BookAddResponce;
import bookshare.api.models.BookData;

import bookshare.api.repositories.BookRepository;
import bookshare.api.repositories.impl.BookRepositoryImpl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin

public class BookController {
    private final BookRepository BookRepository;

    public BookController() {
        this.BookRepository = new BookRepositoryImpl();
    }

    @PostMapping("/api/book/add")
    public BookAddResponce posting(@RequestBody BookData addRequest) throws Exception {

        BookEntity newBook = new BookEntity();
        newBook.setName(addRequest.getName());
        newBook.setAuthor(addRequest.getAuthor());
        newBook.setGenre(addRequest.getName());
        newBook.setYear(addRequest.getYear());
        newBook.setDescription(addRequest.getDescription());

        BookEntity insertedBook = this.BookRepository.insert(newBook);

        BookAddResponce bookResponse = new BookAddResponce();
        bookResponse.setId(insertedBook.getId());

        return bookResponse;
    }

    @GetMapping(value = "/api/book")  //return data set of books
    public List<BookData> getAllBooks() throws Exception {
        List<BookEntity> bookEntities = BookRepository.selectAll();
        List<BookData> bookDataRespons =new ArrayList<>();
        bookEntities.forEach((e)->{
            bookDataRespons.add(new BookData(e.getName(),e.getAuthor(),e.getGenre(),e.getYear(),e.getDescription()));
        });
        return bookDataRespons;
    }

}