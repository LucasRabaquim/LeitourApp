package com.polarys.appleitour.viewmodel;

import static com.polarys.appleitour.api.ApiBook.AUTHOR;
import static com.polarys.appleitour.api.ApiBook.ISBN;
import static com.polarys.appleitour.api.ApiBook.TITLE;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiBook;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;

import java.util.ArrayList;

public class BookApiViewModel extends ViewModel {
    ApiBook apiBook = new ApiBook();
    public BookApiViewModel() {}

    public ArrayList search(String title, String query) {
        ApiResponse apiResponse;
        switch (title) {
            case TITLE:
                apiResponse = apiBook.GetByTitle(query);
                break;
            case ISBN:
                apiResponse = apiBook.GetByIsbn(query);
                break;
            case AUTHOR:
                apiResponse = apiBook.GetByAuthor(query);
                break;
            default:
                return null;
        }
        try {
            return ApiUtil.JsonToArrayObject(new BookApi().getClass(), apiResponse.getBody());
        } catch (Exception e) {
            Log.e("String",e.toString());
            return null;
        }
    }
    public BookApi GetByKey(String key){
        ApiResponse apiResponse = apiBook.GetByKey(key);
        try {
            return (BookApi) ApiUtil.JsonToObject(new BookApi(), apiResponse.getBody());
        }
        catch(Exception e){
            Log.d("From SavedBOok",apiResponse.getBody().toString());
            Log.d("From SavedBOok",e.toString());
            return null;
        }
    }
}
