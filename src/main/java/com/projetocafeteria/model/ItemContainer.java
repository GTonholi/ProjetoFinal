package com.projetocafeteria.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A generic, type-safe container that tracks quantities of items of a
 * given {@link Produto} subtype.
 * <p>
 * This class demonstrates the use of bounded parametric polymorphism
 * ({@code <T extends Produto>}) to eliminate code duplication between
 * otherwise near-identical structures, while preserving strict semantic
 * identity between them at compile time.
 * 
 *
 * @param <T> the type of {@link Produto} held by this container
 */
public class ItemContainer<T extends Produto> {
    private final Map<T, Integer> itens = new HashMap<>();

    /**
     * Adds a given quantity of an item to this container.
     * <p>
     * If the item is not yet present, it is inserted with the given
     * quantity; if it is already present, the quantity is added to the
     * existing amount.
     *
     * @param item       the item to add
     * @param quantidade the quantity to add for this item
     */
    public void adicionar(T item, int quantidade) {
        itens.merge(item, quantidade, Integer::sum);
    }

    /**
     * Calculates the total price of all items currently held in this
     * container, accounting for each item's quantity.
     *
     * @return the sum of {@code item.getValor() * quantity} across all
     *         items in this container
     */
    public double calcularTotal() {
        return itens.entrySet().stream()
                .mapToDouble(e -> e.getKey().getValor() * e.getValue())
                .sum();
    }

    /**
     * Returns the underlying map of items to their quantities.
     *
     * @return the internal item-to-quantity unmodifiable mapping; never {@code null},
     *         may be empty
     */
    public Map<T, Integer> getItens() {
        return Collections.unmodifiableMap(itens);
    }

    /**
     * Checks whether this container holds no items.
     *
     * @return {@code true} if no items have been added, {@code false} otherwise
     */
    public boolean estaVazio() {
        return itens.isEmpty();
    }
}
