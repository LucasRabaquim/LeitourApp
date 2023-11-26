package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiThread.DELETE;
import static com.polarys.appleitour.api.ApiThread.GET;
import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;
import static com.polarys.appleitour.api.ApiThread.POST;
import static com.polarys.appleitour.api.ApiThread.UPDATE;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

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
        try {
            return gson.fromJson(json, object.getClass());
        }catch(Exception e){
            return null;
        }
    }

    public static ArrayList<?> JsonToArrayObject(Class<?> cls, String json) {
        Gson gson = new Gson();
        try {
            Type type = TypeToken.getParameterized(ArrayList.class, cls).getType();
            ArrayList<?> arrayList = gson.fromJson(json, type);
            return arrayList;
        } catch (Exception e) {
            Log.d("Erro JsonToArray",e.toString());
            return null;
        }
    }
}