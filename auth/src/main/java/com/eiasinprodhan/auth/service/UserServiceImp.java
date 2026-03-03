package com.eiasinprodhan.auth.service;

import com.eiasinprodhan.auth.dto.request.UserRequest;
import com.eiasinprodhan.auth.dto.response.UserResponse;
import com.eiasinprodhan.auth.entity.User;
import com.eiasinprodhan.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    @Override
    public UserResponse saveUser(UserRequest userRequest) {

        User user = modelMapper.map(userRequest, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        UserResponse userResponse = modelMapper.map(userRepository.save(user), UserResponse.class);

        return userResponse;
    }
}
