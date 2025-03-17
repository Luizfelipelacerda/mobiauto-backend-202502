package com.luizlacerda.mobiauto_backend_202502.exception;

public class UserDoesntExistException extends RuntimeException {

    public UserDoesntExistException(String message){
        super(message);
    }

}
