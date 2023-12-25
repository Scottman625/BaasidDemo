package com.example.demo.controller;

import com.example.demo.entity.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.service.AuthenticationService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.webjars.NotFoundException;

import javax.security.sasl.AuthenticationException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String account, @RequestParam String password) {
        try {
            String token = authenticationService.authenticate(account, password);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Bearer " + token);

            User user = userService.getUserByAccount(account).orElseThrow(()-> new NotFoundException(account)) ;

            LoginResponse loginResponse = new LoginResponse(user.getAccount(), user.getName());

//            Map<String, String> message = new HashMap<>();
//            message.put("account",user.getAccount());
//            message.put("name",user.getName());
            return ResponseEntity.ok().headers(headers).body(loginResponse);

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("帳號密碼錯誤");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("系統錯誤");
        }
    }
}




