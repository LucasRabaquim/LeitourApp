package com.polarys.appleitour.viewmodel;

import android.app.Activity;
import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Post;

import java.util.ArrayList;

public class SocialViewModel extends ViewModel {
    private Activity context;

    public SocialViewModel() { }
    ApiPost apiPost = new ApiPost();

    public ArrayList<Post> loadPosts(int offset){
        ApiResponse apiResponse = apiPost.GetPosts(offset);
        Log.d("Teste",apiResponse.getBody());
        return (ArrayList<Post>) ApiUtil.JsonToArrayObject(new Post().getClass(), apiResponse.getBody());
    }
    public ArrayList<Post> loadPostsByEmail(String email, int offset){
        ApiResponse apiResponse = apiPost.GetPostsByEmail(email,offset);
        Log.d("Teste",apiResponse.getBody());
        return (ArrayList<Post>) ApiUtil.JsonToArrayObject(new Post().getClass(), apiResponse.getBody());
    }
}