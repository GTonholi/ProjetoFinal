package com.projetocafeteria.model.comida.builders;

import java.util.List;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.comida.PaoDeQueijo;
import com.projetocafeteria.model.comida.decorators.RecheioPaoDeQueijoDecorator;

public class PaoDeQueijoBuilder implements ComidaBuilder {
    private Comida paoDeQueijo;

    public PaoDeQueijoBuilder() {
        this.paoDeQueijo = new PaoDeQueijo();
    }

    @Override
    public ComidaBuilder comSubopcao(String nomeSubopcao) {
        return this; // No sub-options for Pão de Queijo
    }

    @Override
    public ComidaBuilder comAdicional(String nomeAdicional) {
        if (nomeAdicional == null)
            return this;

        if (nomeAdicional.equals("Recheado com Requeijão (+R$ 2,50)")) {
            this.paoDeQueijo = new RecheioPaoDeQueijoDecorator(this.paoDeQueijo);
        } else {
            throw new ItemNaoEncontradoException("O adicional " + nomeAdicional + " não é válido para pão de queijo.");
        }
        return this;
    }

    @Override
    public Comida construir() {
        return this.paoDeQueijo;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Comida base = new com.projetocafeteria.model.comida.PaoDeQueijo();

        return new ItemCardapioInfo(
                base.exibirDescricao(),
                base.getValor(),
                List.of(),
                List.of("Recheado com Requeijão (+R$ 2,50)"));
    }
}