package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.model.ApiResponse;

public interface BookApiInterface {
   // ApiResponse
    ApiResponse getByTitle();
    ApiResponse getByIsbn();
}
