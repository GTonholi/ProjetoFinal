package com.projetocafeteria.service;

import com.projetocafeteria.model.Cardapio;
import com.projetocafeteria.model.Cliente;
import com.projetocafeteria.model.Pedido;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Leite;
import com.projetocafeteria.model.comida.Cobertura;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.pagamento.CartaoCredito;
import com.projetocafeteria.model.pagamento.CartaoDebito;
import com.projetocafeteria.model.pagamento.Dinheiro;
import com.projetocafeteria.model.pagamento.MetodoPagamento;
import com.projetocafeteria.model.pagamento.Pix;
import com.projetocafeteria.view.PainelPedidos;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

public class PedidoService {
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
            System.out.print("Opção: ");

            String opcao = sc.nextLine().trim();
            switch (opcao) {
                case "1" -> adicionarComida(pedido);
                case "2" -> adicionarBebida(pedido);
                case "3" -> continuar = false;
                default -> System.out.println("Opção inválida!");
            }
        }

        escolherMetodoPagamento(pedido);
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
        List<Supplier<Comida>> disponiveis = cardapio.getComidasDisponiveis();

        if (disponiveis.isEmpty()) {
            System.out.println("Nenhuma comida disponível no cardápio.");
            return;
        }

        System.out.println("Escolha a comida:");
        for (int i = 0; i < disponiveis.size(); i++) {
            Comida item = disponiveis.get(i).get();
            System.out.printf("["+ (i+1) +"] "+ item.exibirDescricao() +" - R$ %.2f%n", item.getValor());
        }

        int indice = lerOpcaoNumerica(disponiveis.size());
        Comida comida = disponiveis.get(indice - 1).get();

        System.out.print("Adicionar cobertura? (s/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("s")) {
            comida = new Cobertura(comida);
        }

        int quantidade = lerQuantidade();
        pedido.getCarrinho().AdicionarComida(comida, quantidade);
        System.out.println("Adicionado: " + comida.exibirDescricao() + " x" + quantidade);
    }

    private void adicionarBebida(Pedido pedido) {
        List<Supplier<Bebida>> disponiveis = cardapio.getBebidasDisponiveis();

        if (disponiveis.isEmpty()) {
            System.out.println("Nenhuma bebida disponível no cardápio.");
            return;
        }

        System.out.println("Escolha a bebida:");
        for (int i = 0; i < disponiveis.size(); i++) {
            Bebida item = disponiveis.get(i).get();
            System.out.printf("["+ (i+1) +"] "+ item.exibirDescricao() +" - R$ %.2f%n", item.getValor());
        }

        int indice = lerOpcaoNumerica(disponiveis.size());
        Bebida bebida = disponiveis.get(indice - 1).get();

        System.out.print("Adicionar leite? (s/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("s")) {
            bebida = new Leite(bebida);
        }

        int quantidade = lerQuantidade();
        pedido.getCarrinho().AdicionarBebida(bebida, quantidade);
        System.out.println("Adicionado: " + bebida.exibirDescricao() + " x" + quantidade);
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

    private int lerQuantidade() {
        while (true) {
            try {
                System.out.print("Quantidade: ");
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor > 0) {
                    return valor;
                }
                System.out.println("A quantidade deve ser maior que zero.");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private void escolherMetodoPagamento(Pedido pedido) {
        List<MetodoPagamento> opcoes = List.of(
                new Dinheiro(), new Pix(), new CartaoCredito(), new CartaoDebito()
        );

        System.out.println("Escolha o método de pagamento:");
        for (int i = 0; i < opcoes.size(); i++) {
            System.out.printf("["+ (i+1) +"] %s%n", opcoes.get(i).getDescricao());
        }

        int escolha = lerOpcaoNumerica(opcoes.size());
        pedido.definirMetodoPagamento(opcoes.get(escolha - 1));
    }

}
