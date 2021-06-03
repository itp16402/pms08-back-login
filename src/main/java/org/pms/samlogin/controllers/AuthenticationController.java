package org.pms.samlogin.controllers;

import lombok.extern.slf4j.Slf4j;
import org.pms.samlogin.dto.authenticate.AuthenticationRequest;
import org.pms.samlogin.exceptions.AuthenticationFailedException;
import org.pms.samlogin.exceptions.ErrorResponse;
import org.pms.samlogin.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping(value = "/authenticate")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createAuthenticateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        log.info("Authenticate user {}", authenticationRequest.getUsername());

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @ExceptionHandler(AuthenticationFailedException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {

        ErrorResponse authenticationErrorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_ACCEPTABLE.value())
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(authenticationErrorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
