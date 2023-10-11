package com.polarys.appleitour.viewmodel;

import static android.provider.MediaStore.MediaColumns.TITLE;
import static com.polarys.appleitour.api.ApiRequest.GET;
import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;
import static com.polarys.appleitour.model.BookApi.ISBN;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BookApiViewModel extends ViewModel {
    public ArrayList<BookApi> book;
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
                apiResponse = new BookApi().SearchByTitle(query);
                break;
            case ISBN:
                apiResponse = new BookApi().SearchByIsbn(query);
                break;
            default:
                apiResponse = new ApiResponse();
        }
        if (apiResponse.getCode() != 200) {
            Toast.makeText(context, apiResponse.getBody(), Toast.LENGTH_SHORT).show();
            return null;
        }
        return (ArrayList<BookApi>) ApiUtil.JsonToArrayObject(new BookApi(), apiResponse.getBody());
    }
}
