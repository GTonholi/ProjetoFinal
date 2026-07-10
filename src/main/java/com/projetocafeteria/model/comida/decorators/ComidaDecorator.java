package com.projetocafeteria.model.comida.decorators;

import com.projetocafeteria.model.comida.Comida;

public abstract class ComidaDecorator implements Comida{
    protected Comida comidaDecorada;

    public ComidaDecorator(Comida c){
        this.comidaDecorada = c;
    }

    @Override
    public double getValor(){
        return comidaDecorada.getValor();
    }

    @Override
    public String exibirDescricao(){
        return comidaDecorada.exibirDescricao();
    }
}
