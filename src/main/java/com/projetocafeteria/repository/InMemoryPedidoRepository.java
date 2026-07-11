package com.projetocafeteria.repository;

import java.util.ArrayList;
import java.util.List;
import com.projetocafeteria.model.Pedido;

/**
 * In-memory implementation of the {@link IPedidoRepository}.
 * <p>
 * This class stores orders in a standard {@link ArrayList}, making it suitable 
 * for runtime execution where data persistence across sessions is not required.
 * It encapsulates the list to ensure the single responsibility of data storage.
 */
public class InMemoryPedidoRepository implements IPedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();
    private int proximoId = 1;

    /**
     * Adds a new order to the internal list, assigning it a unique ID.
     * 
     * @param pedido the order to be stored
     */
    @Override
    public void adicionarPedido(Pedido pedido) {
        pedido.setId(proximoId++);
        pedidos.add(pedido);
    }

    /**
     * Filters the stored orders and returns only those that are still being processed.
     * It excludes orders that have been successfully delivered or cancelled.
     * 
     * @return a filtered list of {@link Pedido} instances currently in progress
     */
    @Override
    public List<Pedido> listarPedidosEmAndamento() {
        return pedidos.stream()
                .filter(p -> !p.pedidoEstaEntregue())
                .filter(p -> !p.estaCancelado())
                .toList();
    }

    /**
     * Looks up an order by its unique identifier using a linear search.
     * 
     * @param id the unique identifier of the target order
     * @return the {@link Pedido} matching the ID, or {@code null} if not found
     */
    @Override
    public Pedido buscarPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }
}
