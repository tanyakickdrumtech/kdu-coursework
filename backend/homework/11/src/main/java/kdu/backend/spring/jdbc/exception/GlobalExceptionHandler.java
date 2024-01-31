package kdu.backend.spring.jdbc.exception;

import kdu.backend.spring.jdbc.dto.ErrorDto;
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
    @ExceptionHandler(value={MyCustomException.class})
    public ResponseEntity<ErrorDto> myCustomException(MyCustomException ex){
        ErrorDto error=new ErrorDto(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ErrorDto> exception(Exception ex){
        ErrorDto error=new ErrorDto(ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}

