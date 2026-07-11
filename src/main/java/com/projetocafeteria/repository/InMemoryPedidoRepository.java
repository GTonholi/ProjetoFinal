package com.projetocafeteria.repository;

import java.util.ArrayList;
import java.util.List;
import com.projetocafeteria.model.Pedido;

/**
 * In-memory implementation of the IPedidoRepository.
 */
public class InMemoryPedidoRepository implements IPedidoRepository {
    private final List<Pedido> pedidos = new ArrayList<>();

    @Override
    public void adicionarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    @Override
    public List<Pedido> listarPedidosEmAndamento() {
        return pedidos.stream()
                .filter(p -> !p.pedidoEstaEntregue())
                .filter(p -> !p.estaCancelado())
                .toList();
    }

    @Override
    public Pedido buscarPorId(int id) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == id) {
                return pedido;
            }
        }
        return null;
    }
}
