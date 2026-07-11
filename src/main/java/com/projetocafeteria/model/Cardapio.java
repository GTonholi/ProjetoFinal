package com.projetocafeteria.model;

import java.util.ArrayList;
import java.util.Collections;
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

/**
 * Represents the cafeteria's menu: the fixed catalog of food and drink
 * items available for customers to order.
 * <p>
 * Rather than holding fixed instances of each item, this class stores
 * {@link Supplier} factories that produce a fresh {@link ComidaBuilder}
 * or {@link BebidaBuilder} on demand. Ensuring that each time a
 * customer selects an item, a brand-new builder instance is obtained,
 * avoiding any accidental sharing of mutable builder state between
 * different customers or different additions to the same cart.
 * <p>
 * Actual item construction and customization is delegated to
 * each builder via its own interaction flow (see
 * {@code ComidaBuilder#interagirComUsuario} and
 * {@code BebidaBuilder#interagirComUsuario}).
 */
public class Cardapio {
    private final List<Supplier<ComidaBuilder>> comidasDisponiveis = new ArrayList<>();
    private final List<Supplier<BebidaBuilder>> bebidasDisponiveis = new ArrayList<>();

    /**
     * Creates a new menu, populating it with the cafeteria's fixed catalog
     * of food and drink item builders.
     * 
     */
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

    /**
     * Returns the factories for all food item builders available in this
     * menu.
     *
     * @return the unmodifiable list of food builder factories; never {@code null}
     */
    public List<Supplier<ComidaBuilder>> getComidasDisponiveis() {
        return Collections.unmodifiableList(comidasDisponiveis);
    }

    /**
     * Returns the factories for all drink item builders available in this
     * menu.
     *
     * @return the unmodifiable list of drink builder factories; never {@code null}
     */
    public List<Supplier<BebidaBuilder>> getBebidasDisponiveis() {
        return Collections.unmodifiableList(bebidasDisponiveis);
    }
}
