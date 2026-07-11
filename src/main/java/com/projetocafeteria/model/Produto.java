package com.projetocafeteria.model;

/**
 * Represents a sellable item in the cafeteria's menu, whether food or
 * drink.
 * <p>
 * This is the common contract shared by {@link com.projetocafeteria.model.comida.Comida}
 * and {@link com.projetocafeteria.model.bebida.Bebida}, allowing generic
 * structures such as {@link ItemContainer} to operate uniformly over both
 * categories without knowing their concrete types.
 * 
 */
public interface Produto {
    double getValor();
    String exibirDescricao();
}
