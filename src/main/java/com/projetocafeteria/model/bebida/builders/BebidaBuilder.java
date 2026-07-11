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
     * Customizes the drink item with a specific add-on (e.g., sugar, milk).
     * 
     * @param nomeAdicional the exact string name of the chosen add-on. 
     *                      If {@code null}, no add-on is applied.
     * @return the current builder instance for method chaining
     */
    BebidaBuilder comAdicional(String nomeAdicional);

    /**
     * Finalizes the building process and returns the fully constructed drink item.
     * 
     * @return the constructed {@link Bebida} instance, potentially wrapped in decorators
     */
    Bebida construir();
}