package com.prafull.ArthTrack.model.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponseModel {
    private String jwtToken;
    private String userId;
}
