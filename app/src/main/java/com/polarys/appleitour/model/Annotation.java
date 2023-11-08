package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.GET;
import static com.polarys.appleitour.api.ApiRequest.POST;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import android.app.Activity;
import android.content.Context;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.helper.SharedHelper;

import java.time.LocalDateTime;
import java.util.Date;

import kotlin.jvm.Transient;

public class Annotation {
    private int annotationId;
    private int savedBookId;
    private String annotationText;
    private LocalDateTime createdDate = null;
    private LocalDateTime alteratedDate = null;

    public Annotation() {}

    public Annotation(int savedBookId, String annotationText) {
        this.annotationId = 0;
        this.savedBookId = savedBookId;
        this.annotationText = annotationText;
    }
    public Annotation(int annotationId, int savedBookId, String annotationText, LocalDateTime createdDate, LocalDateTime alteratedDate) {
        this.annotationId = annotationId;
        this.savedBookId = savedBookId;
        this.annotationText = annotationText;
        this.createdDate = createdDate;
        this.alteratedDate = alteratedDate;
    }

    public int getAnnotationId() {
        return annotationId;
    }

    public void setAnnotationId(int annotationId) {
        this.annotationId = annotationId;
    }

    public int getSavedBookId() {
        return savedBookId;
    }

    public void setSavedBookId(int savedBookId) {
        this.savedBookId = savedBookId;
    }

    public String getAnnotationText() {
        return annotationText;
    }

    public void setAnnotationText(String annotationText) {
        this.annotationText = annotationText;
    }

    public ApiResponse getAnnotations(int id,String token){
        ApiThread apiThread;
        apiThread = new ApiThread(GET, "savedBook/Annotation/"+id,token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse createAnnotation(Annotation annotation,String token){
        ApiThread apiThread;
        apiThread = new ApiThread(POST, "api/savedBook/"+annotation.getSavedBookId(),ObjectToString(annotation),token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse updateAnnotation(Annotation annotation,String token){
        ApiThread apiThread;
        apiThread = new ApiThread(POST, "api/Annotation/"+annotation.getAnnotationId(),ObjectToString(annotation),token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}