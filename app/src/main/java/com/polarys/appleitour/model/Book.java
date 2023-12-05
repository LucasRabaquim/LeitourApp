package com.polarys.appleitour.model;



import java.io.Serializable;

public class Book implements Serializable {

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
    private String isbn_10;
    private String isbn_13;
    private String language;
    private String cover;
    public Book() {}
    public Book(String _errorMessage) {
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
        return isbn_10;
    }
    public String getIsbn13() {
        return isbn_13;
    }
    public String getPublishedDate() {
        return publishedDate;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() {
        return category;
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