package com.projetocafeteria.model.status;

import com.projetocafeteria.model.Pedido;

public class StatusCancelado implements StatusPedido{
    @Override
    public void pagar(Pedido pedido){
        System.out.println("Pedido cancelado. Não é possível realizar o pagamento.");
    }

    @Override 
    public void cancelar(Pedido pedido){
        System.out.println("Pedido já está cancelado.");
    }

    @Override
    public boolean estaCancelado() {
        return true;
    }
}
