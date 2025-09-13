package com.prafull.ArthTrack.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prafull.ArthTrack.model.login.LoginRequestModel;
import com.prafull.ArthTrack.model.login.LoginResponseModel;
import com.prafull.ArthTrack.model.signUp.SignUpRequestModel;
import com.prafull.ArthTrack.model.signUp.SignUpResponseModel;
import com.prafull.ArthTrack.security.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseModel> login(@RequestBody LoginRequestModel loginRequestModel) {
        return ResponseEntity.ok(authService.login(loginRequestModel));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseModel> signUp(@RequestBody SignUpRequestModel signUpRequestModel) {
        return ResponseEntity.ok(authService.signUp(signUpRequestModel));
    }
}
