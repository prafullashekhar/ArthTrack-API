package com.prafull.ArthTrack.model.signUp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseModel {
    private String token;
    private String userId;
    private String username;
    private String name;
}
