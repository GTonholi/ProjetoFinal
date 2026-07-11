package com.projetocafeteria.model.comida.builders;

import java.util.List;
import com.projetocafeteria.model.comida.Torta;
import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.comida.decorators.SaborTortaDecorator;

/**
 * Concrete Builder for creating {@link com.projetocafeteria.model.comida.Torta} objects.
 * <p>
 * Implements the {@link ComidaBuilder} interface to construct a customized pie.
 * Requires a mandatory filling/flavor (e.g., Chicken, Hearts of Palm, Apple) 
 * via {@link SaborTortaDecorator}. It currently does not support additional 
 * toppings or extras.
 */
public class TortaBuilder implements ComidaBuilder {
    private Comida torta;

    public TortaBuilder() {
        this.torta = new Torta();
    }

    @Override
    public ComidaBuilder comSubopcao(String nomeSubopcao) {
        if (nomeSubopcao == null)
            return this;

        switch (nomeSubopcao) {
            case "Frango":
            case "Palmito":
            case "Maçã":
                this.torta = new SaborTortaDecorator(this.torta, nomeSubopcao);
                break;
            default:
                throw new ItemNaoEncontradoException("O sabor " + nomeSubopcao + " não existe para esta torta.");
        }
        return this;
    }

    @Override
    public ComidaBuilder comAdicional(String nomeAdicional) {
        return this; // No add-ons for Torta
    }

    @Override
    public Comida construir() {
        return this.torta;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Comida base = new com.projetocafeteria.model.comida.Torta();

        return new ItemCardapioInfo(
                base.exibirDescricao(),
                base.getValor(),
                List.of("Frango", "Palmito", "Maçã"),
                List.of());
    }
}