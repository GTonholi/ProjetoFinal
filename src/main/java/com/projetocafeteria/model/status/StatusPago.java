package com.projetocafeteria.model.status;

import com.projetocafeteria.model.Pedido;

public class StatusPago implements StatusPedido{
    
    @Override
    public boolean pagar(Pedido pedido){
        System.out.println("O pedido já está pago");
        return true;
    }

    @Override 
    public void cancelar(Pedido pedido){
        System.out.println("Reembolsando o valor do pedido.");
        pedido.setStatus(new StatusCancelado());
    }
}
