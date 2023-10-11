package com.polarys.appleitour.viewmodel;

import static com.polarys.appleitour.model.BookApi.TITLE;
import static com.polarys.appleitour.api.ApiRequest.GET;
import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;
import static com.polarys.appleitour.model.BookApi.AUTHOR;
import static com.polarys.appleitour.model.BookApi.ISBN;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookApiViewModel extends ViewModel {
    private Activity context;

    public BookApiViewModel() {
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public ArrayList<BookApi> search(String title, String query){
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
        Toast.makeText(context, apiResponse.getBody(), Toast.LENGTH_SHORT).show();

        try {
            return ApiUtil.JsonToArrayObject(BookApi[].class, apiResponse.getBody());
        }
        catch(Exception e){
            Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
            return null;
        }
    }
}
