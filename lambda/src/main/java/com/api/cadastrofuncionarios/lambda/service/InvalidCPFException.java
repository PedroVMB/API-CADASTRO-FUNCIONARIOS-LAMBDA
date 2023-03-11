package com.api.cadastrofuncionarios.lambda.service;


public class InvalidCPFException extends RuntimeException {
    public InvalidCPFException(String message) {
        super(message);
    }
}