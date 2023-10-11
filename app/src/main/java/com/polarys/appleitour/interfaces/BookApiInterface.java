package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.model.ApiResponse;

public interface BookApiInterface {
   // ApiResponse
    ApiResponse GetByTitle(String search);
    ApiResponse GetByIsbn(String search);
    ApiResponse GetByAuthor(String search);
    ApiResponse GetByKey(String search);
}
