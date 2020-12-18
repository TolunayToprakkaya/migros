package com.project.migros.controller.exception;

import com.project.migros.base.exception.ApplicationError;
import com.project.migros.base.exception.CourierLocationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(CourierLocationException.class)
  public ResponseEntity<ApplicationError> handleCourierIsSameLocationException(CourierLocationException e, WebRequest webRequest) {
    ApplicationError applicationError = new ApplicationError();
    applicationError.setCode(101);
    applicationError.setMessage(e.getMessage()); //"Courier is the same place"

    return new ResponseEntity<>(applicationError, HttpStatus.ALREADY_REPORTED);
  }
}
