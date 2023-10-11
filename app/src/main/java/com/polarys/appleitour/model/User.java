package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.SIGN;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.interfaces.userApi;

import java.io.Serializable;
import java.util.Date;

public class User implements userApi {
    private int id = 0;
    private String nameUser = "null";
    private String email;
    private String password;
    private String profilePhoto = "";
    private String access = "Comum";
    private Date createdDate = null;

    private static final String path = "User";

    public User() { }

    public User(int id, String nameUser, String email, String password, String profilePhoto, String access, Date createdDate) {
        this.id = id;
        this.nameUser = nameUser;
        this.email = email;
        this.password = password;
        this.profilePhoto = profilePhoto;
        this.access = access;
        this.createdDate = createdDate;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public ApiResponse Login(){
        User user = new User(this.email,this.password);
        ApiThread apiThread = new ApiThread(SIGN,path+"/login",ObjectToString(user));
        Thread thread = new Thread(apiThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return apiThread.getJson();
    }
    public ApiResponse Register(){
        User user = new User(this.nameUser,this.email,this.password);
        ApiThread apiThread = new ApiThread(SIGN,path+"/register",ObjectToString(user));
        Thread thread = new Thread(apiThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return apiThread.getJson();
    }
}
