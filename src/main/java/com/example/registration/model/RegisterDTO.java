package com.example.registration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 23:14 25/10/2022
 * lehongquan
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String id;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String fullName;
}
