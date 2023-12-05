package com.polarys.appleitour.viewmodel;

import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Post;

public class PostViewModel extends ViewModel {
    public PostViewModel(){}
    ApiPost apiPost = new ApiPost();

    public String[] CreatePost(Post post, String token){
        ApiResponse response = apiPost.CreatePost(post, token);
        if (response.getCode() == 200 | response.getCode() == 201)
            return new String[]{"Post Criado","SUCCESS"};
        return new String[]{response.getBody(),"ERROR"};
    }
    public String[] UpdatePost(Post post, String token){
        ApiResponse response = apiPost.UpdatePost(post,token);
        if (response.getCode() == 200 | response.getCode() == 201)
            return new String[]{"Post Criado","SUCCESS"};
        return new String[]{response.getBody(),"ERROR"};
    }
    public String DeletePost(Post post, String token){
        ApiResponse response = apiPost.UpdatePost(post,token);
        if (response.getCode() == 200 | response.getCode() == 201)
            return "Post Deletado";
        return response.getBody();
    }
}
