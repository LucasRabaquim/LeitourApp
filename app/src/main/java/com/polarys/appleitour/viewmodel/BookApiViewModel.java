package com.polarys.appleitour.viewmodel;

import static com.polarys.appleitour.api.ApiRequest.GET;
import static com.polarys.appleitour.api.ApiRequest.GETPUBLIC;
import static com.polarys.appleitour.model.BookApi.TITLE;
import static com.polarys.appleitour.model.BookApi.AUTHOR;
import static com.polarys.appleitour.model.BookApi.ISBN;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiRequest;
import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.Post;

import java.util.ArrayList;

public class BookApiViewModel extends ViewModel {

    public BookApiViewModel() {}

    public ArrayList search(String title, String query){
        ApiResponse apiResponse;
        switch (title) {
            case TITLE:
                apiResponse = new BookApi().GetByTitle(query);
                break;
            case ISBN:
                apiResponse = new BookApi().GetByIsbn(query);
                break;
            case AUTHOR:
                apiResponse = new BookApi().GetByAuthor(query);
                break;
            default:
                return null;
        }
        ApiThread apiThread;
        //apiThread = new ApiThread(GETPUBLIC, "/SearchBy/"+TITLE + "/" + query);
        ApiRequest api = new ApiRequest();
        apiResponse = api.get("http://localhost:5126/api/SearchBy/Title/"+query);//apiThread.CreateThread(apiThread).getJson();
        Log.d("Antes",apiResponse.getBody());


        try {
            return  ApiUtil.JsonToArrayObject(new BookApi().getClass(), apiResponse.getBody());        }
        catch(Exception e){
            return null;
        }
    }
}
