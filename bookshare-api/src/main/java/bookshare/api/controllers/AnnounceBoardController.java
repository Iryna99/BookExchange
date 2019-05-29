package bookshare.api.controllers;

import bookshare.api.entities.*;

import bookshare.api.models.AnnounceAddRequest;
import bookshare.api.models.AnnounceAddResponse;
import bookshare.api.models.AnnounceDataResponse;

import bookshare.api.models.BookData;
import bookshare.api.repositories.AnnounceBoardRepository;
import bookshare.api.repositories.BookRepository;
import bookshare.api.repositories.UserAnnounceBookRepository;

import bookshare.api.repositories.impl.AnnounceBoardRepositoryImpl;
import bookshare.api.repositories.impl.BookRepositoryImpl;
import bookshare.api.repositories.impl.UserAnnounceBookRepositoryImpl;

import bookshare.api.utils.UserTmp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin

public class AnnounceBoardController {
    private final AnnounceBoardRepository announceBoardRepository;
    private final UserAnnounceBookRepository userAnnounceBookRepository;
    private final BookRepository bookRepository;

    public AnnounceBoardController() {
        this.userAnnounceBookRepository = new UserAnnounceBookRepositoryImpl();
        this.announceBoardRepository=new AnnounceBoardRepositoryImpl();
        this.bookRepository=new BookRepositoryImpl();
    }

    @PostMapping("/api/announce/add")
    public ResponseEntity register(@RequestBody BookData request) throws Exception {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(request.getName());
        bookEntity.setGenre(request.getGenre());
        bookEntity.setYear(request.getYear());
        bookEntity.setDescription(request.getDescription());
        bookEntity.setAuthor(request.getAuthor());

        BookEntity insertedBook = bookRepository.insert(bookEntity);
        AnnounceBoardEntity announceBoardEntity = new AnnounceBoardEntity();
        announceBoardEntity.setUserId(new UserTmp().getUserId());
        announceBoardEntity.setBookId(insertedBook.getId());
        announceBoardEntity.setAnnounceTimestamp(LocalDateTime.now());

        AnnounceBoardEntity insertedAnnounce = announceBoardRepository.insert(announceBoardEntity);

        AnnounceAddResponse announceAddResponse = new AnnounceAddResponse();
        announceAddResponse.setId(insertedAnnounce.getId());

        return new ResponseEntity<>(announceAddResponse, HttpStatus.OK);
    }

    @GetMapping(value = "/api/announce")//display data
    public ResponseEntity<List<AnnounceDataResponse>> getAllUsers() throws SQLException {
        List<UserAnnounceBookEntity> announceBoardEntities = userAnnounceBookRepository.selectAll();
        List<AnnounceDataResponse> announceDataResponses = new ArrayList<>();
        announceBoardEntities.forEach((a) -> {
            UserEntity u = a.getUserEntity();
            BookEntity b = a.getBookEntity();
            announceDataResponses.add(new AnnounceDataResponse(
                    a.getId(),
                    u.getFirstName(),
                    u.getLastName(),
                    b.getName(),
                    b.getGenre(),
                    b.getAuthor(),
                    b.getYear(),
                    a.getAnnounceTimeStamp()
            ));
        });
        return new ResponseEntity<>(announceDataResponses, HttpStatus.OK);
    }

}
