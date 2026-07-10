package com.projetocafeteria.model.bebida.decorators;
import com.projetocafeteria.model.bebida.Bebida;

public class ComGasDecorator extends BebidaDecorator {

    public ComGasDecorator(Bebida b) {
        super(b);
    }

    @Override
    public String exibirDescricao() {
        return bebidaDecorada.exibirDescricao() + " com Gás";
    }
}