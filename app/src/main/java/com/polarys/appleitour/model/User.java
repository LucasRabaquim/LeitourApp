package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiUtil.GET;
import static com.polarys.appleitour.api.ApiUtil.POST;
import static com.polarys.appleitour.api.ApiUtil.SIGN;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.interfaces.userApi;

import java.io.Serializable;
import java.util.Date;

public class User implements userApi, Serializable {
    private int id;
    private String nameUser;
    private String email;
    private String password;
    private String profilePhoto;
    private String access;
    private Date createdDate;

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
        this.id = 0;
        this.nameUser = "d";
        this.email = email;
        this.password = password;
        this.profilePhoto = "";
        this.access = "Comum";
        this.createdDate = null;
    }

    public User(String username, String email, String password) {
        this.id = 0;
        this.nameUser = username;
        this.email = email;
        this.password = password;
        this.profilePhoto = "";
        this.access = "Comum";
        this.createdDate = null;
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
    public String Login(String user){
        ApiThread apiThread = new ApiThread(SIGN,path+"/login",user);
        Thread thread = new Thread(apiThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return apiThread.getJson();
    }
    public String Register(String user){
        ApiThread apiThread = new ApiThread(SIGN,path+"/register",user);
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
