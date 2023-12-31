package com.jawbr.dnd5e.characterforge.exception.errorResponse;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String message;
    private String timeStamp;
    private String path;

    public ErrorResponse(int status, String message, String path) {
        this.status = status;
        this.message = message;
        this.timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a"));
        this.path = path;
    }
}
