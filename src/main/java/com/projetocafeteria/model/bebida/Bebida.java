package com.projetocafeteria.model.bebida;

import com.projetocafeteria.model.Produto;

/**
 * Marker interface representing a drink item sold at the cafeteria.
 * <p>
 * Extends {@link Produto} without adding any additional behavior; its
 * purpose is purely to establish a distinct, strictly-typed category
 * within the type system, so that generic structures can be parameterized
 * as {@code ItemContainer<Bebida>} and remain incompatible with
 * {@code ItemContainer<com.projetocafeteria.model.comida.Comida>} at
 * compile time.
 * <p>
 * Concrete drink items implement this interface directly, while {@code BebidaDecorator} implements it to
 * support the Decorator pattern for add-ons (e.g. {@code Leite}).
 */
public interface Bebida extends Produto{
    
}
