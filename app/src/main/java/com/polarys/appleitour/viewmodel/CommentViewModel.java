package com.polarys.appleitour.viewmodel;

import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiComment;
import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;

import java.util.ArrayList;

public class CommentViewModel extends ViewModel {

    public CommentViewModel(){}
    ApiComment apiComment = new ApiComment();
    ApiPost apiPost = new ApiPost();

    public ArrayList<Comment> loadComments(int id){
        return loadComments(id,0);
    }

    public ArrayList<Comment> loadComments(int id,int offset){
        ApiResponse apiResponse = apiComment.GetComments(id,offset);
        try {
            return (ArrayList<Comment>) ApiUtil.JsonToArrayObject(new Comment().getClass(), apiResponse.getBody());
        }
        catch(Exception e){
            Log.d("Teste",e.toString());
            return null;
        }
    }
    public ApiResponse CreateComment(Comment comment,String token){
        ApiResponse apiResponse =  apiComment.PostComment(comment,token);
        Log.d("create comment",apiResponse.getBody());
        return apiResponse;
    }
}
