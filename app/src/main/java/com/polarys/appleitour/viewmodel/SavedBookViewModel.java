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

    public SavedBookViewModel(){};
    public ArrayList<SavedBook> loadSaved(String token){
        ApiResponse apiResponse;
        apiResponse = new SavedBook().GetSavedBooks(token);
        try {
            return (ArrayList<SavedBook>) ApiUtil.JsonToArrayObject(new SavedBook().getClass(), apiResponse.getBody());
        }
        catch(Exception e){
            return null;
        }
    }
}