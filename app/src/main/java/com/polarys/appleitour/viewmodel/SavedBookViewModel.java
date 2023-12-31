package com.polarys.appleitour.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiAnnotation;
import com.polarys.appleitour.api.ApiSavedBook;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.Book;
import com.polarys.appleitour.model.SavedBook;

import java.util.ArrayList;

public class SavedBookViewModel extends ViewModel {
    public SavedBookViewModel(){};
    ApiSavedBook apiSavedBook = new ApiSavedBook();
    ApiAnnotation apiAnnotation = new ApiAnnotation();

    public ApiResponse SaveBook(Book book, int userId, String token){
        ApiResponse apiResponse =  apiSavedBook.SaveBook(book, userId, token);
        Log.d("SaveBook", "SaveBook: "+apiResponse.getBody());
        return apiResponse;
    }
    public ApiResponse UnsaveBook(int bookId, String token){
        ApiResponse apiResponse = apiSavedBook.UnsaveBook(bookId, token);
        Log.d("UnsaveBook", "UnsaveBook: "+apiResponse.getBody());
        return apiResponse;
    }
    public ArrayList<SavedBook> loadSaved(String token){
        ApiResponse apiResponse = apiSavedBook.GetSavedBooks(token);
        Log.d("T",apiResponse.getBody());
        return (ArrayList<SavedBook>) ApiUtil.JsonToArrayObject(SavedBook.class, apiResponse.getBody());
    }
    public ArrayList<SavedBook> loadSavedFromEmail(String email, int offset){
        ApiResponse apiResponse = apiSavedBook.GetSavedBookByEmail(email,offset);
        Log.d("T",apiResponse.getBody());
        return (ArrayList<SavedBook>) ApiUtil.JsonToArrayObject(SavedBook.class, apiResponse.getBody());
    }
    public ArrayList<Annotation> showAnnotations(int id, String token){
        ApiResponse apiResponse = apiAnnotation.GetAnnotations(id,token);
        Log.d("Só anotacao",apiResponse.getBody());
        return (ArrayList<Annotation>) ApiUtil.JsonToArrayObject(Annotation.class, apiResponse.getBody());
    }
}