package com.projetocafeteria.repository;

import java.util.List;
import com.projetocafeteria.model.Pedido;

/**
 * Interface that defines the contract for an order repository.
 */
public interface IPedidoRepository {

    /**
     * Saves a new order to the repository.
     * 
     * @param pedido the order to save
     */
    void adicionarPedido(Pedido pedido);

    /**
     * Retrieves all orders that are currently in progress (not delivered or
     * cancelled).
     * 
     * @return a list of orders that are still in progress
     */
    List<Pedido> listarPedidosEmAndamento();

    /**
     * Retrieves an order by its unique identifier.
     * 
     * @param id the identifier of the order to retrieve
     * @return the order with the specified identifier, or null if not found
     */
    Pedido buscarPorId(int id);
}
