package com.example.demo.service;

import com.example.demo.entity.ApiResponse;
import com.example.demo.entity.User;
import com.example.demo.secutiry.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;  // 這個是用來處理JWT的組件，您可能需要自己實現。
    public String authenticate(String account, String password) throws AuthenticationException {
        // 驗證用戶名和密碼
        User user = userService.validateUser(account, password);
        // 如果驗證成功，生成 JWT 令牌
        if (user != null) {
                return jwtTokenProvider.createToken(account);
        } else {
            // 如果驗證失敗，拋出 AuthenticationException
            throw new AuthenticationException("帳號或密碼錯誤");
        }
    }
}

