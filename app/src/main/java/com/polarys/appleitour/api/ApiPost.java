package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiRequest.DELETE;
import static com.polarys.appleitour.api.ApiRequest.GET;
import static com.polarys.appleitour.api.ApiRequest.GETPUBLIC;
import static com.polarys.appleitour.api.ApiRequest.POST;
import static com.polarys.appleitour.api.ApiRequest.UPDATE;

import com.polarys.appleitour.interfaces.IPost;
import com.polarys.appleitour.model.ApiResponse;

public class ApiPost implements IPost {

    private String PATH = "Posts";
    private final String QUERY = "?q=";

    public ApiResponse GetPosts(int offset) {
        ApiThread apiThread = new ApiThread(GETPUBLIC, PATH, null);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse CreatePost(Object object, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(POST, PATH, object,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse UpdatePost(int id,Object object, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(UPDATE, PATH+"/"+id, object,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse DeletePost(int id,Object object, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(DELETE, PATH+"/"+id, object,token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse Like(int id, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(GET, PATH+"/like/"+id,token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse GetPostsByEmail(String email,int offset) {
        ApiThread apiThread = new ApiThread(GETPUBLIC, PATH+"/"+email, null);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
