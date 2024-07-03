package com.shopease.ecommerce.Exception;

public class ResourceExistsException extends RuntimeException{
    public ResourceExistsException(String message){
        super(message);
    }

}
