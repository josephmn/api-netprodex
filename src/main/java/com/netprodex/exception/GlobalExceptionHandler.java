package com.netprodex.exception;

import com.netprodex.persistence.payload.MessageError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest webRequest) {
        Map<String, String> mapError = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
                    String clave = ((FieldError) error).getField();
                    String valor = error.getDefaultMessage();
                    mapError.put(clave, valor);
                }
        );
        MessageError response = new MessageError(
                mapError.toString(),
                webRequest.getDescription(false),
                new ArrayList<>()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // NOT FOUND 404
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<MessageError> handleMethodArgumentNoValidException(ResourceNotFoundException exception, WebRequest webRequest) {
        MessageError response = new MessageError(
                exception.getMessage(),
                webRequest.getDescription(false),
                new ArrayList<>()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    // BAD REQUEST 400
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<MessageError> handleBadRequestException(BadRequestException exception, WebRequest webRequest) {
        MessageError response = new MessageError(
                exception.getMessage(),
                webRequest.getDescription(false),
                new ArrayList<>()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // ERROR 500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageError> handlerException(Exception exception,
                                                         WebRequest webRequest) {
        MessageError apiResponse = new MessageError(
                exception.getMessage(),
                webRequest.getDescription(false),
                new ArrayList<>()
        );
        return new ResponseEntity<>(apiResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
