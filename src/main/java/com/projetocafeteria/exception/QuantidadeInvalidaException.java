package com.projetocafeteria.exception;

/**
 * Thrown to indicate that an invalid quantity was provided for a cart
 * operation (e.g. adding an item with a zero, negative, or non-numeric
 * quantity).
 * <p>
 * 
 */
public class QuantidadeInvalidaException extends RegraNegocioException {
    public QuantidadeInvalidaException(String mensagem) {
        super(mensagem);
    }
}