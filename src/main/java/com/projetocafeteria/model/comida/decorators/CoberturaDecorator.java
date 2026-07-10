package com.projetocafeteria.model.comida.decorators;

import com.projetocafeteria.model.comida.Comida;

public class CoberturaDecorator extends ComidaDecorator{
    public CoberturaDecorator(Comida c){
        super(c);
    }

    @Override
    public double getValor(){
        return (super.getValor() + 3.0);
    }

    @Override
    public String exibirDescricao(){
        return (super.exibirDescricao() + ", com Cobertura");
    }
}
