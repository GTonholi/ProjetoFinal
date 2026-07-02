package com.projetocafeteria.model.status;

import com.projetocafeteria.model.Pedido;

public interface StatusPedido {
    public void pagar(Pedido pedido);
    public void cancelar(Pedido pedido);
}
