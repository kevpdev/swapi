package fr.kevpdev.swapi.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorDetail {

    private Date timestamp;
    private int statusCode;
    private String message;

    public ErrorDetail(int statusCode, String message) {
        this.timestamp = new Date();
        this.statusCode = statusCode;
        this.message = message;
    }
}
