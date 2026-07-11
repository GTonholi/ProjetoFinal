package com.projetocafeteria.model.bebida.builders;

import java.util.Scanner;

import com.projetocafeteria.model.DisponivelNoCardapio;
import com.projetocafeteria.model.bebida.Bebida;

/**
 * Builder contract for constructing a customized {@link Bebida} instance
 * through direct interaction with the customer.
 * <p>
 * Each concrete implementation is responsible for prompting the customer for any
 * relevant customizations via {@link #interagirComUsuario(Scanner)},
 * then producing the final, possibly decorated {@link Bebida} instance via {@link #construir()}.
 * <p>
 * 
 * Instances of this interface are obtained fresh for each customer
 * interaction, so builder implementations may safely hold mutable state
 * collected during {@link #interagirComUsuario(Scanner)}.
 * 
 */
public interface BebidaBuilder extends DisponivelNoCardapio {

    /**
     * Prompts the customer, via the given scanner, for any customizations
     * available for this drink, storing the customer's choices in this builder's internal state.
     *
     * @param scanner the shared scanner used to read the customer's input
     * @return this builder, allowing the call to be chained directly into
     *         {@link #construir()}
     * 
     */
    BebidaBuilder interagirComUsuario(Scanner scanner);

    /**
     * Produces the final {@link Bebida} instance reflecting the
     * customizations collected via {@link #interagirComUsuario(Scanner)}.
     * 
     * @return the constructed, ready-to-add-to-cart drink item
     */
    Bebida construir();
}