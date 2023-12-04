package com.polarys.appleitour.viewmodel;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiPost;
import com.polarys.appleitour.api.ApiUser;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class UserDataViewModel extends ViewModel {

    ApiUser apiUser = new ApiUser();

    public ArrayList<User> loadFollowing(String email, int offset){
        ApiResponse apiResponse = apiUser.GetFollowing(email,offset);
        return (ArrayList<User>) ApiUtil.JsonToArrayObject(new User().getClass(), apiResponse.getBody());
    }
    public ArrayList<User> loadFollowers(String email, int offset){
        ApiResponse apiResponse = apiUser.GetFollowers(email,offset);
        return (ArrayList<User>) ApiUtil.JsonToArrayObject(new User().getClass(), apiResponse.getBody());
    }
    public int[] getStatistics(String email){
        try {
            ApiResponse apiResponse = apiUser.getStatistics(email);
            JSONArray array = new JSONArray(apiResponse.getBody());
            int[] statistics = new int[4];
            for (int i = 0; i < 4; ++i)
                statistics[i] = array.optInt(i);
            return statistics;
        }
        catch (Exception e){
            return new int[]{0,0,0,0};
        }
    }
}