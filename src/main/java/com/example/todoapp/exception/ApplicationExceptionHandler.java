package com.example.todoapp.exception;

import com.example.todoapp.exception.passwordException.CurrentPasswordNotMatch;
import com.example.todoapp.exception.passwordException.InvalidPasswordException;
import com.example.todoapp.exception.passwordException.ResetPasswordTokenNotValidException;
import com.example.todoapp.exception.userException.ResourceExistException;
import com.example.todoapp.exception.userException.UserNotFoundException;
import com.example.todoapp.exception.userException.UserNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
    @ExceptionHandler({ResourceExistException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorDetail handlerUserNameExistException(ResourceExistException ex){
        return ex.getErrorDetail();
    }
    @ExceptionHandler(UserNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handlerUserNotValidException(UserNotValidException ex){
        return ex.getErrorDetail();
    }
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetail handlerNotFoundException(UserNotFoundException ex){
        return ex.getErrorDetail();
    }
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handlerNotFoundException(InvalidPasswordException ex){
        return ex.getErrorDetail();
    }

    @ExceptionHandler(TaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDetail handlerTaskNotFoundException(TaskNotFoundException ex){
        return ex.getErrorDetail();
    }

    @ExceptionHandler(CurrentPasswordNotMatch.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handlerCurrentPasswordNotMatch(CurrentPasswordNotMatch ex){
        return ex.getErrorDetail();
    }

    @ExceptionHandler(ResetPasswordTokenNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDetail handlerResetPasswordTokenNotValidException(ResetPasswordTokenNotValidException ex){
        return ex.getErrorDetail();
    }
}
