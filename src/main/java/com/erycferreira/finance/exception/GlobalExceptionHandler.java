package com.erycferreira.finance.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(error -> error.getDefaultMessage())
                .orElse("Erro de validação");

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(Map.of(
                        "error", "validation_error",
                        "message", message
                ));
    }

    @ExceptionHandler(DateTimeOutLimitException.class)
    public ResponseEntity<?> handleRobotOut(DateTimeOutLimitException ex) {
        return ResponseEntity
                .badRequest()
                .body(Map.of(
                        "error", "invalid_datetime",
                        "message", ex.getMessage()
                ));
    }
}
