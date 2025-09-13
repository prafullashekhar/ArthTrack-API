package com.prafull.ArthTrack.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prafull.ArthTrack.model.initial.InitialResponseModel;
import com.prafull.ArthTrack.service.InitialService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/initial")
@RequiredArgsConstructor    
public class InitialController {
    
    private final InitialService initialService;

    @PostMapping("/get-all-lists")
    public ResponseEntity<InitialResponseModel> getInitialData() {
        return ResponseEntity.ok(initialService.getInitialData());
    }
}
