package com.projetocafeteria.model.comida.decorators;

import com.projetocafeteria.model.comida.Comida;

/**
 * Base class for the Decorator pattern applied to {@link Comida} items,
 * allowing optional add-ons to be layered on top of a base food item 
 * without altering its class hierarchy or requiring a combinatorial explosion
 * of subclasses for every possible combination of add-ons.
 * <p>
 * Concrete decorators extend this class and override {@link #getValor()} 
 * and {@link #exibirDescricao()} to add their own contribution 
 * (price increment, description suffix) on top of
 * the value returned by the wrapped {@link Comida} instance, combining the
 * result with their own addition.
 * <p>
 * By default, this class simply delegates to the wrapped instance, acting
 * as a transparent pass-through.
 * 
 */
public abstract class ComidaDecorator implements Comida{
    protected Comida comidaDecorada;

    /**
     * Creates a new decorator wrapping the given food item.
     *
     * @param c the food item to decorate
     */
    public ComidaDecorator(Comida c){
        this.comidaDecorada = c;
    }

    /**
     * Delegates to the wrapped item's value by default. Subclasses
     * override this to add their own price contribution.
     *
     * @return the wrapped item's price
     */
    @Override
    public double getValor(){
        return comidaDecorada.getValor();
    }

    /**
     * Delegates to the wrapped item's description by default. Subclasses
     * override this to append their own description.
     *
     * @return the wrapped item's description
     * 
     */
    @Override
    public String exibirDescricao(){
        return comidaDecorada.exibirDescricao();
    }
}
