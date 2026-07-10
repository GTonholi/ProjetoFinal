package com.projetocafeteria.model.bebida.decorator;

import com.projetocafeteria.model.bebida.Bebida;

public class SaborSucoDecorator extends BebidaDecorator {
    private String sabor;

    public SaborSucoDecorator(Bebida b, String sabor) {
        super(b);
        this.sabor = sabor;
    }

    @Override
    public String exibirDescricao() {
        return bebidaDecorada.exibirDescricao() + " de " + sabor;
    }
}