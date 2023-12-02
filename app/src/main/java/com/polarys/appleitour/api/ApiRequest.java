package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiUtil.ObjectToString;

import android.util.Log;

import com.google.gson.Gson;
import com.polarys.appleitour.model.ApiResponse;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiRequest {
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    public static final MediaType IMAGE = MediaType.parse("image/png");
    static final String API_URL = "http://localhost:5126/api/";
    static final String TOKEN = "token";
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

    public ApiResponse sign(String path, Object object){
        String json = ObjectToString(object);
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
    public ApiResponse post(String path, Object object, String token){
        String json = ObjectToString(object);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(API_URL+path)
                .post(body)
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }
    public ApiResponse update(String path, Object object, String token){
        String json = ObjectToString(object);
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(API_URL+path)
                .put(body)
                .addHeader(TOKEN,token)
                .build();
        return request(request);
    }
    public ApiResponse sendImage(String path, String image, String token){
        Log.d("UPDATE", "update: "+image);
        Log.d("URL", "url: "+API_URL+path);

        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("file", "UserPhoto.jpeg",
                        RequestBody.create(MediaType.parse("Image/*"), image))
                .build();

        Request request = new Request.Builder()
                .url(API_URL+path)
                .post(requestBody)
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
