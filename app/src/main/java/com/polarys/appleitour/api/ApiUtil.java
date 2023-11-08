//Temporary name
// ApiUtil -> ApiRequest
// ApiUtil2 -> ApiUtil

package com.polarys.appleitour.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.polarys.appleitour.model.Annotation;
import com.polarys.appleitour.model.BookApi;
import com.polarys.appleitour.model.Post;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ApiUtil {
    public static boolean verifyConectivity(Context context){
        final ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
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

    public static ArrayList<?> JsonToArrayObject(Class<?> cls, String json){
        Gson gson = new Gson();
        Type type = TypeToken.getParameterized(ArrayList.class, cls).getType();
        ArrayList<?> arrayList = gson.fromJson(json, type);
        return arrayList;
    }
    /*
    ArrayItem[] userArray = new Gson().fromJson(jsonSource, ArrayItem[].class);

      Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
    ArrayList<ArrayItem> list = gson.fromJson(jsonSource, listType);
    * */


}