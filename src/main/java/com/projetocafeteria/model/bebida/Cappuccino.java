package com.projetocafeteria.model.bebida;

public class Cappuccino implements Bebida {
    @Override
    public double getValor() {
        return 9.00;
    }

    @Override
    public String exibirDescricao() {
        return "Cappuccino Tradicional";
    }
}