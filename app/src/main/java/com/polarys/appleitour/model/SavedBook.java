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
    private String bookAuthor;
    private String bookCover;

    public SavedBook() {  }

    public SavedBook(int _id, int _userId, boolean _isPublic, String _bookKey,String _bookTitle,String _bookAuthor,String _bookCover) {
        userId = _userId;
        isPublic = _isPublic;
        bookKey = _bookKey;
        bookTitle = _bookTitle;
        bookAuthor = _bookAuthor;
        bookCover = _bookCover;
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
    public String getBookAuthor() { return bookAuthor; }
    public String getBookCover() { return bookCover; }

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }

}
