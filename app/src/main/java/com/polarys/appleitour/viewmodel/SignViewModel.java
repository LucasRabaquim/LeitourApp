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

    public User user;
    private Activity context;
    private boolean keepLogged;

    public SignViewModel() {}

    public void setUser(User user) {
        this.user = user;
    }
    public void setKeepLogged(Boolean keepLogged) {
        this.keepLogged = keepLogged;
    }

    public String getEmail() {
        return user.getEmail();
    }//user.getEmail();}
    public String getPassword() {
        return user.getPassword();
    }//user.getPassword();}

    public void setContext(Activity context) {
        this.context = context;
    }

    public String[] login() throws JSONException {
        ApiResponse apiResponse = user.Login();
        if (apiResponse.getCode() != 200) {
            Toast.makeText(context, apiResponse.getBody(), Toast.LENGTH_SHORT).show();
            return null;
        }
        return formatUser(apiResponse.getBody());
    }

    public String[] register() throws JSONException {
        ApiResponse apiResponse = user.Register();
        if (apiResponse.getCode() != 200) {
            Toast.makeText(context, apiResponse.getBody(), Toast.LENGTH_SHORT).show();
            Log.d("ERRO:", apiResponse.getBody());
            return null;
        }
        return formatUser(apiResponse.getBody());
    }
    private String[] formatUser(String apiUser) throws JSONException {
        JSONObject jsonResponse = new JSONObject(apiUser);
        JSONObject jsonUser = jsonResponse.getJSONObject("user");
        return new String[]{jsonUser.toString(), jsonResponse.getString("token")};
    }
}