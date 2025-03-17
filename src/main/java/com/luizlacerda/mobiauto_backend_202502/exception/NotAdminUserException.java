package com.luizlacerda.mobiauto_backend_202502.exception;

public class NotAdminUserException extends IllegalAccessException{

    public  NotAdminUserException(String message){
        super(message);
    }
}
