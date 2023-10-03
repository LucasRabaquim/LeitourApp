package com.polarys.appleitour.model;

import java.util.Date;

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
}