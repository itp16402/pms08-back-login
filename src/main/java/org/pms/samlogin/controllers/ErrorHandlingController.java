package org.pms.samlogin.controllers;

import org.pms.samlogin.exceptions.ErrorResponse;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
public class ErrorHandlingController implements ErrorController {

    private static final String ERROR_MAPPING = "/error";

    @RequestMapping(value = ERROR_MAPPING)
    public ResponseEntity<?> error(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        Integer statusCode = null;
        String errorMessage = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        if (!ObjectUtils.isEmpty(status)){

            statusCode = Integer.valueOf(status.toString());

            if (!ObjectUtils.isEmpty(errorMessage) && errorMessage.contains("JWT"))
                statusCode = HttpStatus.UNAUTHORIZED.value();
        }

        return ResponseEntity.status(HttpStatus.valueOf(statusCode)).body(ErrorResponse.builder()
                .errorCode(statusCode)
                .status(HttpStatus.valueOf(statusCode))
                .message(errorMessage)
                .build());
    }
}
