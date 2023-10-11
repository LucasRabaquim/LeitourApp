package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.GET;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.polarys.appleitour.api.ApiThread;

import java.io.Serializable;

public class BookApi implements Serializable {
    private String key;
    private String title;
    private String authors;
    private String publisher;
    private String publishedDate;
    private String description;
    private int pages;
    private String categories;
    private String language;
    private String cover;

    private final String TITLE = "Title";
    private final String ISBN = "Isbn";

    public BookApi(){}
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

    public ApiResponse searchBookApi(String param, String search) {
        ApiThread apiThread;
        switch (param) {
            case TITLE:
                apiThread = new ApiThread(GET, TITLE + "/" + search, null);
                break;
            case ISBN:
                apiThread = new ApiThread(GET, ISBN + "/" + search, null);
                break;
            default:
                return new ApiResponse();
        }
        ;
        Thread thread = new Thread(apiThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return apiThread.getJson();
    }
}