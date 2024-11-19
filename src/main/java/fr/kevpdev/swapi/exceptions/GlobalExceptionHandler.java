package fr.kevpdev.swapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorDetail> handleWebClientResponseException(WebClientResponseException ex) {
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        String message = ex.getMessage();

        ErrorDetail errorDetail = new ErrorDetail(status.value(), message);

        return ResponseEntity.status(status).body(errorDetail);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetail> handleGenericException(Exception ex) {
        String message = "Erreur inattendue : " + ex.getMessage();
        ErrorDetail errorDetail = new ErrorDetail(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetail);
    }
}
