package com.projetocafeteria.model.comida;

public class MistoQuente implements Comida{

    @Override
    public double getValor(){
        return 15.0;
    }

    @Override
    public String exibirDescricao(){
        return "Misto Quente";
    }
}
