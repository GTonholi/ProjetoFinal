package com.projetocafeteria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.projetocafeteria.model.bebida.builder.AguaBuilder;
import com.projetocafeteria.model.bebida.builder.BebidaBuilder;
import com.projetocafeteria.model.bebida.builder.CafeBuilder;
import com.projetocafeteria.model.bebida.builder.SucoBuilder;
import com.projetocafeteria.model.comida.Bolo;
import com.projetocafeteria.model.comida.Comida;

public class Cardapio {
    private final List<Supplier<Comida>> comidasDisponiveis = new ArrayList<>();
    private final List<Supplier<BebidaBuilder>> bebidasDisponiveis = new ArrayList<>();
    public Cardapio() {
        comidasDisponiveis.add(Bolo::new);
        
        bebidasDisponiveis.add(CafeBuilder::new);
        bebidasDisponiveis.add(SucoBuilder::new);
        bebidasDisponiveis.add(AguaBuilder::new);
    }

    public List<Supplier<Comida>> getComidasDisponiveis() {
        return comidasDisponiveis;
    }

    public List<Supplier<BebidaBuilder>> getBebidasDisponiveis() {
        return bebidasDisponiveis;
    }
}
