package com.projetocafeteria.model;

import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.comida.Comida;

/**
 * Represents the shopping cart of a single {@link Pedido}, holding the
 * food and drink items selected by the customer.
 * <p>
 * Internally, food and drink items are kept in two separate
 * {@link ItemContainer} instances, parameterized respectively over
 * {@link Comida} and {@link Bebida}. This delegates all quantity-tracking,
 * totaling, and emptiness-check logic to {@link ItemContainer}, avoiding
 * code duplication.
 * 
 */
public class Carrinho {
    private final ItemContainer<Bebida> bebidas = new ItemContainer<>();
    private final ItemContainer<Comida> comidas = new ItemContainer<>();

    /**
     * Adds a given quantity of a drink item to this cart.
     *
     * @param bebida the drink to add
     * @param quant  the quantity to add
     */
    public void adicionarBebida(Bebida bebida, int quant){
       bebidas.adicionar(bebida, quant);
    }

    /**
     * Adds a given quantity of a food item to this cart.
     *
     * @param comida the food item to add
     * @param quant  the quantity to add
     */
    public void adicionarComida(Comida comida, int quant){
       comidas.adicionar(comida, quant);
    }

    /**
     * Calculates the total price of this cart, combining both food and
     * drink items.
     *
     * @return the sum of the food total and the drink total
     */
    public double calcularTotal(){
        return bebidas.calcularTotal() + comidas.calcularTotal();
    }

    /**
     * Checks whether this cart has no items at all, neither food nor
     * drinks.
     *
     * @return {@code true} if both the food and drink containers are
     *         empty, {@code false} otherwise
     */
    public boolean estaVazio() {
        return comidas.estaVazio() && bebidas.estaVazio();
    }
}
