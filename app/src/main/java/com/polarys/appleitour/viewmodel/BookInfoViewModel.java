package com.polarys.appleitour.viewmodel;


import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Annotation;

import java.util.ArrayList;

public class BookInfoViewModel extends ViewModel {
    public ArrayList<Annotation> annotations;
    private Activity context;
    private String token;

    public BookInfoViewModel() {
    }

    public void setContext(Activity context) {
        this.context = context;
        token = new SharedHelper(context).GetToken();
    }

    public ArrayList<Annotation> showAnnotations(int id){
        ApiResponse apiResponse = new Annotation().getAnnotations(id,token);

       /* if (apiResponse.getCode() != 200) {
            Toast.makeText(context, apiResponse.getBody(), Toast.LENGTH_SHORT).show();
            return null;
        }*/
        Toast.makeText(context, apiResponse.getBody(), Toast.LENGTH_SHORT).show();

        return ApiUtil.JsonToArrayObject(Annotation[].class, apiResponse.getBody());
    }
}
