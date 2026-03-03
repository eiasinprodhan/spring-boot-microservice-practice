package com.eiasinprodhan.auth.service;

import com.eiasinprodhan.auth.dto.request.UserRequest;
import com.eiasinprodhan.auth.dto.response.UserResponse;

public interface UserService {
    public UserResponse saveUser(UserRequest userRequest);
}
