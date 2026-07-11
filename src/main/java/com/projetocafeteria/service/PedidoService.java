package com.projetocafeteria.service;

import java.util.List;
import java.util.function.Supplier;

import com.projetocafeteria.exception.QuantidadeInvalidaException;
import com.projetocafeteria.exception.RegraNegocioException;
import com.projetocafeteria.model.Cardapio;
import com.projetocafeteria.model.Cliente;
import com.projetocafeteria.model.Pedido;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.builders.BebidaBuilder;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.comida.builders.ComidaBuilder;
import com.projetocafeteria.model.pagamento.MetodoPagamento;
import com.projetocafeteria.repository.IPedidoRepository;
import com.projetocafeteria.view.VisualizadorCardapio;

/**
 * Service class responsible for orchestrating the creation and finalization
 * of an order ({@link Pedido}).
 * <p>
 * This class coordinates the domain models, using builders and strategies,
 * without knowing about I/O or the UI layer (no {@code Scanner} dependencies).
 * It acts as an intermediate controller between the Presentation layer
 * (e.g. {@code MenuCliente}) and the Domain/Repository layer.
 */
public class PedidoService {
    private final Cardapio cardapio;
    private final IPedidoRepository pedidoRepository;

    public PedidoService(IPedidoRepository pedidoRepository) {
        this.cardapio = new Cardapio();
        this.pedidoRepository = pedidoRepository;
    }

    public Cardapio getCardapio() {
        return this.cardapio;
    }

    public Pedido iniciarPedido(String nomeCliente) {
        return new Pedido(new Cliente(nomeCliente));
    }

    public void adicionarComida(Pedido pedido, int indiceBuilder, String subopcao, String adicional, int quantidade)
            throws RegraNegocioException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException(
                    "A quantidade solicitada (" + quantidade + ") é inválida. Deve ser maior que zero.");
        }

        List<Supplier<ComidaBuilder>> disponiveis = cardapio.getComidasDisponiveis();
        ComidaBuilder builder = disponiveis.get(indiceBuilder).get();
        Comida comida = builder.comSubopcao(subopcao).comAdicional(adicional).construir();

        pedido.adicionarComida(comida, quantidade);
    }

    public void adicionarBebida(Pedido pedido, int indiceBuilder, String subopcao, String adicional, int quantidade)
            throws RegraNegocioException {
        if (quantidade <= 0) {
            throw new QuantidadeInvalidaException(
                    "A quantidade solicitada (" + quantidade + ") é inválida. Deve ser maior que zero.");
        }

        List<Supplier<BebidaBuilder>> disponiveis = cardapio.getBebidasDisponiveis();
        BebidaBuilder builder = disponiveis.get(indiceBuilder).get();
        Bebida bebida = builder.comSubopcao(subopcao).comAdicional(adicional).construir();

        pedido.adicionarBebida(bebida, quantidade);
    }

    public boolean finalizarPedido(Pedido pedido, MetodoPagamento metodoPagamento) {
        if (pedido.getCarrinho().estaVazio()) {
            return false;
        }
        pedido.definirMetodoPagamento(metodoPagamento);
        boolean pago = pedido.realizarPagamento();

        if (pago) {
            pedidoRepository.adicionarPedido(pedido);
        }
        return pago;
    }

    public void mostrarCardapioInformativo() {
        GeradorCardapioInformativo gerador = new GeradorCardapioInformativo(this.cardapio);
        VisualizadorCardapio.exibir(gerador);
    }
}
