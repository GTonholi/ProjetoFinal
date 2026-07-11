package com.projetocafeteria.model.comida.builders;

import java.util.Scanner;

import com.projetocafeteria.model.DisponivelNoCardapio;
import com.projetocafeteria.model.comida.Comida;

/**
 * Builder contract for constructing a customized {@link Comida} instance
 * through direct interaction with the customer.
 * <p>
 * Each concrete implementation is responsible for prompting the customer for any
 * relevant customizations via {@link #interagirComUsuario(Scanner)},
 * then producing the final, possibly decorated {@link Comida} instance via {@link #construir()}.
 * <p>
 * 
 * Instances of this interface are obtained fresh for each customer
 * interaction, so builder implementations may safely hold mutable state
 * collected during {@link #interagirComUsuario(Scanner)}.
 * 
 */
public interface ComidaBuilder extends DisponivelNoCardapio {

    /**
     * Prompts the customer, via the given scanner, for any customizations
     * available for this food, storing the customer's choices in this builder's internal state.
     *
     * @param scanner the shared scanner used to read the customer's input
     * @return this builder, allowing the call to be chained directly into
     *         {@link #construir()}
     * 
     */
    ComidaBuilder interagirComUsuario(Scanner scanner);

    /**
     * Produces the final {@link Comida} instance reflecting the
     * customizations collected via {@link #interagirComUsuario(Scanner)}.
     * 
     * @return the constructed, ready-to-add-to-cart food item
     */
    Comida construir();
}