package com.polarys.appleitour.viewmodel;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;

import java.util.ArrayList;

public class SavedBookInfoViewModel extends ViewModel {



    public SavedBookInfoViewModel(){}

    public ArrayList<Annotation> showAnnotations(int id, String token){
        ApiResponse apiResponse = new Annotation().getAnnotations(id,token);
        return (ArrayList<Annotation>) ApiUtil.JsonToArrayObject(Annotation.class, apiResponse.getBody());
    }

}
