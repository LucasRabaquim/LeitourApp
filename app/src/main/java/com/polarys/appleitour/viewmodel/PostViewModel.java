package com.polarys.appleitour.viewmodel;

import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Post;

public class PostViewModel extends ViewModel {
    public PostViewModel(){}
    ApiPost apiPost = new ApiPost();

    public String CreatePost(Post post, String token){
        ApiResponse response = apiPost.CreatePost(post, token);
        if (response.getCode() == 200 | response.getCode() == 201)
            return "Post Criado";
        return "Não foi possível criar o Post";
    }
    public String UpdatePost(Post post, String token){
        ApiResponse response = apiPost.UpdatePost(post.GetId(),post,token);
        if (response.getCode() == 200 | response.getCode() == 201)
            return "Post Alterado";
        return "Não foi possível alterar o Post";
    }
    public String DeletePost(Post post, String token){
        ApiResponse response = apiPost.UpdatePost(post.GetId(),post,token);
        if (response.getCode() == 200 | response.getCode() == 201)
            return "Post Deletado";
        return "Não foi possível alterar o Post";
    }
}
