package com.projetocafeteria.exception;

/**
 * Thrown to indicate that a requested item could not be located.
 * <p>
 * 
 */
public class ItemNaoEncontradoException extends SistemaException {
    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}