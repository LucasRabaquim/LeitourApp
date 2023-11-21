package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiThread.UPDATE;

import com.polarys.appleitour.interfaces.IPost;
import com.polarys.appleitour.model.ApiResponse;

public class ApiPost implements IPost {

    private String PATH = "Posts";
    private final String QUERY = "?q=";

    public ApiResponse GetPosts() {
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, PATH, null);
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

    public ApiResponse GetPosts(int offset) {
        return null;
    }


    public ApiResponse GetPostsByEmail(String email, int offset) {
        return null;
    }
}
