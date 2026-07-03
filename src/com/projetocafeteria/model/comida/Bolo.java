package com.projetocafeteria.model.comida;

public class Bolo implements Comida{

    @Override
    public double getValor(){
        return 10.0;
    }

    @Override
    public String exibirDescricao(){
        return "Fatia de Bolo";
    }
}
