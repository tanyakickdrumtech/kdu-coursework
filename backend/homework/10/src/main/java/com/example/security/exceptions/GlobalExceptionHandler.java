package com.example.security.exceptions;

import com.example.security.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value={EmptyListException.class})
    public static ResponseEntity<ErrorDTO> invalidAddressException(EmptyListException ex){
        ErrorDTO error=new ErrorDTO(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={ResourceNotFoundException.class})
    public static ResponseEntity<ErrorDTO> resourceNotFound(UsernameNotFoundException ex){
        ErrorDTO error=new ErrorDTO(ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ErrorDTO> exception(Exception ex){
        ErrorDTO error=new ErrorDTO(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
