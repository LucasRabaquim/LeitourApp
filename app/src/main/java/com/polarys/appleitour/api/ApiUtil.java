package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiThread.UPDATE;

import android.content.Context;
import android.net.ConnectivityManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.polarys.appleitour.model.ApiResponse;

import java.lang.reflect.Type;
import java.util.ArrayList;

public abstract class ApiUtil {
    public static boolean verifyConectivity(Context context) {
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connMgr != null;
    }

    public static String ObjectToString(Object object) {
        Gson gson = new Gson();
        try {
            return gson.toJson(object);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object JsonToObject(Object object, String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, object.getClass());
    }

    public static ArrayList<?> JsonToArrayObject(Class<?> cls, String json) {
        Gson gson = new Gson();
        try {
            Type type = TypeToken.getParameterized(ArrayList.class, cls).getType();
            ArrayList<?> arrayList = gson.fromJson(json, type);
            return arrayList;
        } catch (Exception e) {
            return null;
        }
    }


    public static ApiResponse GetPublicUtil(String url) {
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, url, null);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public static ApiResponse GetUtil(String url, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(GET, url, null, token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public static ApiResponse PostUtil(String url, String json, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(POST, url, json, token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public static ApiResponse UpdateUtil(String url, int id, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(UPDATE, url + "/" + id, token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public static ApiResponse UpdateUtil(String url, int id, String json, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(UPDATE, url + "/" + id, json, token);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse DeleteUtil(String url, int id, String json, String token) {
        ApiThread apiThread;
        apiThread = new ApiThread(DELETE, url + "/" + id, json, token);
        return apiThread.CreateThread(apiThread).getJson();
    }
}