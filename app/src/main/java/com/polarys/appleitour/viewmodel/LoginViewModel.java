package com.polarys.appleitour.viewmodel;

import static android.app.PendingIntent.getActivity;
import static androidx.core.content.ContextCompat.startActivity;
import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import static java.security.AccessController.getContext;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Observable;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import com.polarys.appleitour.model.SharedSettings;
import com.polarys.appleitour.model.User;
import com.polarys.appleitour.view.MainActivity;

import org.json.JSONObject;

public class LoginViewModel extends ViewModel {

    private Activity context;

    public String getEmail(){return "";}//user.getEmail();}
    public String getPassword(){return "";}//user.getPassword();}

    public LoginViewModel() {}
    public void setContext(Activity context) {this.context = context;}

    public String login(User user){
        String stringUser = ObjectToString(user);
        Toast.makeText(context, stringUser, Toast.LENGTH_SHORT).show();
        Log.d("",stringUser);
        String apiResponse = user.Login(stringUser);
        if(apiResponse == "") {
            Toast.makeText(context, "Verifique conexão com a internet", Toast.LENGTH_SHORT).show();
            return apiResponse;
        }
        try {
            JSONObject jsonResponse = new JSONObject(apiResponse);
            JSONObject jsonUser = jsonResponse.getJSONObject("user");
            user = (User) JsonToObject(new User(), jsonUser);
            SharedSettings settings = new SharedSettings(context);
            // settings.SetKeepLogged(keepLogged.isChecked());
            settings.SetToken(jsonResponse.getString("token"));
            Toast.makeText(context, "Seja bem-vindo(a): " + user.getNameUser(), Toast.LENGTH_SHORT).show();
            return ObjectToString(user);
        } catch (Exception ex) {
            Log.d("Erro: ", String.valueOf(ex));
            Toast.makeText(context, apiResponse, Toast.LENGTH_SHORT).show();
            return "";
        }

    }


}