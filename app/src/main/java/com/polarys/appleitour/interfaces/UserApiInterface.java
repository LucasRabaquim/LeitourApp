package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.model.ApiResponse;
import com.polarys.appleitour.model.User;

public interface UserApiInterface {
    ApiResponse Login();
    ApiResponse Register();
}
