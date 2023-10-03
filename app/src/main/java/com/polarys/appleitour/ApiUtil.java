package com.polarys.appleitour;

import android.app.Service;
import android.content.Intent;
import android.media.session.MediaSession;
import android.os.IBinder;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiUtil{

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    static final String API_URL = "https://api.publicapis.org/";
    static final String TOKEN = "token";
    static final String USER_TOKEN = null;
    static final String GET = "GET";
    static final String POST = "POST";
    static final String SIGN = "SIGN";
    static final String UPDATE = "PUT";
    static final String DELETE = "DELETE";
    final OkHttpClient client = new OkHttpClient();
    
    public String get(String path,String token) throws IOException {
        Request request = new Request.Builder()
                .url(API_URL+path)
                .get()
//                .addHeader(TOKEN,token)
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