package com.projetocafeteria.model.comida.builders;

import java.util.List;

import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.comida.MistoQuente;

public class MistoQuenteBuilder implements ComidaBuilder {
    @Override
    public ComidaBuilder comSubopcao(String nomeSubopcao) {
        return this; // No sub-options for Misto Quente
    }

    @Override
    public ComidaBuilder comAdicional(String nomeAdicional) {
        return this; // No add-ons for Misto Quente
    }

    @Override
    public Comida construir() {
        return new MistoQuente();
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Comida base = new com.projetocafeteria.model.comida.MistoQuente();

        return new ItemCardapioInfo(
                base.exibirDescricao(),
                base.getValor(),
                List.of(),
                List.of());
    }
}