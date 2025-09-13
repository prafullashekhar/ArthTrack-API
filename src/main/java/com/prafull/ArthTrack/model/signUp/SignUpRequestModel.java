package com.prafull.ArthTrack.model.signUp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestModel {
    private String username;
    private String password;
    private String email;
    private String name;

}
