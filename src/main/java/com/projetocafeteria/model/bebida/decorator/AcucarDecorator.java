package com.projetocafeteria.model.bebida.decorator;

import com.projetocafeteria.model.bebida.Bebida;

public class AcucarDecorator extends BebidaDecorator {

    public AcucarDecorator(Bebida b) {
        super(b);
    }

    @Override
    public String exibirDescricao() {
        return bebidaDecorada.exibirDescricao() + " com Açúcar";
    }
}