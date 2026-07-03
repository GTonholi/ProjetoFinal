package com.projetocafeteria.model.comida;

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
