package com.example.android.books;

/*
* Book class to hold as book object
* */
public class Book {
    public String book_name;
    public String book_auther;
    public int book_cover_id;
    public int book_icon_id;
    public String description;
    public String img_url;

    public Book(String book_name, String book_auther, int book_cover_id, int book_icon_id, String description, String img_url) {
        this.book_name = book_name;
        this.book_auther = book_auther;
        this.book_cover_id = book_cover_id;
        this.book_icon_id = book_icon_id;
        this.description = description;
        this.img_url = img_url;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

}
