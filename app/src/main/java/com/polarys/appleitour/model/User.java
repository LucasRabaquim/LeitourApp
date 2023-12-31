package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiThread.AUTOLOGIN;
import static com.polarys.appleitour.api.ApiThread.SIGN;
import static com.polarys.appleitour.api.ApiThread.UPDATE;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.annotations.SerializedName;
import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.interfaces.IUser;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.time.LocalDateTime;


public class User implements Serializable {


    private int id = 0;

    private String nameUser = "";

    private String email;

    private String password;

    private String profilePhoto;

    private String bioUser = "";
    private String access = "Comum";

    private LocalDateTime createdLocalDateTime = null;



    public User() {
    }

    public User(int _id, String _nameUser, String _email, String _password, String _profilePhoto, String _access, LocalDateTime _createdLocalDateTime) {
        id = _id;
        nameUser = _nameUser;
        email = _email;
        password = _password;
        profilePhoto = _profilePhoto;
        access = _access;
        createdLocalDateTime = _createdLocalDateTime;
    }

    public User(String _email, String _password) {
        nameUser = "@";
        email = _email;
        password = _password;
      //  profilePhoto = "https://upload";
    }

    public User(String _username, String _email, String _password) {
        nameUser = _username;
        email = _email;
        password = _password;
       // profilePhoto = ;
    }

    public int GetId() {
        return id;
    }

    public void SetId(int _id) {
        id = _id;
    }

    public String GetNameUser() {
        return nameUser;
    }

    public void SetNameUser(String _nameUser) {
        nameUser = _nameUser;
    }

    public String GetEmail() {
        return email;
    }

    public void SetEmail(String _email) {
        email = _email;
    }

    public String GetPassword() {
        return password;
    }

    public void SetPassword(String _password) {
        password = _password;
    }

    public String GetStringProfilePhoto() {return profilePhoto;}

    public void SetProfilePhoto(Bitmap _profilePhoto) {
        ByteArrayOutputStream outputStream = new  ByteArrayOutputStream();
        _profilePhoto.compress(Bitmap.CompressFormat.PNG,100, outputStream);
        byte [] byteImg = outputStream.toByteArray();
        profilePhoto = Base64.getEncoder().encodeToString(byteImg);
    }

    public String GetAccess() {
        return access;
    }

    public void SetAccess(String _access) {
        access = _access;
    }
}
