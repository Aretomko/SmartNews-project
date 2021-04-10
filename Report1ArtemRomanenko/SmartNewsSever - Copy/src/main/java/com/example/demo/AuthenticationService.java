package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.repos.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepo userRepo;

    public AuthenticationService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean authenticateByToken(String token){
        User user = userRepo.findByToken(token);
        return user != null
                ;
    }
}
