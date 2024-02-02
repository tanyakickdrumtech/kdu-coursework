package com.example.springassessment2.exception;

import com.example.springassessment2.dto.ErrorDTO;
import com.example.springassessment2.exception.custom.MyCustomException;
import com.example.springassessment2.exception.custom.ShiftUserDeletionInvalid;
import com.example.springassessment2.exception.custom.ShiftUserNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Global exception handler class for handling exceptions across the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Exception handler for MyCustomException.
     *
     * @param ex The MyCustomException to handle.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(value = {MyCustomException.class})
    public ResponseEntity<ErrorDTO> handleCustomException(MyCustomException ex){
        ErrorDTO error = new ErrorDTO(ex.getMessage() + " [MyCustomException]", HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ResponseBody
    @ExceptionHandler(ShiftUserNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleShiftUserNotFoundException(ShiftUserNotFound ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ShiftUserDeletionInvalid.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleShiftUserInvalidDeletionException(ShiftUserDeletionInvalid ex) {
        return ex.getMessage();
    }
}
