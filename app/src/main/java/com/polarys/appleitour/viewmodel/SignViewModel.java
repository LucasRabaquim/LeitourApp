package com.polarys.appleitour.viewmodel;

import static android.app.PendingIntent.getActivity;
import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;
import static com.polarys.appleitour.helper.SharedHelper.getContext;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class SignViewModel extends ViewModel {

    public SignViewModel() {}
    public String[] login(User user) throws JSONException {
        ApiResponse apiResponse = user.Login();
        if (apiResponse.getCode() != 200)
            return new String[]{apiResponse.getBody(), null};
        return formatUser(apiResponse.getBody());
    }

    public String[] register(User user) throws JSONException {
        ApiResponse apiResponse = user.Register();
        if (apiResponse.getCode() != 200)
            return new String[]{apiResponse.getBody(), null};
        return formatUser(apiResponse.getBody());
    }
    private String[] formatUser(String apiUser) throws JSONException {
        JSONObject jsonResponse = new JSONObject(apiUser);
        JSONObject jsonUser = jsonResponse.getJSONObject("user");
        return new String[]{jsonUser.toString(), jsonResponse.getString("token")};
    }
}