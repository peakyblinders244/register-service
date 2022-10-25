package com.example.registration.api;

import com.example.registration.entities.User;
import com.example.registration.jwt.JwtTokenProvider;
import com.example.registration.model.LoginDTO;
import com.example.registration.repositories.IUserRepository;
import com.example.registration.utils.Constants;
import com.example.registration.utils.Extensions;
import com.example.registration.wrapper.ObjectResponseWrapper;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.Map;

/**
 * 22:44 24/10/2022
 * lehongquan
 */

@ExtensionMethod(Extensions.class)
@RestController
@RequestMapping(Constants.LOGIN_SERVICE_URL)
public class LoginAPI {
    private final MongoTemplate mongoTemplate;
    private final IUserRepository userRepository;

    public LoginAPI(MongoTemplate mongoTemplate, IUserRepository userRepository) {
        this.mongoTemplate = mongoTemplate;
        this.userRepository = userRepository;
    }

    @PermitAll
    @PostMapping("")
    public ObjectResponseWrapper login(@RequestBody LoginDTO login) {

        String token = JwtTokenProvider.generateToken(login.getUsername());
        User user = userRepository.findByUsername(login.getUsername());
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("full_name", user.getFullName());
        map.put("accessToken", token);
        map.put("address", user.getAddress());
        map.put("phone", user.getPhone());
        return ObjectResponseWrapper.builder()
                .statusCode(200)
                .data(map)
                .build();
    }
}
