package com.projetocafeteria.model.comida.decorators;

import com.projetocafeteria.model.comida.Comida;

public class SaborTortaDecorator extends ComidaDecorator {
    private String sabor;

    public SaborTortaDecorator(Comida c, String sabor) {
        super(c);
        this.sabor = sabor;
    }

    @Override
    public String exibirDescricao() {
        return "Torta de " + sabor;
    }
}