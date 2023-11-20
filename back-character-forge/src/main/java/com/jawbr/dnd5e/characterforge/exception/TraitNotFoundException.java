package com.jawbr.dnd5e.characterforge.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TraitNotFoundException extends RuntimeException {

    private String message;
}
