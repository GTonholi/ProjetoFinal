package com.projetocafeteria.model.bebida.decorator;

import com.projetocafeteria.model.bebida.Bebida;

public class LeiteDecorator extends BebidaDecorator {

    public LeiteDecorator(Bebida b) {
        super(b);
    }

    @Override
    public double getValor() {
        return bebidaDecorada.getValor() + 1.5;
    }

    @Override
    public String exibirDescricao() {
        return bebidaDecorada.exibirDescricao() + " com Leite";
    }
}