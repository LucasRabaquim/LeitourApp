package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.GETPUBLIC;


//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.databind.ObjectMapper;
import com.polarys.appleitour.api.ApiThread;

public class Comment {
    private int commentId;
    private int userId;
  //  @JsonIgnore
    private String userName;
    private int postId;
    private String messagePost;
    private String postDate;
    private String alteratedDate;

    public Comment() { }
    public Comment(int commentId, int userId, int postId, String messagePost, String postDate, String alteratedDate) {
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

    public ApiResponse GetComments(int id){
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, "Posts/Comment/"+id);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
