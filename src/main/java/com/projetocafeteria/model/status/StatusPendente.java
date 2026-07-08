package com.projetocafeteria.model.status;

import com.projetocafeteria.model.Pedido;

public class StatusPendente implements StatusPedido{

    @Override
    public boolean pagar(Pedido pedido){
        boolean sucesso = pedido.validarPagamento();

        if(sucesso){
            pedido.setStatus(new StatusPago());
            pedido.avancarPreparo();
        } 
        else{
            System.out.println("Pagamento não foi concluído. Pedido continua pendente.");
        }
        return sucesso;
    }

    @Override
    public void cancelar(Pedido pedido){
        System.out.println("Pedido cancelado com sucesso!");
        pedido.setStatus(new StatusCancelado());
    }
}
