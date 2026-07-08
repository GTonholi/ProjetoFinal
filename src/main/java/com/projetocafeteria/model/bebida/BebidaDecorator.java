package com.projetocafeteria.model.bebida;

public abstract class BebidaDecorator implements Bebida{
    protected Bebida bebidaDecorada;

    public BebidaDecorator(Bebida b){
        this.bebidaDecorada = b;
    }

    @Override
    public double getValor(){
        return bebidaDecorada.getValor();
    }

    @Override
    public String exibirDescricao(){
        return bebidaDecorada.exibirDescricao();
    }
}
