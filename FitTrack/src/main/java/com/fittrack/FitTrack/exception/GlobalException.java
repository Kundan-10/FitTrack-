package com.fittrack.FitTrack.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Error> userException(UserException userEx, WebRequest web){
        Error error = new Error(LocalDateTime.now(),userEx.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ChallengeException.class)
    public ResponseEntity<Error> challengeException(ChallengeException ex, WebRequest web){
        Error error = new Error(LocalDateTime.now(),ex.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotificationException.class)
    public ResponseEntity<Error> notificationException(NotificationException ex, WebRequest web){
        Error error = new Error(LocalDateTime.now(),ex.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> validatedException(MethodArgumentNotValidException validEx, WebRequest web){
        Error error = new Error(LocalDateTime.now(),validEx.getMessage(),validEx.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Error> noHandler(NoHandlerFoundException nohandler, WebRequest web){
        Error error = new Error(LocalDateTime.now(),nohandler.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Map<String, String>> jwtExcetpion(JwtException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid JWT token");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> authenticationException(AuthenticationException ex){
        Map<String, String> response = new HashMap<>();
        response.put("error", "Invalid JWT token");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> exception(Exception e,WebRequest web){
        Error error = new Error(LocalDateTime.now(),e.getMessage(), web.getDescription(false));
        return new ResponseEntity<>(error,HttpStatus.EXPECTATION_FAILED);
    }
}
