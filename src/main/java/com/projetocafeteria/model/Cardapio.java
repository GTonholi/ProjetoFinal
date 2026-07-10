package com.projetocafeteria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.projetocafeteria.model.bebida.builders.AguaBuilder;
import com.projetocafeteria.model.bebida.builders.BebidaBuilder;
import com.projetocafeteria.model.bebida.builders.CafeBuilder;
import com.projetocafeteria.model.bebida.builders.CappuccinoBuilder;
import com.projetocafeteria.model.bebida.builders.SucoBuilder;
import com.projetocafeteria.model.comida.builders.BoloBuilder;
import com.projetocafeteria.model.comida.builders.ComidaBuilder;
import com.projetocafeteria.model.comida.builders.MistoQuenteBuilder;
import com.projetocafeteria.model.comida.builders.PaoDeQueijoBuilder;
import com.projetocafeteria.model.comida.builders.TortaBuilder;

public class Cardapio {
    private final List<Supplier<ComidaBuilder>> comidasDisponiveis = new ArrayList<>();
    private final List<Supplier<BebidaBuilder>> bebidasDisponiveis = new ArrayList<>();

    public Cardapio() {
        comidasDisponiveis.add(BoloBuilder::new);
        comidasDisponiveis.add(TortaBuilder::new);
        comidasDisponiveis.add(MistoQuenteBuilder::new);
        comidasDisponiveis.add(PaoDeQueijoBuilder::new);
        
        bebidasDisponiveis.add(CafeBuilder::new);
        bebidasDisponiveis.add(SucoBuilder::new);
        bebidasDisponiveis.add(AguaBuilder::new);
        bebidasDisponiveis.add(CappuccinoBuilder::new);
    }

    public List<Supplier<ComidaBuilder>> getComidasDisponiveis() {
        return comidasDisponiveis;
    }

    public List<Supplier<BebidaBuilder>> getBebidasDisponiveis() {
        return bebidasDisponiveis;
    }
}
