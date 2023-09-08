package com.jawbr.dnd5e.characterforge.exception.handler;

import com.jawbr.dnd5e.characterforge.exception.AbilityScoreNotFoundException;
import com.jawbr.dnd5e.characterforge.exception.errorResponse.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AbilityScoreExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(AbilityScoreNotFoundException exc) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
