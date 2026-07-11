package com.projetocafeteria.exception;

/**
 * Base class for all custom, unchecked exceptions used throughout the
 * cafeteria system.
 * <p>
 * 
 */
public class SistemaException extends RuntimeException{
    public SistemaException(String mensagem){
        super(mensagem);
    }
}
