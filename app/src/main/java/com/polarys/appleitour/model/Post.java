package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.GET;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.polarys.appleitour.api.ApiThread;

public class Post {
    private int id;
    private int userId;
    @JsonIgnore
    private String userName;
    private String messagePost;
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
        apiThread = new ApiThread(GET, "Post/", null);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
