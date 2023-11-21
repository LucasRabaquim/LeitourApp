package com.polarys.appleitour.viewmodel;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiAnnotation;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;

public class AnnotationViewModel extends ViewModel{
    public AnnotationViewModel(){}
    ApiAnnotation apiAnnotation = new ApiAnnotation();

    public ApiResponse CreateAnnotation(Annotation annotation, String token){
        return apiAnnotation.CreateAnnotation(annotation,token);
    }

    public ApiResponse UpdateAnnotation(Annotation annotation, String token){
        return apiAnnotation.UpdateAnnotation(annotation,token);
    }
    public ApiResponse DeleteAnnotation(Annotation annotation,String token) {
        return apiAnnotation.DeleteAnnotation(annotation,token);
    }
}
