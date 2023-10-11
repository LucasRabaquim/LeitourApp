package com.polarys.appleitour.model;

import static com.polarys.appleitour.api.ApiRequest.GET;

import android.app.Activity;
import android.content.Context;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.helper.SharedHelper;

import java.util.Date;

import kotlin.jvm.Transient;

public class Annotation {
    private int annotationId;
    private int savedBookId;
    private String annotationText;
    private Date createdDate;
    private Date alteratedDate;

    public Annotation() {}

    public Annotation(int annotationId, int savedBookId, String annotationText, Date createdDate, Date alteratedDate) {
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getAlteratedDate() {
        return alteratedDate;
    }

    public void setAlteratedDate(Date alteratedDate) {
        this.alteratedDate = alteratedDate;
    }

    public ApiResponse getAnnotations(int id,String token){
        ApiThread apiThread;
        apiThread = new ApiThread(GET, "savedBook/Annotation/"+id,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}