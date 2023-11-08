package com.polarys.appleitour.helper;

import static com.polarys.appleitour.api.ApiUtil.JsonToObject;
import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.User;

public class SharedHelper extends AppCompatActivity {
    final static String SHARED_NAME = "com.example.appleitour";
    final static String TOKEN = "Token";
    final static String THEME = "Theme";
    final static String USER = "User";
    final static String KEEP_LOGGED = "Keep_Logged";
    SharedPreferences settings;
    private static Application instance;

    public static Context getContext() {
        return instance.getApplicationContext();
    }

    public SharedHelper(Context context){
        this.settings = context.getSharedPreferences(SHARED_NAME, 0);
    }

    public String GetToken(){
        return settings.getString(TOKEN, null);
    }
    public void SetToken(String token){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(TOKEN,token);
        editor.apply();
    }
    public void SetTheme(int theme){
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(THEME,theme);
        editor.apply();
    }
    public int GetTheme(){
        return settings.getInt(THEME, 0);
    }
    public void ApplyTheme(int theme){
        AppCompatDelegate.setDefaultNightMode(theme);
    }

    public void SetKeepLogged(boolean keepLogged){
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(KEEP_LOGGED,keepLogged);
        editor.apply();
    }
    public boolean GetKeepLogged(){
        return settings.getBoolean(KEEP_LOGGED, false);
    }

    public void SetUser(User user){
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(USER, ObjectToString(user));
        editor.apply();
    }
    public User GetUser(){
        return (User) JsonToObject(new User(),settings.getString(USER, ""));
    }
    public int GetId(){
        User user = (User) JsonToObject(new User(),settings.getString(USER, ""));
        return user.getId();
    }
}