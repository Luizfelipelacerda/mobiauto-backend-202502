package com.luizlacerda.mobiauto_backend_202502.exception;

public class UserOrOportunidadeDoesntExistException extends RuntimeException{
    public UserOrOportunidadeDoesntExistException(String message){
        super(message);
    }
}
