package com.projetocafeteria.model.status;

import com.projetocafeteria.model.Pedido;

/**
 * Represents the payment/lifecycle state of a {@link Pedido} (State
 * pattern).
 * <p>
 * Each implementation defines how the order behaves when payment or
 * cancellation is attempted while in that particular state. This decouples the
 * order's state-transition rules from the order class itself.
 * <p>
 * The {@link #estaCancelado()} default method allows callers to query 
 * whether an order has been cancelled without needing to know
 * the concrete implementing class, avoiding any use of {@code instanceof}
 * or unsafe casting.
 */
public interface StatusPedido {

    /**
     * Attempts to process payment for the given order while it is in this
     * state.
     *
     * @param pedido the order attempting to be paid
     * @return {@code true} if payment was successfully processed in this
     *         state, {@code false} otherwise
     */
    public boolean pagar(Pedido pedido);

    /**
     * Attempts to cancel the given order while it is in this state.
     *
     * @param pedido the order attempting to be cancelled
     */
    public void cancelar(Pedido pedido);

    /**
     * Checks whether this state represents a cancelled order.
     * <p>
     * Defaults to {@code false}; only the cancelled-state implementation
     * overrides this to return {@code true}.
     *
     * @return {@code true} if this state represents cancellation,
     *         {@code false} otherwise
     */
    default boolean estaCancelado() {
        return false;
    }
}
