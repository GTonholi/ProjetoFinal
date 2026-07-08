package com.projetocafeteria.model;

import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Cafe;
import com.projetocafeteria.model.comida.Bolo;
import com.projetocafeteria.model.comida.Comida;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class Cardapio {
private final List<Supplier<Comida>> comidasDisponiveis = new ArrayList<>();
    private final List<Supplier<Bebida>> bebidasDisponiveis = new ArrayList<>();

    public Cardapio() {
        comidasDisponiveis.add(Bolo::new);
        
        bebidasDisponiveis.add(Cafe::new);
    }

    public List<Supplier<Comida>> getComidasDisponiveis() {
        return comidasDisponiveis;
    }

    public List<Supplier<Bebida>> getBebidasDisponiveis() {
        return bebidasDisponiveis;
    }
}
