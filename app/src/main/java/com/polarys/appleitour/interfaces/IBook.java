package com.polarys.appleitour.interfaces;

import static com.polarys.appleitour.api.ApiThread.GETPUBLIC;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.model.ApiResponse;

public interface IBook {
    ApiResponse GetByIsbn(String search);

    ApiResponse GetByTitle(String search);
    ApiResponse GetByAuthor(String search);
    ApiResponse GetByKey(String search);
}
