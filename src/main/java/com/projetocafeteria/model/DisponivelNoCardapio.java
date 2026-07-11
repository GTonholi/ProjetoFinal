package com.projetocafeteria.model;

/**
 * Contract for menu-item builders that can describe themselves for
 * display purposes, independent of how the actual orderable item
 * (e.g. {@code Comida} or {@code Bebida}) is constructed.
 * 
 */
public interface DisponivelNoCardapio{
    ItemCardapioInfo obterInformacaoComercial();
}