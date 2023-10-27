package com.polarys.appleitour.viewmodel;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;
import com.polarys.appleitour.model.Post;

import java.util.ArrayList;


// TODO: Better explain and understand the porpose of this screen
public class PublicationEditViewModel extends BaseViewModel {


    public ArrayList<Post> loadPosts(){
        ApiResponse apiResponse;
        apiResponse = new Post().GetPosts();
        Toast.makeText(GetContext(), apiResponse.getBody(), Toast.LENGTH_SHORT).show();
        try {
            return ApiUtil.JsonToArrayObject(Post[].class, apiResponse.getBody());
        }
        catch(Exception e){
            Toast.makeText(GetContext(), e.toString(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
    public int CreatePost(Post post){
        ApiResponse apiResponse;
        apiResponse = new Post().PostPost(ApiUtil.ObjectToString(post),GetToken());
        Toast.makeText(GetContext(), apiResponse.getBody(), Toast.LENGTH_SHORT).show();
        try {
            return apiResponse.getCode();
        }
        catch(Exception e){
            Toast.makeText(GetContext(), e.toString(), Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    public int UpdatePost(Post post){
        ApiResponse apiResponse;
        apiResponse = new Post().UpdatePost(post.getId(),ApiUtil.ObjectToString(post),GetToken());
        Toast.makeText(GetContext(), apiResponse.getBody(), Toast.LENGTH_SHORT).show();
        try {
            return apiResponse.getCode();
        }
        catch(Exception e){
            Toast.makeText(GetContext(), e.toString(), Toast.LENGTH_SHORT).show();
            return -1;
        }
    }

    public ArrayList<Comment> loadComments(int id){
        ApiResponse apiResponse;
        apiResponse = new Comment().GetComments(id);
        Toast.makeText(GetContext(), apiResponse.getBody(), Toast.LENGTH_SHORT).show();
        try {
            return ApiUtil.JsonToArrayObject(Comment[].class, apiResponse.getBody());
        }
        catch(Exception e){
            Toast.makeText(GetContext(), e.toString(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
