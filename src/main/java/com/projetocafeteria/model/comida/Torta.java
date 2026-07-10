package com.projetocafeteria.model.comida;

public class Torta implements Comida {
    @Override
    public double getValor() {
        return 12.0;
    }

    @Override
    public String exibirDescricao() {
        return "Torta";
    }
}