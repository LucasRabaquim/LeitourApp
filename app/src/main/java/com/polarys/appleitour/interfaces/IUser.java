package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;

public interface IUser {
    ApiResponse Login(User user);
    ApiResponse AutoLogin(String token);
    ApiResponse Register(User user);
    ApiResponse LoadUsers(int offset);
    ApiResponse GetByEmail(String email);
    ApiResponse GetByName(String name,int offset);
    ApiResponse UpdateUser(String token, User user);
    ApiResponse Deactivate(String token);
    ApiResponse GetFollowing(String email,int offset);
    ApiResponse GetFollowers(String email,int offset);
}
