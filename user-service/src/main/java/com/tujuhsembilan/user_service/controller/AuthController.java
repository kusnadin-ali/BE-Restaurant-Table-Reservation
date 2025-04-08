package com.tujuhsembilan.user_service.controller;

import java.util.Objects;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tujuhsembilan.core.utils.ResponseUtil;
import com.tujuhsembilan.user_service.dto.Auth.AuthenticationRequest;
import com.tujuhsembilan.user_service.dto.Auth.AuthenticationResponse;
import com.tujuhsembilan.user_service.dto.User.UserCustomerDto;
import com.tujuhsembilan.user_service.model.User;
import com.tujuhsembilan.core.constant.ApiConstant.ResponseCode;
import com.tujuhsembilan.core.constant.ApiConstant.ResponseMessage;
import com.tujuhsembilan.core.dto.ResponseDto;
import com.tujuhsembilan.core.utils.JwtUtil;
import com.tujuhsembilan.user_service.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    @PostMapping("/login")
    public ResponseDto<Object> login(@RequestBody AuthenticationRequest request) {
        // Cari user berdasarkan username
        Optional<User> userOptional = userService.getUserByUsername(request.getUsername());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Bandingkan password yang diinput dengan yang ada di database
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                String token = jwtUtil.generateToken(user.getUsername(), user.getUserType());
                AuthenticationResponse response = new AuthenticationResponse(token, user.getUserType());
                return ResponseUtil.success(response);
            }
        }

        return ResponseUtil.error(null, ResponseCode.ERROR_CODE, ResponseMessage.ERROR_INVALID_USERNAME_OR_PASSWORD);
    }
    

    @PostMapping("/register")
    public ResponseDto<Object> register(@RequestBody UserCustomerDto userDto) {
        if (Objects.isNull(userDto)) {
            throw new Error("Payload cannot be Null");
        }
        if (userService.isUserExistByUsername(userDto.getUsername())) {
            throw new Error("Username is already taken");
        }
        System.out.println("masuk");
        
        return userService.saveUser(userDto);
    }
}
