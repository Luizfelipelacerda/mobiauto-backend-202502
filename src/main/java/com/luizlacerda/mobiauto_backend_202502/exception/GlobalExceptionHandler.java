package com.luizlacerda.mobiauto_backend_202502.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CannotCreateUpdateUserException.class)
    public ResponseEntity<String> handleCannotCreateUpdateUserException(CannotCreateUpdateUserException ex) {
        return ResponseEntity
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserDoesntExistException.class)
    public ResponseEntity<String> handleUserDoesntExistException(UserDoesntExistException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(CnpjNotValidException.class)
    public ResponseEntity<String> handleCnpjNotValidException(CnpjNotValidException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
    @ExceptionHandler(NotAdminUserException.class)
    public ResponseEntity<String> handleNotAdminUserException(NotAdminUserException ex){
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserOrOportunidadeDoesntExistException.class)
    public ResponseEntity<String> handleUserOrOportunidadeDoesntExistException(UserOrOportunidadeDoesntExistException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(RevendaNotFoundException.class)
    public ResponseEntity<String> handleRevendaNotFoundException(RevendaNotFoundException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

    @ExceptionHandler(UserNOtAuthorizedToAttendOportunity.class)
    public ResponseEntity<String> handleUserNOtAuthorizedToAttendOportunity(UserNOtAuthorizedToAttendOportunity ex){
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ex.getMessage());
    }
    @ExceptionHandler(NoCargoFoundException.class)
    public ResponseEntity<String> handleNoCargoFoundException(NoCargoFoundException ex){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }

}
