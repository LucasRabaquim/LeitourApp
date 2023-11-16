package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.GET;
import static com.polarys.appleitour.api.ApiRequest.GETPUBLIC;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.interfaces.BookApiInterface;

import java.io.Serializable;

public class BookApi implements Serializable, BookApiInterface {

    public static final String TITLE = "Title";
    public static final String AUTHOR = "Author";
    public static final String ISBN = "Isbn";
    private String key;
    private String title;
    private String authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private int pages;
    private String categories;
    private String isbn10;
    private String isbn13;
    private String language;
    private String cover;

    public BookApi() {
    }
    //public BookApi(@NonNull View itemView) {     super(itemView);    }

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
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
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

    public ApiResponse GetByIsbn(String search) {
        ApiThread apiThread = new ApiThread(GETPUBLIC, "/SearchBy/"+ISBN + "/" + search, null);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse GetByTitle(String search) {
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, "SearchBy/"+TITLE + "/" + search);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse GetByAuthor(String search) {
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, "SearchBy/"+AUTHOR + "/" + search, null);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse GetByKey(String search) {
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, "SearchBy/Key/" + search);
        return apiThread.CreateThread(apiThread).getJson();
    }
}