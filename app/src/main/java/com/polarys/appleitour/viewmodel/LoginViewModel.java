package com.polarys.appleitour.viewmodel;

import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.helper.SharedHelper;
import com.polarys.appleitour.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginViewModel extends ViewModel {

    public User user;
    private Activity context;

    public LoginViewModel() {
    }

    public void setUser(User user) {
        this.user = user;
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

    public String login() throws JSONException {
        ApiResponse apiResponse = user.Login();
        if (apiResponse.getCode() != 200) {
            Toast.makeText(context, apiResponse.getBody(), Toast.LENGTH_SHORT).show();
            return apiResponse.getBody();
        }
        JSONObject jsonResponse = new JSONObject(apiResponse.getBody());
        JSONObject jsonUser = jsonResponse.getJSONObject("user");
        user = (User) JsonToObject(new User(), jsonUser);
        SharedHelper settings = new SharedHelper(context);
        // settings.SetKeepLogged(keepLogged.isChecked());
        settings.SetToken(jsonResponse.getString("token"));
        Toast.makeText(context, "Seja bem-vindo(a): " + user.getNameUser(), Toast.LENGTH_SHORT).show();
        return ObjectToString(user);
    }
}