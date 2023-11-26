package com.polarys.appleitour.model;


import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import android.app.Activity;
import android.content.Context;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.SharedHelper;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import kotlin.jvm.Transient;

public class Annotation implements Serializable {

    private int annotationId;
    private int savedBookId;
    private String annotationText;
    private String createdDate;
    private String alteratedDate;

    public Annotation() {}

    public Annotation(int savedBookId, String annotationText) {
        this.annotationId = 0;
        this.savedBookId = savedBookId;
        this.annotationText = annotationText;
        this.createdDate = LocalDateTime.now()+"Z";
        this.alteratedDate = LocalDateTime.now()+"Z";
    }

    public Annotation(int annotationId, int savedBookId, String annotationText, LocalDateTime createdDate, LocalDateTime alteratedDate) {
        this.annotationId = annotationId;
        this.savedBookId = savedBookId;
        this.annotationText = annotationText;

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

}