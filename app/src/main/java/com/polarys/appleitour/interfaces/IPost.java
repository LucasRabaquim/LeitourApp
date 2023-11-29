package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Post;

public interface IPost {

    ApiResponse GetPosts(int offset);
    ApiResponse GetPosts(String token,int offset);
    ApiResponse GetPostsByEmail(String email);
    ApiResponse GetPostsByEmail(String token,String email);
    ApiResponse CreatePost(Post object, String token);
    ApiResponse UpdatePost(Post object, String token);
    ApiResponse DeletePost(Post object, String token);
    ApiResponse Like(int id, String token);
}
