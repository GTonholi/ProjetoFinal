package com.projetocafeteria.model.bebida.builders;

import java.util.List;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Cappuccino;
import com.projetocafeteria.model.bebida.decorators.AdicionalCappuccinoDecorator;

/**
 * Concrete Builder for creating {@link com.projetocafeteria.model.bebida.Cappuccino} objects.
 * <p>
 * Implements the {@link BebidaBuilder} interface to construct a customized cappuccino.
 * Customers can add specific toppings such as Cinnamon ("Canela") or 
 * Chocolate Powder ("Chocolate em Pó"). These additions are managed via the 
 * {@link CanelaDecorator} and {@link ChocolateDecorator}.
 */
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