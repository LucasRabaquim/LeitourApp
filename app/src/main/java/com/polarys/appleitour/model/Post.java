package com.polarys.appleitour.model;

import android.util.Log;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Post implements Serializable{
    private int id;
    private int userId;

    private String userName;
    private String email;
    private String messagePost;
    private int likes = 0;
    private int Comment_number = 0;
    private boolean liked = false;
    private String createdDate;
    private String UserPhoto = null;
    private String alteratedDate = null;
    public Post() { }

    public Post(int userId, String messagePost) {
        this.userId = userId;
        this.messagePost = messagePost;
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
    public String getEmail() {
        return email;
    }
    public void SetLikes(int _likes) {
        likes = _likes;
    }
    public int GetLikes() {return likes;}
    public int GetCommentNumber() {return Comment_number;}
    public void SetLiked(boolean _liked) {liked = _liked;}
    public boolean GetLiked() {return liked;}
    public void SetMessagePost(String messagePost) {
        this.messagePost = messagePost;
    }
    public String GetCreatedDate() {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(createdDate);
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return dateTime.format(formatters);
        }
        catch(Exception e){
            return  createdDate;
        }
    }
    public void SetCreatedDate(String CreatedDate) {
        this.createdDate = CreatedDate;
    }
    public String GetAlteratedDate() {
        return alteratedDate;
    }
    public void SetAlteratedDate(String alteratedDate) {
        this.alteratedDate = alteratedDate;
    }
}