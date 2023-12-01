package com.polarys.appleitour.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiAnnotation;
import com.polarys.appleitour.api.ApiSavedBook;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.interfaces.IAnnotation;
import com.polarys.appleitour.interfaces.ISavedBook;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.SavedBook;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SavedBookViewModel extends ViewModel {
    public SavedBookViewModel(){};
    ApiSavedBook apiSavedBook = new ApiSavedBook();
    ApiAnnotation apiAnnotation = new ApiAnnotation();

    public ApiResponse SaveBook(BookApi book, int userId, String token){
        return apiSavedBook.SaveBook(book, userId, token);
    }
    public ApiResponse UnsaveBook(int userId, String token){
        return apiSavedBook.UnsaveBook(userId, token);
    }
    public ArrayList<SavedBook> loadSaved(String token){
        ApiResponse apiResponse = apiSavedBook.GetSavedBooks(token);
        Log.d("T",apiResponse.getBody());
        return (ArrayList<SavedBook>) ApiUtil.JsonToArrayObject(new SavedBook().getClass(), apiResponse.getBody());
    }
    public ArrayList<SavedBook> loadSavedFromEmail(String email, int offset){
        ApiResponse apiResponse = apiSavedBook.GetSavedBookByEmail(email,offset);
        Log.d("T",apiResponse.getBody());
        return (ArrayList<SavedBook>) ApiUtil.JsonToArrayObject(new SavedBook().getClass(), apiResponse.getBody());
    }
    public ArrayList<Annotation> showAnnotations(int id, String token){
        ApiResponse apiResponse = apiAnnotation.GetAnnotations(id,token);
        Log.d("Só anotacao",apiResponse.getBody());
        return (ArrayList<Annotation>) ApiUtil.JsonToArrayObject(new Annotation().getClass(), apiResponse.getBody());
    }
}