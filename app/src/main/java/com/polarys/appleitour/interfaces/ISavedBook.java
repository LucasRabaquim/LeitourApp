package com.polarys.appleitour.interfaces;

import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.SavedBook;

public interface ISavedBook {
    ApiResponse SaveBook(BookApi book, int userId, String token);
    ApiResponse UnsaveBook(int id,String token);
    ApiResponse GetSavedBooks(String token);
    ApiResponse GetSavedBook(String key, String token);
}
