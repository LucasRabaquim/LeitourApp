package com.polarys.appleitour.viewmodel;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;

import java.util.ArrayList;

public class SocialViewModel extends ViewModel {
    private Activity context;

    public SocialViewModel() {
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public ArrayList<Post> loadPosts(){
        ApiResponse apiResponse;
        apiResponse = new Post().GetPosts();
        try {
            return ApiUtil.JsonToArrayObject(Post[].class, apiResponse.getBody());
        }
        catch(Exception e){
            return null;
        }
    }

    public ArrayList<Comment> loadComments(int id){
        ApiResponse apiResponse;
        apiResponse = new Comment().GetComments(id);
        try {
            return ApiUtil.JsonToArrayObject(Comment[].class, apiResponse.getBody());
        }
        catch(Exception e){
            return null;
        }
    }
}