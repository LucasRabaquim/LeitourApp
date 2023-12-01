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
    private final String QUERY = "?offset=";

    public ApiResponse GetPosts(int offset) {
        ApiThread apiThread = new ApiThread(GETPUBLIC, PATH+QUERY+offset, null);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse GetPosts(String token,int offset) {
        ApiThread apiThread = new ApiThread(GET, PATH+QUERY+offset, null,token);
        return apiThread.CreateThread(apiThread).getJson();
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

    public ApiResponse GetPostsByEmail(String token,String email) {
        return GetPostsByEmail(token,email,0);
    }
    public ApiResponse GetPostsByEmail(String email) { return GetPostsByEmail(email,0);
    }
    public ApiResponse GetPostsByEmail(String email,int offset) {
        ApiThread apiThread = new ApiThread(GETPUBLIC, PATH+"/"+email+QUERY+offset, null);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse GetPostsByEmail(String token,String email,int offset) {
        ApiThread apiThread = new ApiThread(GET, PATH+"/"+email+QUERY+offset, null,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
