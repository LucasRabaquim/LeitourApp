package com.polarys.appleitour.viewmodel;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.api.ApiUser;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;

import java.util.ArrayList;

public class UserDataViewModel extends ViewModel {

    ApiUser apiPost = new ApiUser();

    public ArrayList<User> loadFollowing(String email, int offset){
        ApiResponse apiResponse = apiPost.GetFollowing(email,offset);
        return (ArrayList<User>) ApiUtil.JsonToArrayObject(new User().getClass(), apiResponse.getBody());
    }
    public ArrayList<User> loadFollowers(String email, int offset){
        ApiResponse apiResponse = apiPost.GetFollowers(email,offset);
        return (ArrayList<User>) ApiUtil.JsonToArrayObject(new User().getClass(), apiResponse.getBody());
    }
}