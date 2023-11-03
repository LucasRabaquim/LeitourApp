package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.AUTOLOGIN;
import static com.polarys.appleitour.api.ApiRequest.SIGN;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.interfaces.UserApiInterface;

import java.time.Instant;
import java.time.LocalDateTime;


public class User implements UserApiInterface {
    private int id = 0;
    private String nameUser = "null";
    private String email;
    private String password;
    private String profilePhoto = "";
    private String access = "Comum";
    private LocalDateTime createdLocalDateTime = null;

    private static final String path = "User";

    public User() { }

    public User(int id, String nameUser, String email, String password, String profilePhoto, String access, LocalDateTime createdLocalDateTime) {
        this.id = id;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
        this.profilePhoto = profilePhoto;
        this.access = access;
        this.createdLocalDateTime = createdLocalDateTime;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password) {
        this.nameUser = username;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }


    public ApiResponse Login(){
        User user = new User(this.email,this.password);
        ApiThread apiThread = new ApiThread(SIGN,path+"/login",ObjectToString(user));
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse AutoLogin(String token){
        User user = new User(this.email,this.password);
        ApiThread apiThread = new ApiThread(AUTOLOGIN,path+"/autologin",null,token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse Register(){
        User user = new User(this.nameUser,this.email,this.password);
        ApiThread apiThread = new ApiThread(SIGN,path+"/register",ObjectToString(user));
        return apiThread.CreateThread(apiThread).getJson();
    }
}
