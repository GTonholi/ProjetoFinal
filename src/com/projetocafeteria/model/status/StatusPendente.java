package com.projetocafeteria.model.status;

import com.projetocafeteria.model.Pedido;

public class StatusPendente implements StatusPedido{

    @Override
    public void pagar(Pedido pedido){
        System.out.println("Pedido pago!");
        pedido.setStatus(new StatusPago());
    }

    @Override
    public void cancelar(Pedido pedido){
        System.out.println("Pedido cancelado com sucesso!");
        pedido.setStatus(new StatusCancelado());
    }
}
