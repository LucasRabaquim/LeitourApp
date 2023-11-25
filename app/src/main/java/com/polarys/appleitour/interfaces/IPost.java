package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;

public interface IPost {

    ApiResponse GetPosts(int offset);
    ApiResponse GetPostsByEmail(String email);
    ApiResponse CreatePost(Object object, String token);
    ApiResponse UpdatePost(int id, Object object, String token);
    ApiResponse DeletePost(int id, Object object, String token);
    ApiResponse Like(int id, String token);
}
