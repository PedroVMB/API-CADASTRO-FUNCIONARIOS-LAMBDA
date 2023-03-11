package com.api.cadastrofuncionarios.lambda.utils;


public class InvalidCPFException extends RuntimeException {
    public InvalidCPFException(String message) {
        super(message);
    }
}