package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiThread.UPDATE;

import com.polarys.appleitour.interfaces.IAnnotation;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;

public class ApiAnnotation implements IAnnotation {

    public ApiResponse GetAnnotations(int id, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(GET, "savedBook/Annotation/" + id, null, token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse CreateAnnotation(Annotation annotation, String token) {
        ApiThread apiThread = new ApiThread(POST, "/Annotations/api/savedBook/" + annotation.getSavedBookId(), annotation, token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    @Override
    public ApiResponse UpdateAnnotation(Annotation annotation, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(UPDATE, "/Annotation/" + annotation.getAnnotationId(), annotation, token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse DeleteAnnotation(Annotation annotation, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(DELETE, "/Annotation/" + annotation.getAnnotationId(), annotation, token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
