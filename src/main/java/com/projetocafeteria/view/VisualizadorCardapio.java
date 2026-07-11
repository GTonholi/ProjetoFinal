package com.projetocafeteria.view;

import java.util.List;

import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.service.GeradorCardapioInformativo;

/**
 * View responsible for rendering a human-readable, informative menu to the
 * console.
 * <p>
 * 
 * displays descriptive information (base price, available flavors/options,
 * and add-ons) for each menu item, sourced from a
 * {@link GeradorCardapioInformativo}.
 * <p>
 * 
 */
public class VisualizadorCardapio {

    /**
     * Prints the full informative menu to the console, including both
     * drink and food sections.
     *
     * @param gerador the source of informative menu data (drinks and food),
     *                used to retrieve the items to be displayed
     */
    public static void exibir(GeradorCardapioInformativo gerador) {
        System.out.println("\n==================================================");
        System.out.println("             CARDÁPIO INFORMATIVO             ");
        System.out.println("==================================================");

        System.out.println("\n[ BEBIDAS ]");
        renderizarItens(gerador.obterBebidasInformativas());

        System.out.println("\n[ COMIDAS ]");
        renderizarItens(gerador.obterComidasInformativas());
        System.out.println("==================================================");
    }

    /**
     * Renders a list of menu items to the console, printing each item's
     * name, base price, and, when present, its available sub-options
     * and add-ons.
     *
     * @param itens the list of menu item descriptors to render; items with
     *              no sub-options or add-ons simply omit those lines
     */
    private static void renderizarItens(List<ItemCardapioInfo> itens) {
        for (ItemCardapioInfo item : itens) {
            System.out.printf("- %s - Preço Base: R$ %.2f%n", item.getNome(), item.getPrecoBase());
            if (!item.getSubopcoes().isEmpty()) {
                System.out.println("  Opções/Sabores: " + String.join(", ", item.getSubopcoes()));
            }
            if (!item.getAdicionais().isEmpty()) {
                System.out.println("  Adicionais/Extras: " + String.join(", ", item.getAdicionais()));
            }
            System.out.println();
        }
    }
}