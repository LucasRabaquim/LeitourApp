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

    public BookApi() {
    }
    public BookApi(String _errorMessage) {
        errorMessage = _errorMessage;
        successRequest = false;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }
    public String getIsbn10() {
        return isbn10;
    }


    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCategories() {
        return category;
    }

    public void setCategories(String categories) {
        this.category = categories;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
    public boolean getSuccess() {
        return successRequest;
    }
    public String getMessage() {
        return errorMessage;
    }
}