package com.polarys.appleitour.viewmodel;

import android.app.Activity;

import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    private Activity context;
    private String token;

    public BaseViewModel() { }


    public void SetContext(Activity context) {
        this.context = context;
    }
    public Activity GetContext() {
        return context;
    }
    public void SetToken(String token) { this.token = token; }
    public String GetToken() { return token; }
}
