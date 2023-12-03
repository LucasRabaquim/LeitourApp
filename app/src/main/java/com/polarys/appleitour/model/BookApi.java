package com.polarys.appleitour.model;



import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;

import com.polarys.appleitour.api.ApiThread;

import java.io.Serializable;

public class BookApi implements Serializable {

    private String key;
    private boolean successRequest = true;
    private String  errorMessage;
    private String title;
    private String authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private int pages;
    private String category;
    private String isbn10;
    private String isbn13;
    private String language;
    private String cover;
    public BookApi() {}
    public BookApi(String _errorMessage) {
        errorMessage = _errorMessage;
        successRequest = false;
    }
    public String getKey() {
        return key;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthors() {
        return authors;
    }
    public String getPublisher() {
        return publisher;
    }
    public String getIsbn10() {
        return isbn10;
    }
    public String getIsbn13() {
        return isbn13;
    }
    public String getPublishedDate() {
        return publishedDate;
    }
    public String getDescription() {
        return description;
    }
    public int getPages() {
        return pages;
    }
    public String getLanguage() {
        return language;
    }
    public String getCover() {
        return cover;
    }
    public boolean getSuccess() {
        return successRequest;
    }
    public String getMessage() {
        return errorMessage;
    }
}