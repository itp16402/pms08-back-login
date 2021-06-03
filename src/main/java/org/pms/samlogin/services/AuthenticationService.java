package org.pms.samlogin.services;

import org.pms.samlogin.dto.authenticate.AuthenticationRequest;
import org.pms.samlogin.dto.authenticate.AuthenticationResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) throws Exception;
}
