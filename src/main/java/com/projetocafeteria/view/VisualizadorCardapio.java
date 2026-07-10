package com.projetocafeteria.view;

import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.service.GeradorCardapioInformativo;
import java.util.List;

public class VisualizadorCardapio {

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