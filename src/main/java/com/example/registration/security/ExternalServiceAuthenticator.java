package com.example.registration.security;

import com.example.registration.entities.User;
import com.example.registration.handler.RegisterServiceException;
import com.example.registration.jwt.JwtTokenProvider;
import com.example.registration.repositories.IUserRepository;
import com.example.registration.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 3:58 PM 6/12/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Component
public class ExternalServiceAuthenticator implements IExternalServiceAuthenticator {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public AuthenticationWithToken authenticate(String token) {
        AuthenticationWithToken authenticationWithToken;
        String username = null;
        try {
            username = JwtTokenProvider.getUserNameFromJwt(token);
        } catch (Exception e) {
            throw new RegisterServiceException("Token không hợp lệ");
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new RegisterServiceException("Token không hợp lệ");
        }

        authenticationWithToken = new AuthenticationWithToken(user, null, null);
        return authenticationWithToken;
    }
}