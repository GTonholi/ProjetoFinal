package com.projetocafeteria.exception;

public class SistemaException extends RuntimeException{
    public SistemaException(String mensagem){
        super(mensagem);
    }
}
