package com.example.android.booklistingapp;

public class Book {
    private String bookTitle ;
    private String bookAuthors ;

    public Book (String t , String a )
    {
        bookTitle = t ;
        bookAuthors = a ;
    }

    String getBookTitle () {return bookTitle ;}

    String getBookAuthors () {return bookAuthors ;}
}
