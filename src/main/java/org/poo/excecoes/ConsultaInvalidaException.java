package org.poo.excecoes;

public class ConsultaInvalidaException extends Exception {
    public ConsultaInvalidaException(String message) {
        super(message);
    }
    
    public ConsultaInvalidaException(String message, Throwable cause) {
        super(message, cause);
    }
} 