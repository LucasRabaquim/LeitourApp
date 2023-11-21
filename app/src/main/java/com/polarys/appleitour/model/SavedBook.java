package com.polarys.appleitour.model;


import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import com.polarys.appleitour.api.ApiThread;

import java.io.Serializable;
import java.util.List;

public class SavedBook implements Serializable {
    private int id;
    private int userId;
    private boolean isPublic;
    private String bookKey;
    private String bookTitle;
    private String bookCover;

    public SavedBook() {  }

    public SavedBook(int id, int userId, boolean isPublic, String bookKey,String bookTitle,String bookCover) {
        this.userId = userId;
        this.isPublic = isPublic;
        this.bookKey = bookKey;
        this.bookTitle = bookTitle;
        this.bookCover = bookCover;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getBookKey() {
        return bookKey;
    }
    public String getBookTitle() { return bookTitle; }
    public String getBookCover() { return bookTitle; }

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }

}
