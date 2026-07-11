package com.projetocafeteria.service;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.exception.QuantidadeInvalidaException;
import com.projetocafeteria.model.Cardapio;
import com.projetocafeteria.model.Cliente;
import com.projetocafeteria.model.Pedido;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.builders.BebidaBuilder;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.pagamento.CartaoCredito;
import com.projetocafeteria.model.pagamento.CartaoDebito;
import com.projetocafeteria.model.pagamento.Dinheiro;
import com.projetocafeteria.model.pagamento.MetodoPagamento;
import com.projetocafeteria.model.pagamento.Pix;
import com.projetocafeteria.view.PainelPedidos;
import com.projetocafeteria.view.VisualizadorCardapio;

public class PedidoService{       
    private final Scanner sc;
    private final Cardapio cardapio;

    public PedidoService(Scanner sc) {
        this.sc = sc;
        this.cardapio = new Cardapio();
    }

    public void realizarPedido(PainelPedidos painelPedidos) {
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine().trim();
        Cliente cliente = new Cliente(nome);
        Pedido pedido = new Pedido(cliente);

        boolean continuar = true;
        while (continuar) {
            System.out.println("\n[1] Adicionar comida");
            System.out.println("[2] Adicionar bebida");
            System.out.println("[3] Finalizar pedido");
            System.out.println("[4] Cancelar Pedido");
            System.out.print("Opção: ");

            String opcao = sc.nextLine().trim();
            try {
                switch (opcao) {
                    case "1" -> adicionarComida(pedido);
                    case "2" -> adicionarBebida(pedido);
                    case "3" -> {
                        if (pedido.getCarrinho().estaVazio()) {
                            System.out.println("Seu carrinho está vazio! Adicione itens antes de pagar.");
                        } else {
                            continuar = false;
                        }
                    }
                    case "4" -> {
                        pedido.cancelarPedido();
                        return;
                    }
                    default -> System.out.println("Opção inválida! Escolha de 1 a 4.");
                }
            } catch (ItemNaoEncontradoException e) {
                System.out.println("\n[OPÇÃO INVÁLIDA] " + e.getMessage());
            }
        }

        escolherMetodoPagamento(pedido);
        
        if (pedido.estaCancelado()) {
            System.out.println("Pedido cancelado. Voltando ao menu inicial...");
            return;
        }

        boolean pago = pedido.realizarPagamento();
        
        if(pago){
            painelPedidos.adicionarPedido(pedido);
            System.out.printf("Pedido #"+ pedido.getId() +" finalizado! Total: R$ %.2f%n",  pedido.calcularTotal());
        }
        else{
            System.out.println("Não foi possível concluir o pagamento. Pedido não foi registrado.");
        }
    }

    private void adicionarComida(Pedido pedido) {
        List<Supplier<com.projetocafeteria.model.comida.builders.ComidaBuilder>> disponiveis = cardapio.getComidasDisponiveis();

        if (disponiveis.isEmpty()) {
            System.out.println("Nenhuma comida disponível no cardápio.");
            return;
        }

        System.out.println("Escolha a comida:");
        for (int i = 0; i < disponiveis.size(); i++) {
            com.projetocafeteria.model.comida.builders.ComidaBuilder builderInicial = disponiveis.get(i).get();
            Comida itemBase = builderInicial.construir();
            System.out.printf("["+ (i+1) +"] "+ itemBase.exibirDescricao() +" - R$ %.2f%n", itemBase.getValor());
        }

        int indice = lerOpcaoNumerica(disponiveis.size());
        
        if (indice < 1 || indice > disponiveis.size()) {
            throw new ItemNaoEncontradoException("Erro Crítico: O índice " + indice + " não corresponde a nenhuma comida mapeada no cardápio.");
        }
        
        com.projetocafeteria.model.comida.builders.ComidaBuilder builderEscolhido = disponiveis.get(indice - 1).get();

        try {
            Comida comida = builderEscolhido.interagirComUsuario(sc).construir();
            
            int quantidade = lerQuantidade();
            
            pedido.getCarrinho().adicionarComida(comida, quantidade);
            System.out.println("Adicionado: " + comida.exibirDescricao() + " x" + quantidade);
            
        } catch (QuantidadeInvalidaException e) {
            System.out.println("\n[ERRO DE NEGÓCIO] " + e.getMessage());
            System.out.println("Operação cancelada. O item não foi adicionado ao carrinho.");
        } catch (ItemNaoEncontradoException e) { 
            System.out.println("\n[OPÇÃO INVÁLIDA] " + e.getMessage());
            System.out.println("Operação cancelada. Voltando ao menu de escolhas...");
        }
    }


