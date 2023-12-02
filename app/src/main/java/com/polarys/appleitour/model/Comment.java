package com.polarys.appleitour.model;


import com.google.gson.annotations.Expose;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Comment {
    private int commentId;
    private int userId;
  //  @JsonIgnore
    @Expose(serialize = false)
    private String userName ="";
    @Expose(serialize = false)
    private String email = "";
    private int postId;
    private String messagePost;
    //private String postDate = "";
    private String createdDate;
    private String alteratedDate;

    public Comment() { }
    public Comment(int userId, int postId, String messagePost,User user) {
        this.commentId = 0;
        this.userId = userId;
        this.postId = postId;
        this.messagePost = messagePost;
        this.userName = user.GetNameUser();
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
        LocalDateTime datetime = LocalDateTime.parse(createdDate, DateTimeFormatter.ofPattern("dd/MM/yy"));
        return datetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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
