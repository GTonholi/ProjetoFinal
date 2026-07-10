package com.projetocafeteria.model.comida.decorators;

import com.projetocafeteria.model.comida.Comida;

public class SaborBoloDecorator extends ComidaDecorator {
    private String sabor;

    public SaborBoloDecorator(Comida c, String sabor) {
        super(c);
        this.sabor = sabor;
    }

    @Override
    public String exibirDescricao() {
        return "Fatia de Bolo de " + sabor;
    }
}