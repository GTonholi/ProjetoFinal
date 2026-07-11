package com.projetocafeteria.model.pagamento;

/**
 * Represents a payment method that can process a transaction of a given
 * value (Strategy pattern).
 * <p>
 * Implementations encapsulate how a payment is actually carried
 * out, allowing {@link com.projetocafeteria.model.Pedido} and
 * {@link com.projetocafeteria.model.status.StatusPedido} implementations
 * to trigger payment without knowing which concrete method was chosen.
 * Keeping payment processing fully decoupled from order state transitions.
 * 
 */
public interface MetodoPagamento {

     /**
     * Attempts to process a payment of the given amount using this
     * payment method.
     *
     * @param valor the amount to be charged
     * @return {@code true} if the payment was successfully processed,
     *         {@code false} otherwise
     */
    boolean realizarPagamento(double valor);

    /**
     * Returns a human-readable description of this payment method, used
     * to present it as an option to the customer.
     *
     * @return the payment method's display description (e.g. "Pix",
     *         "Cartão de Crédito")
     */
    String getDescricao();
}
