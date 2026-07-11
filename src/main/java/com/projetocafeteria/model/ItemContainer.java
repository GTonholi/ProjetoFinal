package com.projetocafeteria.model;

import java.util.HashMap;
import java.util.Map;

public class ItemContainer<T extends Produto> {
    private final Map<T, Integer> itens = new HashMap<>();

    public void adicionar(T item, int quantidade) {
        itens.merge(item, quantidade, Integer::sum);
    }

    public double calcularTotal() {
        return itens.entrySet().stream()
                .mapToDouble(e -> e.getKey().getValor() * e.getValue())
                .sum();
    }

    public Map<T, Integer> getItens() {
        return itens;
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }
}
