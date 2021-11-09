package com.example.API.exceptions;

import com.example.API.models.ErrorValidation;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class RestApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ErrorValidation> errors = new ArrayList<>();
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        for(FieldError error:fieldErrors) {
            errors.add(new ErrorValidation(error.getField(), error.getDefaultMessage()));
        }

        return this.handleExceptionInternal(ex,errors,headers,status,request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path= ((MismatchedInputException)ex.getCause()).getPath().toString();
        ErrorValidation error = new ErrorValidation(path,ex.getCause().getMessage());
        return this.handleExceptionInternal(ex,error,headers,status,request);
    }
}
