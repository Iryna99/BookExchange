package bookshare.api.entities;

public class OrderEntity {
    Integer userId;
    Integer announceId;
    String comment;
    Boolean isActive;

    public OrderEntity() {
    }

    public OrderEntity(Integer userId, Integer announceId, String comment, Boolean isActive) {
        this.userId = userId;
        this.announceId = announceId;
        this.comment = comment;
        this.isActive = isActive;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer clientId) {
        this.userId = clientId;
    }

    public Integer getAnnounceId() {
        return announceId;
    }

    public void setAnnounceId(Integer announceId) {
        this.announceId = announceId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "userId=" + userId +
                ", announceId=" + announceId +
                ", comment='" + comment + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}