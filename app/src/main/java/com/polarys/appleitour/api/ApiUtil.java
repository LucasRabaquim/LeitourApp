//Temporary name
// ApiUtil -> ApiRequest
// ApiUtil2 -> ApiUtil

package com.polarys.appleitour.api;

import java.util.*;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.polarys.appleitour.model.User;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.MediaType;

public final class ApiUtil {
    public static boolean verifyConectivity(Context context){
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connMgr == null)
            Toast.makeText(context, "Verifique sua conexão", Toast.LENGTH_SHORT).show();
        return connMgr != null;
    }
    public static String ObjectToString(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static Object JsonToObject(Object object, String json){
        Gson gson = new Gson();
        return gson.fromJson(json, object.getClass());
    }

    public static <T> ArrayList<T> JsonToArrayObject(Class<T[]> clazz, String json){
        Gson gson = new Gson();
        T[] arr = new Gson().fromJson(json, clazz);
        return new ArrayList<>(Arrays.asList(arr));
    }


}