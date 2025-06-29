package org.poo.excecoes;

public class ProntuarioInvalidoException extends Exception {
    public ProntuarioInvalidoException(String message) {
        super(message);
    }
    
    public ProntuarioInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
} 