package com.polarys.appleitour.viewmodel;

import static android.app.PendingIntent.getActivity;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModel;

import com.polarys.appleitour.api.ApiUser;
import com.polarys.appleitour.helper.RegexHelper;
import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class UserViewModel extends ViewModel {

    public final static String SUCCESS = "SUCCESS";
    public User viewModelUser = null;
    public UserViewModel() {}

    ApiUser apiUser = new ApiUser();

    public String[] login(User user){
        ApiResponse apiResponse = apiUser.Login(user);
        return (apiResponse.getCode() == 200) ? formatUser(apiResponse.getBody()) :
                new String[]{apiResponse.getBody(), null};
    }

    public String[] register(User user){
        ApiResponse apiResponse = apiUser.Register(user);
        return (apiResponse.getCode() == 200) ? formatUser(apiResponse.getBody()) :
                new String[]{apiResponse.getBody(), null};
    }

    public boolean update(String token,User user){
        ApiResponse apiResponse = apiUser.UpdateUser(token,user);
        return (apiResponse.getCode() == 200 | apiResponse.getCode() == 201);
    }

    private String[] formatUser(String apiUser){
        try {
            JSONObject jsonResponse = new JSONObject(apiUser);
            JSONObject jsonUser = jsonResponse.getJSONObject("user");
            return new String[]{jsonUser.toString(), jsonResponse.getString("token")};
        }catch(Exception e){
            return new String[]{null, null};
        }
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
       RegexHelper regexHelper = new RegexHelper();
        if(!regexHelper.verifyUserName(user.GetNameUser()))
            return "O nome só pode conter letras, números underscore e ponto";
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
        RegexHelper regexHelper = new RegexHelper();
        if(user.GetEmail().isEmpty())
            return "Preencha o campo email";
        if(!regexHelper.verifyEmail(user.GetEmail()))
            return "Digite um email válido";
        if(user.GetPassword().isEmpty())
            return "Preencha o campo senha";
        return regexHelper.verifyPassword(user.GetPassword());
    }
}