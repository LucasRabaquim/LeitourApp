package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiThread.UPDATE;

import com.polarys.appleitour.interfaces.IComment;
import com.polarys.appleitour.model.ApiResponse;

public class ApiComment implements IComment {

    public ApiComment() {};

    public ApiResponse GetComments(int id){
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, "Posts/Comment/"+id);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse PostComment(Object object, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(POST, "Comment", object,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse UpdateComment(int id,Object object, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(UPDATE, "Comment/"+id, object,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse DeleteComment(int id,Object object, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(DELETE, "Comment/"+id, object,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
