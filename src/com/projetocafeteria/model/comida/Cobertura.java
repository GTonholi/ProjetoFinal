package com.projetocafeteria.model.comida;

public class Cobertura extends ComidaDecorator{
    public Cobertura(Comida c){
        super(c);
    }

    @Override
    public double getValor(){
        return (super.getValor() + 3.0);
    }

    @Override
    public String exibirDescricao(){
        return (super.exibirDescricao() + ", Cobertura");
    }
}
