package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiRequest.DELETE;
import static com.polarys.appleitour.api.ApiRequest.GET;
import static com.polarys.appleitour.api.ApiRequest.POST;
import static com.polarys.appleitour.api.ApiRequest.SIGN;
import static com.polarys.appleitour.api.ApiRequest.UPDATE;
import com.polarys.appleitour.model.ApiResponse;

public class ApiThread implements Runnable {

    private static String method;
    private static String url;
    private static String json;
    private static String token;
    private volatile ApiResponse result;

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
        ApiRequest api = new ApiRequest();
        switch (method) {
            case GET:
                result = api.get(url,token);
                break;
            case POST:
                result = api.post(url,json,token);
                break;
            case SIGN:
                result = api.sign(url,json);
                break;
            case UPDATE:
                result = api.update(url,json,token);
                break;
            case DELETE:
                result = api.delete(url,token);
                break;
            default:
                System.out.println("Invalid day of the week");
        }
    }

    public ApiResponse getJson() {
        return result;
    }
}