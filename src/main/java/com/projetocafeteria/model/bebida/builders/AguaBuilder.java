package com.projetocafeteria.model.bebida.builders;

import java.util.List;

import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.decorators.ComGasDecorator;
import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.Agua;;

public class AguaBuilder implements BebidaBuilder {
    private Bebida agua;

    public AguaBuilder() {
        this.agua = new Agua();
    }

    @Override
    public BebidaBuilder comSubopcao(String nomeSubopcao) {
        if (nomeSubopcao == null)
            return this;

        switch (nomeSubopcao) {
            case "Sem Gás":
                break;
            case "Com Gás":
                this.agua = new ComGasDecorator(this.agua);
                break;
            default:
                throw new ItemNaoEncontradoException("A opção " + nomeSubopcao + " não existe para água.");
        }
        return this;
    }

    @Override
    public BebidaBuilder comAdicional(String nomeAdicional) {
        return this; // No add-ons for Agua
    }

    @Override
    public Bebida construir() {
        return this.agua;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Bebida base = new com.projetocafeteria.model.bebida.Agua();

        return new ItemCardapioInfo(
                base.exibirDescricao(),
                base.getValor(),
                List.of("Sem Gás", "Com Gás"),
                List.of());
    }
}