package com.polarys.appleitour.model;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Comment {
    private int commentId;
    private int userId;
    @JsonIgnore
    private String userName;
    private int postId;
    private String messagePost;
    private Date postDate;
    private Date alteratedDate;

    public Comment() { }
    public Comment(int commentId, int userId, int postId, String messagePost, Date postDate, Date alteratedDate) {
        this.commentId = commentId;
        this.userId = userId;
        this.postId = postId;
        this.messagePost = messagePost;
        this.postDate = postDate;
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

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getMessagePost() {
        return messagePost;
    }

    public void setMessagePost(String messagePost) {
        this.messagePost = messagePost;
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
}
