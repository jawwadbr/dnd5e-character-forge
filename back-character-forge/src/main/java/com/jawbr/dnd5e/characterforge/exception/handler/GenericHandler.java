package com.jawbr.dnd5e.characterforge.exception.handler;

import com.jawbr.dnd5e.characterforge.exception.IntegrityConstraintViolationException;
import com.jawbr.dnd5e.characterforge.exception.errorResponse.ErrorResponse;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Objects;

@ControllerAdvice
public class GenericHandler {

    @ExceptionHandler(PropertyReferenceException.class)
    public ResponseEntity<ErrorResponse> handleException(PropertyReferenceException exc, WebRequest request) {
        String endpointPath = request.getDescription(false);
        if (endpointPath.startsWith("uri=")) {
            endpointPath = endpointPath.substring(4);
        }
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(), endpointPath);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exc, WebRequest request) {
        String endpointPath = request.getDescription(false);
        if (endpointPath.startsWith("uri=")) {
            endpointPath = endpointPath.substring(4);
        }
        BindingResult result = exc.getBindingResult();
        String message = Objects.requireNonNull(result.getFieldError()).getDefaultMessage();
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, endpointPath);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // This exception handlers below are used when creating and updating an entity that already exists
    // In the future I might add endpoints to create and update, hence why I added this handlers
    @ExceptionHandler(IntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(IntegrityConstraintViolationException exc, WebRequest request) {
        String endpointPath = request.getDescription(false);
        if (endpointPath.startsWith("uri=")) {
            endpointPath = endpointPath.substring(4);
        }
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), exc.getMessage(), endpointPath);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleException(SQLIntegrityConstraintViolationException exc, WebRequest request) {
        String endpointPath = request.getDescription(false);
        if (endpointPath.startsWith("uri=")) {
            endpointPath = endpointPath.substring(4);
        }
        ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), "Duplicated entity. Cannot create or update because the entity already exists.", endpointPath);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
}
