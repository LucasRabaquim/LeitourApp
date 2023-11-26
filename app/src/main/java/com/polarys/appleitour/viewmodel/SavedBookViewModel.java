package com.polarys.appleitour.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiAnnotation;
import com.polarys.appleitour.api.ApiSavedBook;
import com.polarys.appleitour.api.ApiUtil;
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
    public Object[] GetBookAndAnnotation(String key, String token) {
        ApiResponse apiResponse = apiSavedBook.GetSavedBookAnnotation(key,token);
        try {
            JSONObject jsonResponse = new JSONObject(apiResponse.getBody());
            String jsonSaved = jsonResponse.getJSONObject("saved").toString();
            String jsonAnnotation = jsonResponse.getJSONArray("annotation").toString();
            Log.d("jsonAnnotation", jsonAnnotation);
            SavedBook savedBook = (SavedBook) ApiUtil.JsonToObject(new SavedBook().getClass(), jsonSaved);
            ArrayList<Annotation> annotations = (ArrayList<Annotation>) ApiUtil.JsonToArrayObject(new Annotation().getClass(), jsonAnnotation);
            return new Object[]{savedBook, annotations};
        }
        catch(Exception e){
            Log.d("jsonAnnotation Erro", e.toString());
            return null;
        }
    }
    public ApiResponse SaveBook(BookApi book, int userId, String token){
        return apiSavedBook.SaveBook(book, userId, token);
    }
    public ArrayList<SavedBook> loadSaved(String token){
        ApiResponse apiResponse = apiSavedBook.GetSavedBooks(token);
        Log.d("T",apiResponse.getBody());
        return (ArrayList<SavedBook>) ApiUtil.JsonToArrayObject(new SavedBook().getClass(), apiResponse.getBody());
    }
    public ArrayList<SavedBook> loadSavedFromEmail(String email){
        ApiResponse apiResponse = apiSavedBook.GetSavedBookByEmail(email);
        Log.d("T",apiResponse.getBody());
        return (ArrayList<SavedBook>) ApiUtil.JsonToArrayObject(new SavedBook().getClass(), apiResponse.getBody());
    }
    public ArrayList<Annotation> showAnnotations(int id, String token){
        ApiResponse apiResponse = apiAnnotation.GetAnnotations(id,token);
        Log.d("Só anotacao",apiResponse.getBody());
        return (ArrayList<Annotation>) ApiUtil.JsonToArrayObject(new Annotation().getClass(), apiResponse.getBody());
    }
}