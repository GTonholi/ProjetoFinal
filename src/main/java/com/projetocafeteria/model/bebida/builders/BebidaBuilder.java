package com.projetocafeteria.model.bebida.builders;

import com.projetocafeteria.model.DisponivelNoCardapio;
import com.projetocafeteria.model.bebida.Bebida;

/**
 * Builder contract for constructing a customized {@link Bebida} instance.
 * <p>
 * Customizations are passed as strings (e.g. from a UI layer) without the 
 * domain needing to know about I/O devices (like Scanner).
 */
public interface BebidaBuilder extends DisponivelNoCardapio {

    /**
     * Applies a sub-option (e.g., flavor or type) to the drink being built.
     * 
     * @param nomeSubopcao the chosen sub-option's name
     * @return this builder
     */
    BebidaBuilder comSubopcao(String nomeSubopcao);

    /**
     * Applies an add-on (e.g., extra topping or milk) to the drink being built.
     * 
     * @param nomeAdicional the chosen add-on's name
     * @return this builder
     */
    BebidaBuilder comAdicional(String nomeAdicional);

    /**
     * Produces the final {@link Bebida} instance reflecting the applied customizations.
     * 
     * @return the constructed, ready-to-add-to-cart drink item
     */
    Bebida construir();
}