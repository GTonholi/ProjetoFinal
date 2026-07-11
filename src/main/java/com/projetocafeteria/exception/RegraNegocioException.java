package com.projetocafeteria.exception;

/**
 * Base class for checked exceptions representing violations of business
 * rules within the cafeteria system.
 * <p>
 * 
 */
public class RegraNegocioException extends Exception{
    public RegraNegocioException(String mensagem){
        super(mensagem);
    }
}
