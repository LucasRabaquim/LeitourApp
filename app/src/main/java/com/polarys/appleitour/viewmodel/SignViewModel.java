package com.polarys.appleitour.viewmodel;

import static android.app.PendingIntent.getActivity;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class SignViewModel extends ViewModel {

    public final static String SUCCESS = "SUCCESS";
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

    public String VerifyField(String username, String email, String pasword, String passwordCompare){
        if(username.isEmpty())
            return "Preencha o campo usuario";
        String verifyEmailPassword = VerifyField(email, pasword);
        if(verifyEmailPassword != SUCCESS)
            return verifyEmailPassword;
        if(passwordCompare.isEmpty())
            return "Preencha a senha para ser comparada";
        if(passwordCompare != passwordCompare)
            return "As senhas precisam ser iguais";
        return SUCCESS;
    }
    public String VerifyField(String email, String pasword){
        if(email.isEmpty())
            return "Preencha o campo email";
        if(pasword.isEmpty())
            return "Preencha o campo senha";
        else
            return SUCCESS;
    }
}