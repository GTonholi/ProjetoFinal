package com.projetocafeteria.service;

import com.projetocafeteria.model.Cardapio;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.builders.BebidaBuilder;
import com.projetocafeteria.model.comida.builders.ComidaBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class GeradorCardapioInformativo {

    private final Cardapio cardapio;

    public GeradorCardapioInformativo(Cardapio cardapio) {
        this.cardapio = cardapio;
    }

    public List<ItemCardapioInfo> obterBebidasInformativas() {
        List<ItemCardapioInfo> lista = new ArrayList<>();
        for (Supplier<BebidaBuilder> supplier : cardapio.getBebidasDisponiveis()) {
            lista.add(supplier.get().obterInformacaoComercial());
        }
        return lista;
    }

    public List<ItemCardapioInfo> obterComidasInformativas() {
        List<ItemCardapioInfo> lista = new ArrayList<>();
        for (Supplier<ComidaBuilder> supplier : cardapio.getComidasDisponiveis()) {
            lista.add(supplier.get().obterInformacaoComercial());
        }
        return lista;
    }
}