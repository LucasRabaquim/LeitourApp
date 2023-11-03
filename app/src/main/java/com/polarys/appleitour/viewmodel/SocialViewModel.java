package com.polarys.appleitour.viewmodel;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;

import java.util.ArrayList;

public class SocialViewModel extends ViewModel {
    private Activity context;

    public SocialViewModel() { }

    public ArrayList<Post> loadPosts(){
        ApiResponse apiResponse;
        apiResponse = new Post().GetPosts();
        Log.d("Teste",apiResponse.getBody());
        return (ArrayList<Post>) ApiUtil.JsonToArrayObject(new Post().getClass(), apiResponse.getBody());
    /*    try {
            return ApiUtil.JsonToArrayObject(Post[].class, apiResponse.getBody());
        }
        catch(Exception e){
            return null;
        }*/
    }

    public ArrayList<Comment> loadComments(int id){
        ApiResponse apiResponse;
        apiResponse = new Comment().GetComments(id);
        Log.d("EI",apiResponse.getBody());
        try {
            return (ArrayList<Comment>) ApiUtil.JsonToArrayObject(new Comment().getClass(), apiResponse.getBody());
        }
        catch(Exception e){
            return null;
        }
    }
}