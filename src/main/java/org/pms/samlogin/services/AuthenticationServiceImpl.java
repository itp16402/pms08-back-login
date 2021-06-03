package org.pms.samlogin.services;

import lombok.extern.slf4j.Slf4j;
import org.pms.samlogin.config.security.JwtTokenProvider;
import org.pms.samlogin.domain.User;
import org.pms.samlogin.dto.authenticate.AuthenticationRequest;
import org.pms.samlogin.dto.authenticate.AuthenticationResponse;
import org.pms.samlogin.enums.AuthenticationStatus;
import org.pms.samlogin.exceptions.AuthenticationFailedException;
import org.pms.samlogin.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@PropertySource(value = "classpath:application.properties")
public class AuthenticationServiceImpl implements AuthenticationService {

    private MyUserDetailsService userDetailsService;
    private UserRepository userRepository;
    private JwtTokenProvider jwtTokenProvider;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationServiceImpl(MyUserDetailsService userDetailsService,
                                     UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
                                     AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        log.info("Authentication processs begins");

        authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                authenticationRequest.getPassword()));

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenProvider.generateToken(userDetails.getUsername());

        Optional<User> user = userRepository.findByUsername(authenticationRequest.getUsername());

        log.info("Authentication processs completed");

        return AuthenticationResponse.builder()
                .jwt(jwt)
                .username(userDetails.getUsername())
                .email(user.map(User::getEmail).orElse(null))
                .authorities(new HashSet<>(userDetails.getAuthorities()))
                .authenticationStatus(AuthenticationStatus.AUTHENTICATION_SUCCEEDED)
                .build();
    }

    private void authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken){

        try {

            authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        }
        catch (BadCredentialsException e){

            throw new AuthenticationFailedException("Incorrect username or password", e);
        }
    }
}
