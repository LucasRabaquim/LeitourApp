package com.polarys.appleitour.viewmodel;


import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Annotation;

import java.util.ArrayList;

public class BookInfoViewModel extends ViewModel{
    public ArrayList<Annotation> annotations;

    public BookInfoViewModel() {}

    public ArrayList<Annotation> showAnnotations(int id, String token){
        ApiResponse apiResponse = new Annotation().getAnnotations(id,token);
        return ApiUtil.JsonToArrayObject(Annotation[].class, apiResponse.getBody());
    }
}
