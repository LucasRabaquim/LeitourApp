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

public class ApiUtil{

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


    final OkHttpClient client = new OkHttpClient();
    public String get(String path) {
        Request request = new Request.Builder()
                .url(API_URL+path)
                .get()
                .build();
        return request(request);
    }

    public String get(String path,String token) throws IOException {
        Request request = new Request.Builder()
                .url(API_URL+path)
                .get()
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }

    public String sign(String path, String json){
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(API_URL+path)
                .post(body)
                .build();
        return request(request);
    }
    public String post(String path, String json, String token) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(API_URL+path)
                .post(body)
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }
    public String update(String path, String json, String token) throws IOException {
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(API_URL+path)
                .put(body)
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }
    public String delete(String path, String token) throws IOException {
        Request request = new Request.Builder()
                .url(API_URL+path)
                .delete()
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }
    
    public String request(Request request) {
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
        catch(Exception e){ return e.toString();}
    }
}