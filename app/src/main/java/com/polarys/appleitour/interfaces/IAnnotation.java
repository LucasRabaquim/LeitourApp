package com.polarys.appleitour.interfaces;

import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;

public interface IAnnotation {

    ApiResponse GetAnnotations(int id, String token);

    ApiResponse CreateAnnotation(Annotation annotation, String token);
    ApiResponse UpdateAnnotation(Annotation annotation, String token);
    ApiResponse DeleteAnnotation(Annotation annotation,String token);
}
