package com.projetocafeteria.model.status;

import com.projetocafeteria.model.Pedido;

public interface StatusPedido {
    public boolean pagar(Pedido pedido);
    public void cancelar(Pedido pedido);

    default boolean estaCancelado() {
        return false;
    }
}
