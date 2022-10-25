package com.example.registration.security;

public interface IExternalServiceAuthenticator {
    AuthenticationWithToken authenticate(String token);
}
