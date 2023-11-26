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
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userName = "";
    private String messagePost;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int likes = 0;

    private String postDate;
    private String alteratedDate;
    public Post() { }

    public Post(int userId, String messagePost) {
        this.userId = userId;
        this.messagePost = messagePost;
        this.postDate = LocalDateTime.now()+"Z";
        this.alteratedDate = LocalDateTime.now()+"Z";
    }
    public Post(int id, int userId, String messagePost, int likes, String postDate, String alteratedDate) {
        this.id = id;
        this.userId = userId;
        this.messagePost = messagePost;
       // this.likes = likes;
        this.postDate = null;
        this.alteratedDate = null;
    }

    public Post(int id, int userId, String message) {
        this.id = id;
        this.userId = userId;
        this.messagePost = messagePost;
        this.postDate = LocalDateTime.now()+"Z";
        this.alteratedDate = LocalDateTime.now()+"Z";
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

    /*public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }*/

    public String GetPostDate() {
        return postDate;
    }

    public void SetPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String GetAlteratedDate() {
        return alteratedDate;
    }

    public void SetAlteratedDate(String alteratedDate) {
        this.alteratedDate = alteratedDate;
    }
}