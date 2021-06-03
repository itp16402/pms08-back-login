package org.pms.samlogin.controllers;

import lombok.extern.slf4j.Slf4j;
import org.pms.samlogin.dto.UserDto;
import org.pms.samlogin.exceptions.ErrorResponse;
import org.pms.samlogin.exceptions.UnacceptableActionException;
import org.pms.samlogin.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@Slf4j
@RequestMapping(value = "/register")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> register(@RequestBody UserDto userDto) {

        log.info("Register user {}", userDto.getUsername());

        registrationService.registerMember(userDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ExceptionHandler(UnacceptableActionException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode(HttpStatus.NOT_ACCEPTABLE.value())
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }
}
