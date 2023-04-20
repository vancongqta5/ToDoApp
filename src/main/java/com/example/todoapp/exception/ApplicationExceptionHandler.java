package com.example.todoapp.exception;

import com.example.todoapp.exception.passwordException.CurrentPasswordNotMatch;
import com.example.todoapp.exception.passwordException.ResetPasswordTokenNotValidException;
import com.example.todoapp.exception.userException.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {
//    @ExceptionHandler({UserNameExistException.class})
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public ErrorDetail handlerUserNameExistException(UserNameExistException ex){
//        return ex.getErrorDetail();
//    }
//    @ExceptionHandler(UserNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDetail handlerUserNotValidException(UserNotValidException ex){
//        return ex.getErrorDetail();
//    }
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

//    @ExceptionHandler(TaskListNameIsExisException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public ErrorDetail handlerTaskListNameIsExisException(TaskListNameIsExisException ex){
//        return ex.getErrorDetail();
//    }

//    @ExceptionHandler(TaskListNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDetail handlerTaskListNotValidException(TaskListNotValidException ex){
//        return ex.getErrorDetail();
//    }
//
//    @ExceptionHandler(TaskListNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorDetail handlerTaskListNotFoundException(TaskListNotFoundException ex){
//        return ex.getErrorDetail();
//    }

//    @ExceptionHandler(TaskNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    public ErrorDetail handlerTaskNotFoundException(TaskNotFoundException ex){
//        return ex.getErrorDetail();
//    }
//
//    @ExceptionHandler(TaskIsExistException.class)
//    @ResponseStatus(HttpStatus.CONFLICT)
//    public ErrorDetail handlerTaskIsExistException(TaskIsExistException ex){
//        return ex.getErrorDetail();
//    }
//    @ExceptionHandler(TaskIsNotValidException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorDetail handlerTaskIsNotValidException(TaskIsNotValidException ex){
//        return ex.getErrorDetail();
//    }

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
