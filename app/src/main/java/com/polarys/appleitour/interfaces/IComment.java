package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.model.ApiResponse;

public interface IComment {
    ApiResponse GetComments(int id);
    ApiResponse PostComment(Object object, String token);
    ApiResponse UpdateComment(int id,Object object, String token);
    ApiResponse DeleteComment(int id,Object object, String token);
}
