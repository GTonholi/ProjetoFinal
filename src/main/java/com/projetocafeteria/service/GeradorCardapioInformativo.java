package com.projetocafeteria.service;

import com.projetocafeteria.model.Cardapio;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.builders.BebidaBuilder;
import com.projetocafeteria.model.comida.builders.ComidaBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Service responsible for translating the raw menu builders held by
 * {@link Cardapio} into display-ready {@link ItemCardapioInfo} descriptors.
 * <p>
 * This class acts as an adapter between the menu's internal
 * representation (factories of {@link ComidaBuilder}/{@link BebidaBuilder}
 * instances, used to actually construct orderable items) and the
 * informative view layer ({@link com.projetocafeteria.view.VisualizadorCardapio}),
 * which only needs descriptive data.
 * 
 */
public class GeradorCardapioInformativo {

    private final Cardapio cardapio;

    public GeradorCardapioInformativo(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    /**
     * Builds descriptive information for every drink item available in
     * the menu.
     * <p>
     * For each available drink builder, a fresh instance is obtained from
     * its {@link Supplier} and queried for its commercial information; no
     * intermediate {@link com.projetocafeteria.model.bebida.Bebida}
     * instance is retained or returned.
     *
     * @return a list of descriptors for all available drink items; never
     *         {@code null}, may be empty if the menu has no drinks
     */
    public List<ItemCardapioInfo> obterBebidasInformativas() {
        List<ItemCardapioInfo> lista = new ArrayList<>();
        for (Supplier<BebidaBuilder> supplier : cardapio.getBebidasDisponiveis()) {
            lista.add(supplier.get().obterInformacaoComercial());
        }
        return lista;
    }

    /**
     * Builds descriptive information for every food item available in
     * the menu.
     * <p>
     * For each available food builder, a fresh instance is obtained from
     * its {@link Supplier} and queried for its commercial information; no
     * intermediate {@link com.projetocafeteria.model.comida.Comida}
     * instance is retained or returned.
     *
     * @return a list of descriptors for all available food items; never
     *         {@code null}, may be empty if the menu has no food
     */
    public List<ItemCardapioInfo> obterComidasInformativas() {
        List<ItemCardapioInfo> lista = new ArrayList<>();
        for (Supplier<ComidaBuilder> supplier : cardapio.getComidasDisponiveis()) {
            lista.add(supplier.get().obterInformacaoComercial());
        }
        return lista;
    }
}