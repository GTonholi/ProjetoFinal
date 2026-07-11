package com.projetocafeteria.model.comida.builders;

import java.util.List;

import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.comida.MistoQuente;

/**
 * Concrete Builder for creating
 * {@link com.projetocafeteria.model.comida.MistoQuente} objects.
 * <p>
 * Implements the {@link ComidaBuilder} interface for constructing a hot
 * sandwich.
 * Currently, this item does not support extra flavors or toppings, so both
 * customization methods simply return the builder without applying any
 * decorators.
 */
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