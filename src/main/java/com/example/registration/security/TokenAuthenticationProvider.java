package com.example.registration.security;

import com.example.registration.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Component;

/**
 * 3:54 PM 6/12/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private IExternalServiceAuthenticator externalServiceAuthenticator;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = (String) authentication.getPrincipal();
        if (token.isBlankOrNull()) {
            throw new BadCredentialsException("Token không hợp lệ");
        }
        AuthenticationWithToken authenticationWithToken = externalServiceAuthenticator.authenticate(token);
        return authenticationWithToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PreAuthenticatedAuthenticationToken.class);
    }
}