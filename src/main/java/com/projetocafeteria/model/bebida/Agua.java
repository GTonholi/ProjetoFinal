package com.projetocafeteria.model.bebida;

public class Agua implements Bebida {

    @Override
    public double getValor(){
        return 4.0;
    }

    @Override
    public String exibirDescricao(){
        return "Água";
    }

}
