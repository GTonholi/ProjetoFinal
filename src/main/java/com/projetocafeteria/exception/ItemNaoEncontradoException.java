package com.projetocafeteria.exception;

public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}