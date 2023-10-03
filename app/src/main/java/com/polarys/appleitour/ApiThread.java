package com.polarys.appleitour;

import static com.polarys.appleitour.ApiUtil.DELETE;
import static com.polarys.appleitour.ApiUtil.GET;
import static com.polarys.appleitour.ApiUtil.POST;
import static com.polarys.appleitour.ApiUtil.SIGN;
import static com.polarys.appleitour.ApiUtil.UPDATE;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Future;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;


public class ApiThread implements Runnable {

    private static String method;
    private static String url;
    private static String json;
    private static String token;
    private volatile String result;

    public ApiThread(String method,String url,String json){
        this.method = method;
        this.url = url;
        this.json = json;
        this.token = null;
    }
    public ApiThread(String method,String url,String json,String token){
        this.method = method;
        this.url = url;
        this.json = json;
        this.token = token;
    }



    @Override
    public void run() {
        ApiUtil api = new ApiUtil();
        switch (method) {
            case GET:
                try {
                    result = api.get(url,token);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;

            case POST:
                try {
                    result = api.post(url,json,token);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case SIGN:
                result = api.sign(url,json);
                break;
            case UPDATE:
                try {
                    result = api.update(url,json,token);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                break;
            case DELETE:
                try {
                    result = api.delete(url,token);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            default:
                System.out.println("Invalid day of the week");
                break;
        }
    }

    public String getValue() {
        return result;
    }
}