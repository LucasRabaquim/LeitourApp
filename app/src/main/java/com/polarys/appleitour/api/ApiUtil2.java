//Temporary name
// ApiUtil -> ApiRequest
// ApiUtil2 -> ApiUtil

package com.polarys.appleitour.api;

import android.app.Service;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.IBinder;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.polarys.appleitour.model.User;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiUtil2{

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    static final String API_URL = "http://192.168.15.31:80/api/";
    static final String TOKEN = "token";
    static final String USER_TOKEN = null;
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String SIGN = "SIGN";
    public static final String UPDATE = "PUT";
    public static final String DELETE = "DELETE";

    public static String ObjectToString(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static Object JsonToObject(Object object, JSONObject json){
        Gson gson = new Gson();
        return gson.fromJson(json.toString(), object.getClass());
    }

    public static Object JsonToArrayObject(Object object, JSONObject json){

        // Ref: https://howtodoinjava.com/gson/gson-parse-json-array/
        // Array as root
        Object[] objArray = new Gson().fromJson(json, Object[].class);
        // Other
        Object listType = new TypeToken<ArrayList<object>>(){}.getType();
        ArrayList<ArrayItem> list = gson.fromJson(json, listType); 
       
        Gson gson = new Gson();
        return gson.fromJson(json.toString(), object.getClass());
    }


}