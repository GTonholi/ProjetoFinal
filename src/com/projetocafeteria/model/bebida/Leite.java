package com.projetocafeteria.model.bebida;

public class Leite extends BebidaDecorator{
    public Leite(Bebida b){
        super(b);
    }

    @Override
    public double getValor(){
        return (super.getValor() + 1.50);
    }

    @Override
    public String exibirDescricao(){
        return (super.exibirDescricao() + ", Leite");
    }
}
