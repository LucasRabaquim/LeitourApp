package com.polarys.appleitour.viewmodel;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Post;
import com.polarys.appleitour.model.SavedBook;

import java.util.ArrayList;

public class SavedBookViewModel extends ViewModel {
    private Activity context;
    private String token;

    public SavedBookViewModel() {}

    public void setContext(Activity context,String token) {
        this.context = context;
        this.token = token;
    }
}