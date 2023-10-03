package com.polarys.appleitour.model;

import java.util.Date;

public class User {
    private int id;
    private String nameUser;
    private String email;
    private String password;
    private String profilePhoto;
    private String access;
    private Date createdDate;

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
}
