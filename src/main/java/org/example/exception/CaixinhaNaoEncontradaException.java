package org.example.exception;

public class CaixinhaNaoEncontradaException extends RuntimeException {
    public CaixinhaNaoEncontradaException(String message) {
        super(message);
    }
}
