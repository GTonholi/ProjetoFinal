package com.projetocafeteria.model;

import java.util.List;

/**
 * Immutable data-transfer descriptor holding the commercial (display-only)
 * information of a menu item, decoupled from its actual orderable
 * representation.
 * <p>
 * Instances of this class are produced by builders and consumed
 * by {@code com.projetocafeteria.service.GeradorCardapioInformativo}
 * and {@code com.projetocafeteria.view.VisualizadorCardapio} to render the
 * informative menu, without those classes needing any knowledge of how
 * items are actually built or ordered.
 * 
 */
public class ItemCardapioInfo {
    private final String nome;
    private final double precoBase;
    private final List<String> subopcoes;
    private final List<String> adicionais;

    /**
     * Creates a new immutable menu item descriptor.
     *
     * @param nome       the item's display name
     * @param precoBase  the item's base price, before any add-ons
     * @param subopcoes  the item's available sub-options (e.g. flavors);
     *                   may be empty if the item has none
     * @param adicionais the item's available add-ons (e.g. extras); may
     *                   be empty if the item has none
     */
    public ItemCardapioInfo(String nome, double precoBase, List<String> subopcoes, List<String> adicionais) {
        this.nome = nome;
        this.precoBase = precoBase;
        this.subopcoes = subopcoes;
        this.adicionais = adicionais;
    }

    /* getters */
    public String getNome() { return nome; }
    public double getPrecoBase() { return precoBase; }
    public List<String> getSubopcoes() { return subopcoes; }
    public List<String> getAdicionais() { return adicionais; }
}