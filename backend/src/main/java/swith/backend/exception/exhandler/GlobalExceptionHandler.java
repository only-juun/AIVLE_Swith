package swith.backend.exception.exhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import swith.backend.exception.ErrorResponse;
import swith.backend.exception.UserException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> UserHandle(UserException e) {
        ErrorResponse response = new ErrorResponse(e.getExceptionCode());
      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
