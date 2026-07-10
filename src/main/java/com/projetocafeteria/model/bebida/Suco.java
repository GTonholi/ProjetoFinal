package com.projetocafeteria.model.bebida;

public class Suco implements Bebida {

    @Override
    public double getValor(){
        return 8.0;
    }

    @Override
    public String exibirDescricao(){
        return "Suco";
    }

}
