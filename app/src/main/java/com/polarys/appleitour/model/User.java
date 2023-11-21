package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiThread.AUTOLOGIN;
import static com.polarys.appleitour.api.ApiThread.SIGN;
import static com.polarys.appleitour.api.ApiThread.UPDATE;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import com.google.gson.annotations.SerializedName;
import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.interfaces.IUser;

import java.io.Serializable;
import java.time.LocalDateTime;


public class User implements Serializable {

    @SerializedName("id")
    private int id = 0;
    @SerializedName("nameUser")
    private String nameUser = "null";
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("profilePhoto")
    private String profilePhoto = "";
    @SerializedName("access")
    private String access = "Comum";
    @SerializedName("createdLocalDateTime")
    private LocalDateTime createdLocalDateTime = null;



    public User() {
    }

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

    public int GetId() {
        return id;
    }

    public void SetId(int id) {
        this.id = id;
    }

    public String GetNameUser() {
        return nameUser;
    }

    public void SetNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String GetEmail() {
        return email;
    }

    public void SetEmail(String email) {
        this.email = email;
    }

    public String GetPassword() {
        return password;
    }

    public void SetPassword(String password) {
        this.password = password;
    }

    public String GetProfilePhoto() {
        return profilePhoto;
    }

    public void SetProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String GetAccess() {
        return access;
    }

    public void SetAccess(String access) {
        this.access = access;
    }
}
