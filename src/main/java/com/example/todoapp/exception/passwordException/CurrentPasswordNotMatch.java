package com.example.todoapp.exception.passwordException;

import com.example.todoapp.exception.ErrorDetail;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CurrentPasswordNotMatch extends RuntimeException{
    private final ErrorDetail errorDetail;

    public CurrentPasswordNotMatch(int errorCode, String message) {
        super();
        this.errorDetail = new ErrorDetail().builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }
}
