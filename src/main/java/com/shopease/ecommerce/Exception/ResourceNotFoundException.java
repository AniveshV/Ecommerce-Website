package com.shopease.ecommerce.Exception;

import org.springframework.stereotype.Component;


public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
