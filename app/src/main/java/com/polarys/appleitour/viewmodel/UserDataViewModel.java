package com.polarys.appleitour.viewmodel;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;

import java.util.ArrayList;

public class UserDataViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    ApiPost apiPost = new ApiPost();

    public ArrayList<User> loadFollowing(String email, int offset){
        ApiResponse apiResponse = apiPost.GetPosts(offset);
        return (ArrayList<User>) ApiUtil.JsonToArrayObject(new User().getClass(), apiResponse.getBody());
    }
}