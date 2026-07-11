package com.projetocafeteria.model.bebida.builders;

import java.util.List;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Cafe;
import com.projetocafeteria.model.bebida.decorators.LeiteDecorator;

public class CafeBuilder implements BebidaBuilder {
    private Bebida cafe;

    public CafeBuilder() {
        this.cafe = new Cafe();
    }

    @Override
    public BebidaBuilder comSubopcao(String nomeSubopcao) {
        if (nomeSubopcao == null)
            return this;

        if (!nomeSubopcao.equals("Preto")) {
            throw new ItemNaoEncontradoException("O sabor " + nomeSubopcao + " não existe para este café.");
        }
        return this;
    }

    @Override
    public BebidaBuilder comAdicional(String nomeAdicional) {
        if (nomeAdicional == null)
            return this;

        if (nomeAdicional.equals("Leite (+R$ 1,50)")) {
            this.cafe = new LeiteDecorator(this.cafe);
        } else {
            throw new ItemNaoEncontradoException("O adicional " + nomeAdicional + " não é válido para este café.");
        }
        return this;
    }

    @Override
    public Bebida construir() {
        return this.cafe;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Bebida base = new com.projetocafeteria.model.bebida.Cafe();

        return new ItemCardapioInfo(
                base.exibirDescricao(),
                base.getValor(),
                List.of("Preto"),
                List.of("Leite (+R$ 1,50)"));
    }
}