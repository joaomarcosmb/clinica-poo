package org.poo.excecoes;

public class MedicoSemEspecialidadeException extends Exception {
    public MedicoSemEspecialidadeException(String message) {
        super(message);
    }
    
    public MedicoSemEspecialidadeException(String message, Throwable cause) {
        super(message, cause);
    }
} 