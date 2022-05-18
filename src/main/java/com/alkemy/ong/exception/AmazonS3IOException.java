package com.alkemy.ong.exception;

public class AmazonS3IOException extends RuntimeException{

    public AmazonS3IOException(String message){
        super(message);
    }
}
