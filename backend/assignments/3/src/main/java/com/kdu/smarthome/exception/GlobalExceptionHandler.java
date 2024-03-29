package com.kdu.smarthome.exception;


import com.kdu.smarthome.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {DeviceAlreadyInRoomException.class})
    public ResponseEntity<ErrorDto> deviceAlreadyInRoomException(DeviceAlreadyInRoomException ex){
        ErrorDto error = new ErrorDto(ex.getMessage() + " [DeviceAlreadyInRoomException]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {DeviceNotFoundException.class})
    public ResponseEntity<ErrorDto> deviceNotFoundException(DeviceNotFoundException ex){
        ErrorDto error = new ErrorDto(ex.getMessage() + " [DeviceNotFoundException]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {HouseNotFoundException.class})
    public ResponseEntity<ErrorDto> houseNotFoundException(HouseNotFoundException ex){
        ErrorDto error = new ErrorDto(ex.getMessage() + " [HouseNotFoundException]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {RoomNotFoundException.class})
    public ResponseEntity<ErrorDto> roomNotFoundException(RoomNotFoundException ex){
        ErrorDto error = new ErrorDto(ex.getMessage() + " [RoomNotFoundException]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorDto> userNotFoundException(UserNotFoundException ex){
        ErrorDto error = new ErrorDto(ex.getMessage() + " [UserNotFoundException]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UsernameAlreadyExistsException.class})
    public ResponseEntity<ErrorDto> usernameAlreadyExistsException(UsernameAlreadyExistsException ex){
        ErrorDto error = new ErrorDto(ex.getMessage() + " [UsernameAlreadyExistsException]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {EmailAlreadyExistsException.class})
    public ResponseEntity<ErrorDto> emailAlreadyExistsException(EmailAlreadyExistsException ex){
        ErrorDto error = new ErrorDto(ex.getMessage() + " [UsernameAlreadyExistsException]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {UserNotAuthorizedException.class})
    public ResponseEntity<ErrorDto> userNotAuthorizedException(UserNotAuthorizedException ex){
        ErrorDto error = new ErrorDto(ex.getMessage() + " [UserNotAuthorized]", HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }


    /**
     * Exception handler for all kinds of exceptions.
     *
     * @param ex The exception to handle.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ErrorDto> exception(Exception ex){
        ErrorDto error = new ErrorDto(ex.getMessage() + " [Parent Exception]", HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

