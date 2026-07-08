package com.projetocafeteria.view;

import java.util.ArrayList;
import java.util.List;

import com.projetocafeteria.model.Pedido;

public class PainelPedidos {
    private final List<Pedido> pedidos = new ArrayList<>();

    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void exibir(){
         if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido registrado ainda.");
            return;
        }

        System.out.println("=== Painel de Pedidos ===");
        for (Pedido pedido : pedidos) {
            System.out.printf("Pedido #"+ pedido.getId() +" - Cliente: "+ pedido.getNomeCliente() +" - Status:" + pedido.consultarPreparo());
        }
    }
}
