package com.projetocafeteria.view;

import java.util.ArrayList;
import java.util.List;

import com.projetocafeteria.model.Pedido;

/**
 * View responsible for tracking and displaying all orders placed in the
 * system.
 * <p>
 * Acts as the single in-memory registry of {@link Pedido} instances,
 * providing lookup and filtering operations.
 * <p>
 * This class does not persist data across application executions; all
 * orders are lost once the program terminates.
 */
public class PainelPedidos {
    private final List<Pedido> pedidos = new ArrayList<>();

    /**
     * Registers a new order in the panel.
     *
     */
    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    /**
     * Prints all registered orders to the console.
     * 
     */
    public void exibir(){
         if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado ainda.");
            return;
        }

        System.out.println("=== Painel de Pedidos ===");
        for (Pedido pedido : pedidos) {
            imprimirPedido(pedido);
        }
    }


    /**
     * Returns the orders that are still in progress, orders that have
     * neither been fully delivered nor cancelled.
     * <p>
     *
     * @return an immutable list of orders that are neither delivered nor
     *         cancelled; never {@code null}, may be empty
     */
    public List<Pedido> listarPedidosEmAndamento() {
        return pedidos.stream()
                .filter(p -> !p.pedidoEstaEntregue())
                .filter(p -> !p.estaCancelado())
                .toList();
    }

    /**
     * Searches for an order by its unique identifier.
     *
     * @param id the identifier of the order to look for
     * @return the matching {@link Pedido}, or {@code null} if no order with
     *         the given id has been registered
     */
    public Pedido buscarPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

     /**
     * Prints a single order's summary line, including its id, customer
     * name, and current preparation status.
     *
     * @param pedido the order to print
     */
    private void imprimirPedido(Pedido pedido) {
        System.out.printf("\nPedido #"+ pedido.getId() +" - Cliente: "+ pedido.getNomeCliente() +" - Status:" + pedido.consultarPreparo());
    }
}
