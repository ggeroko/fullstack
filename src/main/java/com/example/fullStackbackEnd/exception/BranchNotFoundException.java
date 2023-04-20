package com.example.fullStackbackEnd.exception;

public class BranchNotFoundException extends RuntimeException{
    public BranchNotFoundException(Long id){
        super("Could not found the user with id"+id);

    }
}