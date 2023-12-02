package com.jawbr.dnd5e.characterforge.exception.handler;

import com.jawbr.dnd5e.characterforge.exception.ClassNotFoundException;
import com.jawbr.dnd5e.characterforge.exception.errorResponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ClassExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(ClassNotFoundException exc, WebRequest request) {
        String endpointPath = request.getDescription(false);
        if(endpointPath.startsWith("uri=")) {
            endpointPath = endpointPath.substring(4);
        }
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), endpointPath);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
