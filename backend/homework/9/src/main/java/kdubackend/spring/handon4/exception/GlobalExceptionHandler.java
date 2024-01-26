package kdubackend.spring.handon4.exception;


import kdubackend.spring.handon4.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value={ResourceNotFoundException.class})
    public ResponseEntity<ErrorDto> resourceException(ResourceNotFoundException ex){
        ErrorDto error=new ErrorDto(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value={BadRequestException.class})
    public ResponseEntity<ErrorDto> badRequestException(BadRequestException ex){
        ErrorDto error=new ErrorDto("Bad Request Exception during runtime",HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value={Exception.class})
    public ResponseEntity<ErrorDto> exception(Exception ex){
        ErrorDto error=new ErrorDto(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
