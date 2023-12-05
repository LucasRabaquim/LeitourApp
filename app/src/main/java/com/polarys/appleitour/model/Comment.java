package com.polarys.appleitour.model;


import android.util.Log;

import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;

public class Comment {
    private int commentId;
    private int userId;
  //  @JsonIgnore
    private String userName;
    private String email;
    private int postId;
    private String messagePost;
    private String createdDate;
    private String alteratedDate;

    public Comment() { }
    public Comment(int userId, int postId, String messagePost,User user) {
        this.commentId = 0;
        this.userId = userId;
        this.postId = postId;
        this.messagePost = messagePost;
        this.userName = user.GetNameUser();
        this.email = user.GetEmail();
    }
    public Comment(int commentId, int userId, int postId, String messagePost, String postDate, String alteratedDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.messagePost = messagePost;
        this.createdDate = postDate;
        this.alteratedDate = alteratedDate;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPostId() {
        return postId;
    }
    public String getUserName() {
        return userName;
    }
    public String getEmail() {
        return email;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getMessagePost() {
        return messagePost;
    }

    public void setMessagePost(String messagePost) {
        this.messagePost = messagePost;
    }

    public String getCreatedDate() {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(createdDate);
            DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return dateTime.format(formatters);
        }
        catch(Exception e){
            return  createdDate;
        }
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getAlteratedDate() {
        return alteratedDate;
    }

    public void setAlteratedDate(String alteratedDate) {
        this.alteratedDate = alteratedDate;
    }


}
