package swith.backend.exception;

import lombok.Getter;

public class UserException extends RuntimeException {

    @Getter
    private final ExceptionCode exceptionCode;

    public UserException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
}
