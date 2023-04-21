package com.example.todoapp.exception.userException;

import com.example.todoapp.exception.ErrorDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserNotValidException extends RuntimeException{
    private final ErrorDetail errorDetail;

    public UserNotValidException(int errorCode, String message) {
        super();
        this.errorDetail = new ErrorDetail().builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
