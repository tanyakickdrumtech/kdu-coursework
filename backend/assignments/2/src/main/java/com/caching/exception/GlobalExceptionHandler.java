package com.caching.exception;


import com.caching.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * different exception methods that give different message according to the one which is invoked/called
     * @param ex
     * @return
     */
    @ExceptionHandler(value={ResourceNotFoundException.class})
    public ResponseEntity<ErrorDto> resourceException(ResourceNotFoundException ex){
        ErrorDto error=new ErrorDto(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value={BadRequestException.class})
    public static ResponseEntity<ErrorDto> badRequestException(BadRequestException ex){
        ErrorDto error=new ErrorDto(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ErrorDto> exception(Exception ex){
        ErrorDto error=new ErrorDto(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
