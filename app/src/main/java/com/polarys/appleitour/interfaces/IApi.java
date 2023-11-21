package com.polarys.appleitour.interfaces;

import com.polarys.appleitour.api.ApiUtil;
import com.polarys.appleitour.model.ApiResponse;

public interface IApi {
    ApiResponse GetPublic(String path);

    ApiResponse Get(String path, String token);

    ApiResponse Post(String path, String json, String token);

    ApiResponse Update(String path, int id, String json, String token);

    ApiResponse Delete(String path, int id, String json, String token);
}
