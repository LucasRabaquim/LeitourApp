package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiThread.AUTOLOGIN;
import static com.polarys.appleitour.api.ApiThread.SIGN;
import static com.polarys.appleitour.api.ApiThread.UPDATE;

import com.polarys.appleitour.interfaces.IUser;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;

public class ApiUser implements IUser {
    private static final String PATH = "User";
    public ApiUser(){};
    public ApiResponse Login(User user) {
        ApiThread apiThread = new ApiThread(SIGN, PATH + "/login", user);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse AutoLogin(String token) {
        ApiThread apiThread = new ApiThread(AUTOLOGIN, PATH + "/autologin", null, token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse Register(User user) {
        ApiThread apiThread = new ApiThread(SIGN, PATH + "/register", user);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse LoadUsers(int offset) {
        return null;
    }

    public ApiResponse GetByEmail(String email) {
        return null;
    }

    public ApiResponse GetByName(String name, int offset) {
        return null;
    }

    public ApiResponse UpdateUser(String token, User user) {
        ApiThread apiThread = new ApiThread(UPDATE, PATH + "/alter", user);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse Deactivate(String token) {
        ApiThread apiThread = new ApiThread(UPDATE, PATH + "/deactivate", null, token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}