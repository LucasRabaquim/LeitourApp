package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiThread.UPDATE;

import java.io.Serializable;
import java.time.LocalDateTime;


public class Post implements Serializable{
    private int id;
    private int userId;
    private String userName = "";
    private String messagePost;
    private int likes = 0;
    private int Comment_number = 0;
    private boolean liked = false;

    private String CreatedDate = null;
    private String UserPhoto = null;
    private String alteratedDate = null;
    public Post() { }

    public Post(int userId, String messagePost) {
        this.userId = userId;
        this.messagePost = messagePost;
    }
    public Post(int id, int userId, String messagePost, int likes, String CreatedDate, String alteratedDate) {
        this.id = id;
        this.userId = userId;
        this.messagePost = messagePost;
        this.CreatedDate = null;
        this.alteratedDate = null;
    }

    public Post(int id, int userId, String message) {
        this.id = id;
        this.userId = userId;
        this.messagePost = message;
    }

    public int GetId() {
        return id;
    }

    public void SetId(int id) {
        this.id = id;
    }

    public int GetUserId() {
        return userId;
    }
    public void SetUserId(int userId) {
        this.userId = userId;
    }
    public String GetMessagePost() {
        return messagePost;
    }
    public String GetUserName() {
        return userName;
    }
    public int GetLikes() {
        return likes;
    }
    public void SetMessagePost(String messagePost) {
        this.messagePost = messagePost;
    }
    public String GetCreatedDate() {
        return CreatedDate;
    }
    public void SetCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }
    public String GetAlteratedDate() {
        return alteratedDate;
    }
    public void SetAlteratedDate(String alteratedDate) {
        this.alteratedDate = alteratedDate;
    }
}