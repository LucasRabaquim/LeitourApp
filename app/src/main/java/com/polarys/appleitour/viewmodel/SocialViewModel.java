package com.polarys.appleitour.viewmodel;

import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.ViewHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;

import java.util.ArrayList;

public class SocialViewModel extends ViewModel {
    private Activity context;

    public SocialViewModel() { }
    ApiPost apiPost = new ApiPost();

    public ArrayList<Post> loadPosts(){
        ApiResponse apiResponse;
        apiResponse = apiPost.GetPosts();
        Log.d("Teste",apiResponse.getBody());
       try {
            return (ArrayList<Post>) ApiUtil.JsonToArrayObject(new Post().getClass(), apiResponse.getBody());
        }
        catch(Exception e){
            return null;
        }
    }
}