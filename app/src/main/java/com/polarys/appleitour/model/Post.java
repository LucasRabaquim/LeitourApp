package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiThread.UPDATE;

import android.util.Log;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Post implements Serializable{
    private int id;
    private int userId;

    @Expose(serialize = false)
    private String userName = "";
    @Expose(serialize = false)
    private String email = "";
    private String messagePost;
    private int likes = 0;
    private int Comment_number = 0;
    private boolean liked = false;
    private String CreatedDate;
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
            LocalDateTime datetime = LocalDateTime.parse(CreatedDate, DateTimeFormatter.ofPattern("dd/MM/yy"));
            return datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception e){
            Log.d("GetCreatedDate", "GetCreatedDate: " +e);
            return CreatedDate;
        }
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