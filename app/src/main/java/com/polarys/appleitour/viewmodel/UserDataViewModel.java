package com.polarys.appleitour.viewmodel;

import androidx.lifecycle.ViewModel;

public class UserDataViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public ArrayList<User> loadFollowing(String email,int offset){
        ApiResponse apiResponse = apiPost.GetPosts(offset);
        return (ArrayList<User>) ApiUtil.JsonToArrayObject(new User().getClass(), apiResponse.getBody());
    }
}