package com.polarys.appleitour.model;

import java.util.List;

public class SavedBook {
    private int id;
    private int userId;
    private boolean isPublic;
    private String bookKey;

    public SavedBook() {  }

    public SavedBook(int id, int userId, boolean isPublic, String bookKey) {
        this.id = id;
        this.userId = userId;
        this.isPublic = isPublic;
        this.bookKey = bookKey;
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

    public void setBookKey(String bookKey) {
        this.bookKey = bookKey;
    }
}
