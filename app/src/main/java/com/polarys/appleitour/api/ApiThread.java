package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiUtil.DELETE;
import static com.polarys.appleitour.api.ApiUtil.GET;
import static com.polarys.appleitour.api.ApiUtil.POST;
import static com.polarys.appleitour.api.ApiUtil.SIGN;
import static com.polarys.appleitour.api.ApiUtil.UPDATE;

import com.polarys.appleitour.api.ApiUtil;

import java.io.IOException;


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
                    result = e.toString();
                }
                break;

            case POST:
                try {
                    result = api.post(url,json,token);
                } catch (IOException e) {
                    result = e.toString();
                }
                break;
            case SIGN:
                result = api.sign(url,json);
                break;
            case UPDATE:
                try {
                    result = api.update(url,json,token);
                } catch (IOException e) {
                    result = e.toString();
                }
                break;
            case DELETE:
                try {
                    result = api.delete(url,token);
                } catch (IOException e) {
                    result = e.toString();
                }
            default:
                System.out.println("Invalid day of the week");
                break;
        }
    }

    public String getJson() {
        return result;
    }
}