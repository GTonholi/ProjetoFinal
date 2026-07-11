package com.projetocafeteria.model.bebida.builders;

import java.util.List;

import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Suco;
import com.projetocafeteria.model.bebida.decorators.AcucarDecorator;
import com.projetocafeteria.model.bebida.decorators.SaborSucoDecorator;
import com.projetocafeteria.exception.ItemNaoEncontradoException;

public class SucoBuilder implements BebidaBuilder {
    private Bebida suco;

    public SucoBuilder() {
        this.suco = new Suco();
    }

    @Override
    public BebidaBuilder comSubopcao(String nomeSubopcao) {
        if (nomeSubopcao == null)
            return this;

        switch (nomeSubopcao) {
            case "Laranja":
            case "Abacaxi":
            case "Morango":
            case "Uva":
                this.suco = new SaborSucoDecorator(this.suco, nomeSubopcao);
                break;
            default:
                throw new ItemNaoEncontradoException("O sabor " + nomeSubopcao + " não existe para este suco.");
        }
        return this;
    }

    @Override
    public BebidaBuilder comAdicional(String nomeAdicional) {
        if (nomeAdicional == null)
            return this;

        if (nomeAdicional.equals("Com Açucar")) {
            this.suco = new AcucarDecorator(this.suco);
        } else {
            throw new ItemNaoEncontradoException("O adicional " + nomeAdicional + " não é válido para este suco.");
        }
        return this;
    }

    @Override
    public Bebida construir() {
        return this.suco;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Bebida base = new com.projetocafeteria.model.bebida.Suco();

        return new ItemCardapioInfo(
                base.exibirDescricao(),
                base.getValor(),
                List.of("Laranja", "Abacaxi", "Morango", "Uva"),
                List.of("Com Açucar"));
    }
}