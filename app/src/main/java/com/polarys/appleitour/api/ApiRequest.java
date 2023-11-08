package com.polarys.appleitour.api;

import com.google.gson.Gson;
import com.polarys.appleitour.model.ApiResponse;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiRequest {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    static final String API_URL = "http://localhost:5126/api/";

    static final String TOKEN = "token";
    static final String USER_TOKEN = null;
    public static final String GET = "GET";
    public static final String DEBUG = "DEBUG";
    public static final String GETPUBLIC = "GETPUBLIC";
    public static final String POST = "POST";
    public static final String SIGN = "SIGN";
    public static final String AUTOLOGIN = "AUTOLOGIN";
    public static final String UPDATE = "PUT";
    public static final String DELETE = "DELETE";
    final OkHttpClient client = new OkHttpClient();


    public ApiResponse get(String path) {
        Request request = new Request.Builder()
                .url(API_URL+path)
                .get()
                .build();
        return request(request);
    }

    public ApiResponse get(String path,String token){
        Request request = new Request.Builder()
                .url(API_URL+path)
                .get()
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }

    public ApiResponse sign(String path, String json){
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(API_URL+path)
                .post(body)
                .build();
        return request(request);
    }

    public ApiResponse debug(String path){
        Request request = new Request.Builder()
                .url(path)
                .get()
                .build();
        return request(request);
    }
    public ApiResponse autologin(String path, String token){
        RequestBody body = RequestBody.create("body", JSON);
        Request request = new Request.Builder()
                .url(API_URL+path)
                .post(body)
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }
    public ApiResponse post(String path, String json, String token){
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(API_URL+path)
                .post(body)
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }
    public ApiResponse update(String path, String json, String token){
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(API_URL+path)
                .put(body)
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }
    public ApiResponse delete(String path, String token){
        Request request = new Request.Builder()
                .url(API_URL+path)
                .delete()
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }
    
    public ApiResponse request(Request request) {
        try (Response response = client.newCall(request).execute()) {
            return new ApiResponse(response.code(),response.body().string());
        }
        catch(Exception e){ return new ApiResponse();}
    }
}
