package com.example.registration.api;

import com.example.registration.entities.User;
import com.example.registration.handler.RegisterServiceException;
import com.example.registration.jwt.JwtTokenProvider;
import com.example.registration.model.RegisterDTO;
import com.example.registration.utils.Constants;
import com.example.registration.utils.Extensions;
import com.example.registration.wrapper.ObjectResponseWrapper;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 22:47 24/10/2022
 * lehongquan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.REGISTER_SERVICE_URL)
public class RegisterAPI {
    private final MongoTemplate mongoTemplate;
    protected final PasswordEncoder passwordEncoder;

    public RegisterAPI(MongoTemplate mongoTemplate, PasswordEncoder passwordEncoder) {
        this.mongoTemplate = mongoTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("")
    public ObjectResponseWrapper register(@RequestBody RegisterDTO registerDTO) {
        if (registerDTO.getUsername().isBlankOrNull()) {
            throw new RegisterServiceException("Username is required");
        }
        if (registerDTO.getPassword().isBlankOrNull()) {
            throw new RegisterServiceException("Password is required");
        }
        User user1 = mongoTemplate.findById(registerDTO.getUsername(), User.class);
        if (user1 != null) {
            throw new RegisterServiceException("Username is existed");
        }
        String password = passwordEncoder.encode(registerDTO.getPassword());
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .username(registerDTO.getUsername())
                .password(password)
                .address(registerDTO.getAddress())
                .phone(registerDTO.getPhone())
                .fullName(registerDTO.getFullName())
                .build();
        mongoTemplate.save(user);
        String token = JwtTokenProvider.generateToken(user.getUsername());
        Map<String,Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("token", token);
        return ObjectResponseWrapper.builder()
                .statusCode(200)
                .data(map)
                .build();
    }
}
