package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;


public interface ISavedBook {
    ApiResponse SaveBook(BookApi book, int userId, String token);
    ApiResponse UnsaveBook(int id,String token);
    ApiResponse GetSavedBookByEmail(String email, int offset);
    ApiResponse GetSavedBooks(String token);
    ApiResponse GetSavedBook(String key, String token);
}
