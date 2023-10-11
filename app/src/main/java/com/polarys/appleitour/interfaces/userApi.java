package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.api.ApiThread;
import com.polarys.appleitour.model.ApiResponse;

public interface userApi {
    ApiResponse Login();
    ApiResponse Register();
}
