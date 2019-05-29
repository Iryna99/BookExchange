package bookshare.api.entities;

import java.time.LocalDateTime;

public class UserAnnounceBookEntity {
    private Integer id;
    private UserEntity userEntity;
    private BookEntity bookEntity;
    private LocalDateTime announceTimeStamp;
    private Boolean isActive;

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "UserAnnounceBookEntity{" +
                "id=" + id +
                ", userEntity=" + userEntity +
                ", bookEntity=" + bookEntity +
                ", announceTimeStamp=" + announceTimeStamp +
                '}';
    }

    public UserAnnounceBookEntity() {
    }

    public UserAnnounceBookEntity(Integer id, UserEntity userEntity, BookEntity bookEntity, LocalDateTime announceTimeStamp) {
        this.id = id;
        this.userEntity = userEntity;
        this.bookEntity = bookEntity;
        this.announceTimeStamp = announceTimeStamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public BookEntity getBookEntity() {
        return bookEntity;
    }

    public void setBookEntity(BookEntity bookEntity) {
        this.bookEntity = bookEntity;
    }

    public LocalDateTime getAnnounceTimeStamp() {
        return announceTimeStamp;
    }

    public void setAnnounceTimeStamp(LocalDateTime announceTimeStamp) {
        this.announceTimeStamp = announceTimeStamp;
    }
}