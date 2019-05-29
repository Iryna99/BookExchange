package bookshare.api.models;

public class BookAddResponce {
    private Integer id;

    public  BookAddResponce(){}

    public BookAddResponce(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
