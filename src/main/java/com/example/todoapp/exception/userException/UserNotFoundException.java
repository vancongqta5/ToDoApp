package com.example.todoapp.exception.userException;

import com.example.todoapp.exception.ErrorDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserNotFoundException extends RuntimeException{
    private final ErrorDetail errorDetail;

    public UserNotFoundException(int errorCode, String message) {
        super();
        this.errorDetail = new ErrorDetail().builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
