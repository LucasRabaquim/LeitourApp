package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import com.polarys.appleitour.interfaces.ISavedBook;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.SavedBook;

public class ApiSavedBook implements ISavedBook {
    public ApiResponse SaveBook(BookApi book, int userId, String token){
        ApiThread apiThread;
        SavedBook savedBook = new SavedBook(0,userId,false,book.getKey(),book.getTitle(),book.getCover());
        apiThread = new ApiThread(POST, "SavedBooks", savedBook,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse UnsaveBook(int id,String token){
        ApiThread apiThread;
        apiThread = new ApiThread(DELETE, "SavedBooks/"+id,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse GetSavedBooks(String token){
        ApiThread apiThread;
        apiThread = new ApiThread(GET, "SavedBooks",null,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse GetSavedBook(String key, String token){
        ApiThread apiThread;
        apiThread = new ApiThread(GET, "SavedBooks/"+key,null,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse GetSavedBookAnnotation(String key, String token){
        ApiThread apiThread = new ApiThread(GET, "SavedBooks/new"+key,null,token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
