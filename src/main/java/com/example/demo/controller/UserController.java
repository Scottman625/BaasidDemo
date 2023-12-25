package com.example.demo.controller;


import com.example.demo.entity.ApiResponse;
import com.example.demo.entity.User;
import com.example.demo.secutiry.JwtTokenProvider;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);


    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;  // 這個是用來處理JWT的組件，您可能需要自己實現。

    @Autowired
    private JwtService jwtService;  // 這個是用來處理JWT的組件，您可能需要自己實現。

    @PostMapping("/register/")
    public ResponseEntity<ApiResponse<Map<String, String>>> register(@RequestParam String account, @RequestParam String password,@RequestParam String name) {
        User existingUser = userService.findByAccountAndPassword(account, password);
        if (existingUser != null) {
            return new ResponseEntity<>(new ApiResponse<>(null, "This account is already been used."), HttpStatus.UNAUTHORIZED); // 或者其他您希望的錯誤響應
        }
        User newUser = userService.createUser(account, password, name);
        if (newUser != null) {
            String username = "New User" + newUser.get_id();
            String token = jwtTokenProvider.createToken(username);
            Map<String, String> tokenMap = new HashMap<>();
            tokenMap.put("token", token);
            return new ResponseEntity<>(new ApiResponse<>(tokenMap, "success login"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse<>(null, "login failed"), HttpStatus.UNAUTHORIZED);
        }
//        return ResponseEntity.ok(newUser);
    }


}



