package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.DELETE;
import static com.polarys.appleitour.api.ApiRequest.GET;
import static com.polarys.appleitour.api.ApiRequest.POST;
import static com.polarys.appleitour.api.ApiRequest.GETPUBLIC;
import static com.polarys.appleitour.api.ApiRequest.UPDATE;

import java.util.Date;
//import com.fasterxml.jackson.annotation.JsonProperty;
import com.polarys.appleitour.api.ApiThread;

public class Post {
    private int id;
    private int userId;
    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userName;
    private String messagePost;
   // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int likes;
    private Date postDate;
    private Date alteratedDate;

    public Post() { }

    public Post(int id, int userId, String messagePost, int likes, Date postDate, Date alteratedDate) {
        this.id = id;
        this.userId = userId;
        this.messagePost = messagePost;
        this.likes = likes;
        this.postDate = postDate;
        this.alteratedDate = alteratedDate;
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
    public String getUserName() {
        return userName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessagePost() {
        return messagePost;
    }

    public void setMessagePost(String messagePost) {
        this.messagePost = messagePost;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public Date getAlteratedDate() {
        return alteratedDate;
    }

    public void setAlteratedDate(Date alteratedDate) {
        this.alteratedDate = alteratedDate;
    }


    public ApiResponse GetPosts() {
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, "Post", null);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse PostPost(String json, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(POST, "Post", json,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse UpdatePost(int id,String json, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(UPDATE, "Post/"+id, json,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse DeletePost(int id,String json, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(DELETE, "Post/"+id, json,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
