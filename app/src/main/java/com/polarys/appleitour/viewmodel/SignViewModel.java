package com.polarys.appleitour.viewmodel;

import static android.app.PendingIntent.getActivity;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiUser;
import com.polarys.appleitour.helper.RegexHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;

import org.json.JSONException;
import org.json.JSONObject;

public class SignViewModel extends ViewModel {

    public final static String SUCCESS = "SUCCESS";
    public final static String SIGN_KEY_BUNDLE = "SIGN_KEY_BUNDLE";
    public User viewModelUser = null;
    public SignViewModel() {}

    ApiUser apiUser = new ApiUser();

    public String[] login(User user) throws JSONException {
        ApiResponse apiResponse = apiUser.Login(user);
        if (apiResponse.getCode() != 200)
            return new String[]{apiResponse.getBody(), null};
        return formatUser(apiResponse.getBody());
    }

    public String[] register(User user) throws JSONException {
        ApiResponse apiResponse = apiUser.Register(user);
        if (apiResponse.getCode() != 200)
            return new String[]{apiResponse.getBody(), null};
        return formatUser(apiResponse.getBody());
    }
    private String[] formatUser(String apiUser) throws JSONException {
        JSONObject jsonResponse = new JSONObject(apiUser);
        JSONObject jsonUser = jsonResponse.getJSONObject("user");
        return new String[]{jsonUser.toString(), jsonResponse.getString("token")};
    }

    public void SetUser(User user){
        if(user.GetNameUser().isEmpty())
            user.SetNameUser("");
        viewModelUser = user;
    }
    public User GetUser(){ return viewModelUser;}

    public String VerifyFields(User user,String passwordCompare){
        if(user.GetNameUser().isEmpty())
            return "Preencha o campo usuario";
      /*  RegexHelper regexHelper = new RegexHelper();
        if(!regexHelper.verifyUserName(username))
            return "O nome só pode conter letras, números underscore e ponto";*/
        String verifyEmailPassword = VerifyFields(user);
        if(verifyEmailPassword != SUCCESS)
            return verifyEmailPassword;
        if(passwordCompare.isEmpty())
            return "Preencha a senha para ser comparada";
        if(!user.GetPassword().equals(passwordCompare))
            return "As senhas precisam ser iguais";
        return SUCCESS;
    }
    public String VerifyFields(User user){
     //   RegexHelper regexHelper = new RegexHelper();
        if(user.GetEmail().isEmpty())
            return "Preencha o campo email";
     //   if(!regexHelper.verifyEmail(email))
    //        return "Digite um email válido";
        if(user.GetPassword().isEmpty())
            return "Preencha o campo senha";
   //     return regexHelper.verifyPassword(password);
        return SUCCESS;
    }
}