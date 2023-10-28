package com.polarys.appleitour.viewmodel;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {
    private String token;
    public BaseViewModel() { }
    public void SetToken(String token) { this.token = token; }
    public String GetToken() { return token; }
}
