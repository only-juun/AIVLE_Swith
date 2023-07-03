package swith.backend.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    USER_NOT_FOUND(404, "User not found"),
    USER_EMAIL_EXISTS(409, "Email address already in use"),
    USER_SERIAL_EXISTS(409, "Serial number already in use"),
    USER_PHONE_EXISTS(409, "Phone number already in use"),
    USER_NICKNAME_EXISTS(409, "Nickname already in use"),
    USER_SERIAL_NO_EXISTS(409,"Serial number is empty"),
    USER_INFO_NO_MATCH(409,"User info no match");


    private final int status;

    private final String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
