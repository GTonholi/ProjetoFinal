package com.projetocafeteria.model;

/**
 * Represents a customer placing an order at the cafeteria.
 * <p>
 * This is a minimal, immutable value object: unlike {@link Funcionario},
 * customers do not authenticate and are not persisted across sessions —
 * a new {@code Cliente} instance is created each time a customer starts
 * placing an order.
 * 
 */
public class Cliente {
    private final String nome;

    /**
     * The customer's name, as provided when placing an order.
     */
    public Cliente(String nome) {
        this.nome = nome;
    }
    
    /**
     * Returns the customer's name.
     *
     * @return the customer's name
     */
    public String getNome(){
        return nome;
    }
}