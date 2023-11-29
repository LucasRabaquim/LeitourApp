package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiThread.UPDATE;

import com.polarys.appleitour.interfaces.IComment;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;

public class ApiComment implements IComment {

    public ApiComment() {};

    public ApiResponse GetComments(int id){
        return GetComments(id,0);
    }
    public ApiResponse GetComments(int id,int offset){
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, "Posts/Comment/"+id+"?offset="+offset);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse PostComment(Comment comment, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(POST, "Comment", comment,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse UpdateComment(Comment comment, String token) {
        ApiThread apiThread = new ApiThread(UPDATE, "Posts/Comment/"+comment.getCommentId(), comment,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse DeleteComment(Comment comment, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(DELETE, "Posts/Comment/"+comment.getCommentId(), comment,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
