package com.polarys.appleitour.interfaces;

import static com.polarys.appleitour.api.ApiRequest.GET;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.model.ApiResponse;

public interface AnnotationInterface {
    ApiResponse GetAnnotations(int id, String token);
}
