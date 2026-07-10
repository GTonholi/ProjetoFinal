package com.projetocafeteria.model.comida.decorators;

import com.projetocafeteria.model.comida.Comida;

public class RecheioPaoDeQueijoDecorator extends ComidaDecorator {
    public RecheioPaoDeQueijoDecorator(Comida c) {
        super(c);
    }

    @Override
    public double getValor() {
        return comidaDecorada.getValor() + 2.50;
    }

    @Override
    public String exibirDescricao() {
        return comidaDecorada.exibirDescricao() + " Recheado com Requeijão";
    }
}