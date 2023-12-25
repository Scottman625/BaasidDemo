package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User validateUser(String account, String password) {
        Optional<User> userOptional = userRepository.findByAccount(account);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) { // Use encoder to compare passwords
                return user;
            }
        }
        return null;
    }

    public User createUser(String account, String password, String name) {
        User user = new User();
        user.setAccount(account);
        user.setName(name);
        String hashedPassword = passwordEncoder.encode(password); // Encrypting the password using bcrypt
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    public User findByAccountAndPassword(String account, String password) {
        return userRepository.findByAccountAndPassword(account, password);
    }

    public User findByAccount(String account) {
        return userRepository.findByAccount(account).orElseThrow(()-> new NotFoundException(account));
    }


    public Optional<User> getUserByAccount(String account) {
        return userRepository.findByAccount(account);
    }
}
