package com.example.raprap.midterm;

/**
 * Created by raprap on 2/23/2016.
 */
public class Book {

    private String mBookName;

    public Book(){

    }
    public Book(String bookName) {
        this.mBookName = bookName;
    }

    public String getBookName() {
        return mBookName;
    }

    public Book setBookName(String bookName) {
        this.mBookName = bookName;
        return this;
    }
}
