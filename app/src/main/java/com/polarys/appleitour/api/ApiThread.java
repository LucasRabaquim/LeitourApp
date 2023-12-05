package com.polarys.appleitour.api;


import com.polarys.appleitour.model.ApiResponse;


import org.json.JSONObject;

import java.io.File;

public class ApiThread implements Runnable {
    private static String method = null;
    private static String url = null;
    private static Object json = null;
    private static String token = null;
    private static String image = null;

    public static final String GET = "GET";
    public static final String DEBUG = "DEBUG";
    public static final String GETPUBLIC = "GETPUBLIC";
    public static final String POST = "POST";
    public static final String SIGN = "SIGN";
    public static final String AUTOLOGIN = "AUTOLOGIN";
    public static final String SENDIMAGE = "SENDIMAGE";
    public static final String UPDATE = "PUT";
    public static final String DELETE = "DELETE";
    private volatile ApiResponse result;

    public ApiThread(String method,String url){
        this.method = method;
        this.url = url;
    }
    public ApiThread(String method,String url,Object json){
        this.method = method;
        this.url = url;
        this.json = json;
    }
    public ApiThread(String method,String url,Object json,String token){
        this.method = method;
        this.url = url;
        this.json = json;
        this.token = token;
    }
    public ApiThread(String method,String url,String image,String token){
        this.method = method;
        this.url = url;
        this.image = image;
        this.token = token;
    }

    public ApiThread CreateThread(ApiThread apiThread){
        Thread thread = new Thread(apiThread);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return apiThread;
    }
    @Override
    public void run() {
        ApiRequest api = new ApiRequest();
        switch (method) {
            case GETPUBLIC:
                result = api.get(url);
                break;
            case GET:
                result = api.get(url,token);
                break;
            case AUTOLOGIN:
                result = api.autologin(url,token);
                break;
            case POST:
                result = api.post(url,json,token);
                break;
            case SIGN:
                result = api.sign(url,json);
                break;
            case SENDIMAGE:
                result = api.sendImage(url,image,token);
                break;
            case UPDATE:
                result = api.update(url,json,token);
                break;
            case DELETE:
                result = api.delete(url,token);
                break;
            case DEBUG:
                result = api.debug(url);
                break;
            default:
                break;
        }

    }
    public ApiResponse getJson() {
        return result;
    }

}