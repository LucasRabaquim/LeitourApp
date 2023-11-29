package com.polarys.appleitour.api;

import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;

import com.polarys.appleitour.interfaces.IBook;
import com.polarys.appleitour.model.ApiResponse;

public class ApiBook implements IBook {

    public static final String TITLE = "Title";
    public static final String AUTHOR = "Author";
    public static final String ISBN = "Isbn";
    public static final String QUERY_PARAMETER = "?offset=";

    public ApiResponse GetByIsbn(String search) {
        ApiThread apiThread = new ApiThread(GETPUBLIC, "/SearchBy/"+ISBN + "/" + search, null);
        return apiThread.CreateThread(apiThread).getJson();
    }

    public ApiResponse GetByTitle(String search,int offset) {
        ApiThread apiThread;
        apiThread = new ApiThread(GETPUBLIC, "SearchBy/"+TITLE + "/" + search+QUERY_PARAMETER+offset);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse GetByAuthor(String search,int offset) {
        ApiThread apiThread = new ApiThread(GETPUBLIC, "SearchBy/"+AUTHOR + "/" + search+QUERY_PARAMETER+offset, null);
        return apiThread.CreateThread(apiThread).getJson();
    }
    public ApiResponse GetByKey(String search) {
        ApiThread apiThread = new ApiThread(GETPUBLIC, "SearchBy/Key/" + search);
        return apiThread.CreateThread(apiThread).getJson();
    }
}
