package com.projetocafeteria.model.bebida.builders;

import java.util.List;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Cappuccino;
import com.projetocafeteria.model.bebida.decorators.AdicionalCappuccinoDecorator;

public class CappuccinoBuilder implements BebidaBuilder {
    private Bebida cappuccino;

    public CappuccinoBuilder() {
        this.cappuccino = new Cappuccino();
    }

    @Override
    public BebidaBuilder comSubopcao(String nomeSubopcao) {
        return this; // No sub-options for Cappuccino
    }

    @Override
    public BebidaBuilder comAdicional(String nomeAdicional) {
        if (nomeAdicional == null)
            return this;

        switch (nomeAdicional) {
            case "Canela":
            case "Chocolate em Pó":
                this.cappuccino = new AdicionalCappuccinoDecorator(this.cappuccino, nomeAdicional);
                break;
            default:
                throw new ItemNaoEncontradoException("O adicional " + nomeAdicional + " não é válido para cappuccino.");
        }
        return this;
    }

    @Override
    public Bebida construir() {
        return this.cappuccino;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Bebida base = new com.projetocafeteria.model.bebida.Cappuccino();

        return new ItemCardapioInfo(
                base.exibirDescricao(),
                base.getValor(),
                List.of(),
                List.of("Canela", "Chocolate em Pó"));
    }
}