package com.projetocafeteria.view;

import java.util.List;
import com.projetocafeteria.model.Pedido;

/**
 * View responsible for displaying orders placed in the system.
 * <p>
 * This class handles only presentation, relying on a repository to
 * manage the actual order data.
 */
public class PainelPedidos {

    /**
     * Prints a given list of orders to the console.
     * 
     * @param pedidos the list of orders to display
     */
    public void exibir(List<Pedido> pedidos) {
        if (pedidos == null || pedidos.isEmpty()) {
            System.out.println("Nenhum pedido para exibir.");
            return;
        }

        System.out.println("=== Painel de Pedidos ===");
        for (Pedido pedido : pedidos) {
            imprimirPedido(pedido);
        }
    }

    /**
     * Prints a single order's summary line, including its id, customer
     * name, and current preparation status.
     *
     * @param pedido the order to print
     */
    private void imprimirPedido(Pedido pedido) {
        System.out.printf("\nPedido #" + pedido.getId() + " - Cliente: " + pedido.getNomeCliente() + " - Status: " + pedido.consultarPreparo());
    }
}

