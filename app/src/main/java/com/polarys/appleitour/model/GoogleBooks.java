package com.polarys.appleitour.model;

import java.util.List;

public class GoogleBooks {
    private String key;
    private String title;
    private String subtitle;
    private String authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private String isbn10;
    private String isbn13;
    private int pages;
    private String categories;
    private String language;
    private String cover;

    public GoogleBooks() {}

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public int getPages() {
        return pages;
    }

    public String getCategories() {
        return categories;
    }

    public String getLanguage() {
        return language;
    }

    public String getCover() {
        return cover;
    }
}
