package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.secutiry.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.sasl.AuthenticationException;

@Service
public class JwtService {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    public User tokenGetUser(HttpServletRequest request) throws AuthenticationException {
        String token = jwtTokenProvider.resolveToken(request);

        // 從token中取得使用者ID或使用者名稱
        String account = jwtTokenProvider.getUsername(token);

        // 使用ID或使用者名稱從數據庫中獲取使用者詳情

        return userService.findByAccount(account);
    }
}
