package com.maxiflexy.app.auth;


import com.maxiflexy.app.auth.request.AuthenticationRequest;
import com.maxiflexy.app.auth.request.RefreshRequest;
import com.maxiflexy.app.auth.request.RegistrationRequest;
import com.maxiflexy.app.auth.response.AuthenticationResponse;

public interface AuthenticationService {

    AuthenticationResponse login(AuthenticationRequest request);

    void register(RegistrationRequest request);

    AuthenticationResponse refreshToken(RefreshRequest req);
}