package com.example.analyticaldataquery;

import com.example.analyticaldataquery.exception.InvalidQueryException;
import com.example.analyticaldataquery.model.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = InvalidQueryException.class)
    public ResponseEntity<ErrorModel> handleCustomException(InvalidQueryException exception) {
        log.error("Invalid query: {} having an issue: {}", exception.getQuery(), exception.getMessage());
        return new ResponseEntity<>(new ErrorModel("INVALID_QUERY_400", "Please provide valid Query"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorModel> handleException(Exception exception) {
        log.error("Error with message: {}", exception.getMessage());
        return new ResponseEntity<>(new ErrorModel("UNKNOWN_ERROR_500", "Please contact development team"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
