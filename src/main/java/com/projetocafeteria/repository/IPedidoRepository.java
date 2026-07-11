package com.projetocafeteria.repository;

import java.util.List;
import com.projetocafeteria.model.Pedido;

/**
 * Interface that defines the contract for an order repository.
 * <p>
 * This interface abstracts the underlying storage mechanism for {@link Pedido}
 * instances, adhering to the Dependency Inversion Principle (DIP) and
 * ensuring that services do not depend on concrete persistence classes.
 */
public interface IPedidoRepository {

    /**
     * Saves a new order into the repository.
     * 
     * @param pedido the {@link Pedido} instance to be persisted
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

    /**
     * Retrieves all orders, regardless of their current status.
     * 
     * @return a list of all orders
     */
    List<Pedido> listarTodosPedidos();
}
