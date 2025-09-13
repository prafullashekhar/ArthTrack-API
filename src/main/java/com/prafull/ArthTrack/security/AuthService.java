package com.prafull.ArthTrack.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.prafull.ArthTrack.model.login.LoginRequestModel;
import com.prafull.ArthTrack.model.login.LoginResponseModel;
import com.prafull.ArthTrack.model.signUp.SignUpRequestModel;
import com.prafull.ArthTrack.model.signUp.SignUpResponseModel;
import com.prafull.ArthTrack.domain.entity.User;
import com.prafull.ArthTrack.domain.jpaRepository.UserRepository;
import com.prafull.ArthTrack.exception.LoginException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authendicationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    // Login Service Implementation
    public LoginResponseModel login(LoginRequestModel loginRequestModel) {
        Authentication authentication;
        try{
            authentication = authendicationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestModel.getUsername(), loginRequestModel.getPassword())
            );
        }catch(Exception e){
            throw new LoginException("Invalid username or password");
        }

        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateToken(user);

        return new LoginResponseModel(token, user.getUserId().toString());
    }


    // New user signup service implementation
    public SignUpResponseModel signUp(SignUpRequestModel signUpRequestModel) {
        User user = userRepository.findByUsername(signUpRequestModel.getUsername()).orElse(null);
        if (user != null) {
            throw new LoginException("Username already exists");
        }

        user = new User();
        user.setUsername(signUpRequestModel.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequestModel.getPassword()));
        user.setEmail(signUpRequestModel.getEmail());
        user.setName(signUpRequestModel.getName());
        userRepository.save(user);

        String token = authUtil.generateToken(user);
        return new SignUpResponseModel(token, user.getUserId().toString(), user.getUsername(), user.getName());
    }
}
