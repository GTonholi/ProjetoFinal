package com.projetocafeteria.model.comida;

import com.projetocafeteria.model.Produto;

/**
 * Marker interface representing a food item sold at the cafeteria.
 * <p>
 * Extends {@link Produto} without adding any additional behavior; its
 * purpose is purely to establish a distinct, strictly-typed category
 * within the type system, so that generic structures can be parameterized
 * as {@code ItemContainer<Comida>} and remain incompatible with
 * {@code ItemContainer<com.projetocafeteria.model.bebida.Bebida>} at
 * compile time.
 * <p>
 * Concrete food items implement this interface directly, while {@code ComidaDecorator} implements it to
 * support the Decorator pattern for add-ons (e.g. {@code Cobertura}).
 */
public interface Comida extends Produto{
}
