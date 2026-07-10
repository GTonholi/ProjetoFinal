package com.projetocafeteria.exception;

public class ItemNaoEncontradoException extends SistemaException {
    public ItemNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}