    private void adicionarBebida(Pedido pedido) {
        List<java.util.function.Supplier<com.projetocafeteria.model.bebida.builders.BebidaBuilder>> disponiveis = cardapio.getBebidasDisponiveis();

        if (disponiveis.isEmpty()) {
            System.out.println("Nenhuma bebida disponível no cardápio.");
            return;
        }

        System.out.println("Escolha a bebida:");
        for (int i = 0; i < disponiveis.size(); i++) {
            com.projetocafeteria.model.bebida.builders.BebidaBuilder builderInicial = disponiveis.get(i).get();
            Bebida itemBase = builderInicial.construir();
            System.out.printf("["+ (i+1) +"] "+ itemBase.exibirDescricao() +" - R$ %.2f%n", itemBase.getValor());
        }

        int indice = lerOpcaoNumerica(disponiveis.size());
    
        if (indice < 1 || indice > disponiveis.size()) {
            throw new ItemNaoEncontradoException("Erro Crítico: O índice " + indice + " não corresponde a nenhuma bebida mapeada no cardápio.");
        }

        BebidaBuilder builderEscolhido = disponiveis.get(indice - 1).get();

        try {
            Bebida bebida = builderEscolhido.interagirComUsuario(sc).construir();
            
            int quantidade = lerQuantidade();
            
            pedido.getCarrinho().adicionarBebida(bebida, quantidade);
            System.out.println("Adicionado: " + bebida.exibirDescricao() + " x" + quantidade);
            
        } catch (QuantidadeInvalidaException e) {
            System.out.println("\n[ERRO DE NEGÓCIO] " + e.getMessage());
            System.out.println("Operação cancelada. O item não foi adicionado ao carrinho.");
        } catch (ItemNaoEncontradoException e) { 
            System.out.println("\n[OPÇÃO INVÁLIDA] " + e.getMessage());
            System.out.println("Operação cancelada. Voltando ao menu de escolhas...");
        }
    }

    private int lerOpcaoNumerica(int max) {
        while (true) {
            try {
                System.out.print("Opção: ");
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= 1 && valor <= max) {
                    return valor;
                }
                System.out.println("Opção inválida!");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private int lerQuantidade() throws QuantidadeInvalidaException {
        try {
            System.out.print("Quantidade: ");
            int valor = Integer.parseInt(sc.nextLine().trim());
            
            if (valor <= 0) {
                throw new QuantidadeInvalidaException("A quantidade solicitada (" + valor + ") é inválida. Deve ser maior que zero.");
            }
            return valor;
        } catch (NumberFormatException e) {
            throw new QuantidadeInvalidaException("O valor digitado para a quantidade não é um número válido.");
        }
    }

    private void escolherMetodoPagamento(Pedido pedido) {
        List<MetodoPagamento> opcoes = List.of(
                new Dinheiro(), new Pix(), new CartaoCredito(), new CartaoDebito()
        );

        System.out.println("\nTotal do seu pedido: R$ " + String.format("%.2f", pedido.calcularTotal()));
        
        System.out.println(" ATENÇÃO: Após o pagamento, o pedido não poderá mais ser cancelado!");
        
        System.out.println("\nEscolha o método de pagamento:");
        for (int i = 0; i < opcoes.size(); i++) {
            System.out.printf("[%d] %s%n", (i + 1), opcoes.get(i).getDescricao());
        }
        System.out.printf("[%d] Desistir e Cancelar Pedido%n", (opcoes.size() + 1));

        int escolha = lerOpcaoNumerica(opcoes.size() + 1);

        if (escolha == opcoes.size() + 1) {
            pedido.cancelarPedido();
        } else {
            pedido.definirMetodoPagamento(opcoes.get(escolha - 1));
        }
    }

    public void mostrarCardapioInformativo() {
        GeradorCardapioInformativo gerador = new GeradorCardapioInformativo(this.cardapio);
        VisualizadorCardapio.exibir(gerador);
    }
}

