package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.DELETE;
import static com.polarys.appleitour.api.ApiRequest.GET;
import static com.polarys.appleitour.api.ApiRequest.POST;
import static com.polarys.appleitour.api.ApiRequest.GETPUBLIC;
import static com.polarys.appleitour.api.ApiRequest.UPDATE;

import java.io.Serializable;
import java.time.LocalDateTime;
//import com.fasterxml.jackson.annotation.JsonProperty;
import com.polarys.appleitour.api.ApiThread;

public class Post implements Serializable {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }
   /*public String getUserName() {
        return userName;
    }*/

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessagePost() {
        return messagePost;
    }
    public String getUserName() {
        return userName;
    }
    public int getLikes() {
        return likes;
    }

    public void setMessagePost(String messagePost) {
        this.messagePost = messagePost;
    }

    /*public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }*/

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getAlteratedDate() {
        return alteratedDate;
    }

    public void setAlteratedDate(String alteratedDate) {
        this.alteratedDate = alteratedDate;
    }


    public ApiResponse GetPosts() {
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, "Posts", null);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse PostPost(String json, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(POST, "Posts", json,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse UpdatePost(int id,String json, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(UPDATE, "Posts/"+id, json,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse DeletePost(int id,String json, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(DELETE, "Posts/"+id, json,token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse like(int id, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(GET, "Posts/like/"+id,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
