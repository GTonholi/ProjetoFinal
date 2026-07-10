package com.projetocafeteria.model.bebida.decorators;

import com.projetocafeteria.model.bebida.Bebida;

public class AdicionalCappuccinoDecorator extends BebidaDecorator {
    private String adicional;

    public AdicionalCappuccinoDecorator(Bebida b, String adicional) {
        super(b);
        this.adicional = adicional;
    }

    @Override
    public double getValor() {
        return bebidaDecorada.getValor() + 1.00;
    }

    @Override
    public String exibirDescricao() {
        return bebidaDecorada.exibirDescricao() + " com " + adicional;
    }
}