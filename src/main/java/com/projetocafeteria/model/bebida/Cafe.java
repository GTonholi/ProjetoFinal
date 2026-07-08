package com.projetocafeteria.model.bebida;

public class Cafe implements Bebida {

    @Override
    public double getValor(){
        return 5.0;
    }

    @Override
    public String exibirDescricao(){
        return "Café";
    }

}
