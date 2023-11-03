package com.polarys.appleitour.viewmodel;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;

public class AnnotationViewModel extends ViewModel{
    public AnnotationViewModel(){}

    public boolean addAnnotation(Annotation annotation, String token){
        ApiResponse success = new Annotation().createAnnotation(annotation,token);
        return success.getCode() == 200;
    }

    public boolean updateAnnotation(Annotation annotation, String token){
        ApiResponse success = new Annotation().updateAnnotation(annotation,token);
        return success.getCode() == 200;
    }
}
