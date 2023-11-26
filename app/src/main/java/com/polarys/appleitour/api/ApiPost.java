package com.polarys.appleitour.api;


import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiThread.UPDATE;

import com.polarys.appleitour.interfaces.IPost;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Post;

public class ApiPost implements IPost {

    private String PATH = "Posts";
    private final String QUERY = "?q=";

    public ApiResponse GetPosts(int offset) {
        ApiThread apiThread = new ApiThread(GETPUBLIC, PATH, null);
        return apiThread.CreateThread(apiThread).getJson();
    }

    @Override
    public ApiResponse GetPostsByEmail(String email) {
        return null;
    }


    public ApiResponse CreatePost(Post post, String token) {
        ApiThread apiThread = new ApiThread(POST, PATH, post,token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse UpdatePost(Post post, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(UPDATE, PATH+"/"+post.GetId(), post,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse DeletePost(Post post, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(DELETE, PATH+"/"+post.GetId(), post,token);
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
