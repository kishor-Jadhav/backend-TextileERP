package com.kj.textile.TextileERP.Exceptions;

public class JWTTokenException extends RuntimeException {
   public JWTTokenException(String message){
        super(message);
    }
}
