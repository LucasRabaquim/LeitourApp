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
import com.polarys.appleitour.model.User;

import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.MediaType;

public class ApiUtil {
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

    public static Object JsonToObject(Object object, JSONObject json){
        Gson gson = new Gson();
        return gson.fromJson(json.toString(), object.getClass());
    }

    public static ArrayList<? extends Object> JsonToArrayObject(Object t, String json){
        Gson gson = new Gson();
        // Ref: https://howtodoinjava.com/gson/gson-parse-json-array/
        // Array as root
        ArrayList<? extends Object> arrayList = (ArrayList<?>) gson.fromJson(json, t.getClass());
        // Other
        return arrayList;
    }


}