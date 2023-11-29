package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Comment;

public interface IComment {
    ApiResponse GetComments(int id,int offset);
    ApiResponse PostComment(Comment comment, String token);
    ApiResponse UpdateComment(Comment comment, String token);
    ApiResponse DeleteComment(Comment comment, String token);
}
