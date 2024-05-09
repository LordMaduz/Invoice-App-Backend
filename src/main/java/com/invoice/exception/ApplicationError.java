package com.invoice.exception;

public class ApplicationError extends RuntimeException{
    public ApplicationError(final String message){
        super(message);
    }
}
