package com.projetocafeteria.model.comida;

public class PaoDeQueijo implements Comida {
    @Override
    public double getValor() {
        return 5.00;
    }

    @Override
    public String exibirDescricao() {
        return "Pão de Queijo";
    }
}