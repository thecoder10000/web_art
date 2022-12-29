package dduwcom.mobile.finalreport;

import java.io.Serializable;

public class Book implements Serializable {
    private long _id;
    private String title;
    private String author;
    private String publisher;
    private String summary;
    private String price;
    private String image;

    public Book(long _id, String title, String author, String publisher, String summary, String price, String image) {
        this._id = _id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.summary = summary;
        this.price = price;
        this.image = image;
    }

    public Book(String title, String author, String publisher, String summary, String price) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.summary = summary;
        this.price = price;
        this.image = null;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return  " 제목 : " + title +
                ", 저자 : " + author +
                ", 출판사 : " + publisher +
                ", 가격 : " + price;
    }
}
