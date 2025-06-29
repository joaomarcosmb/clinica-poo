package org.poo.excecoes;

public class AgendaConflitanteException extends Exception {
    public AgendaConflitanteException(String message) {
        super(message);
    }
    
    public AgendaConflitanteException(String message, Throwable cause) {
        super(message, cause);
    }
} 