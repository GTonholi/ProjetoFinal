package com.projetocafeteria.view;

import com.projetocafeteria.model.Pedido;
import java.util.ArrayList;
import java.util.List;

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
            imprimirPedido(pedido);
        }
    }

    public List<Pedido> listarPedidosEmAndamento() {
        return pedidos.stream()
                .filter(p -> !p.pedidoEstaEntregue())
                .filter(p -> !p.estaCancelado())
                .toList();
    }

    public Pedido buscarPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }

    private void imprimirPedido(Pedido pedido) {
        System.out.printf("\nPedido #"+ pedido.getId() +" - Cliente: "+ pedido.getNomeCliente() +" - Status:" + pedido.consultarPreparo());
    }
}
