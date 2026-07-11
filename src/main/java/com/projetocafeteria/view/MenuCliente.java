package com.projetocafeteria.view;

import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.exception.RegraNegocioException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.Pedido;
import com.projetocafeteria.model.bebida.builders.BebidaBuilder;
import com.projetocafeteria.model.comida.builders.ComidaBuilder;
import com.projetocafeteria.model.pagamento.*;
import com.projetocafeteria.service.PedidoService;
import com.projetocafeteria.repository.IPedidoRepository;

public class MenuCliente {
    private final Scanner sc;

    public MenuCliente(Scanner sc){
        this.sc = sc;
    }
    
    public void run(PedidoService pedidoService, PainelPedidos painelPedidos, IPedidoRepository pedidoRepository) {
        boolean rodando = true;
        
        while (rodando) {
            try {
                int escolha = selecionarOpcaoGlob(sc);
                
                switch (escolha) {
                    case 1 -> pedidoService.mostrarCardapioInformativo();
                    case 2 -> realizarPedido(pedidoService);
                    case 3 -> painelPedidos.exibir(pedidoRepository.listarPedidosEmAndamento());
                    case 0 -> {
                        System.out.println("\nvoltando a tela inicial...\n");
                        rodando = false;
                    }
                    default -> throw new ItemNaoEncontradoException("Opção inválida: " + escolha);
                }
                
            } catch (ItemNaoEncontradoException e) {
                System.out.println("\n[ERRO DE NAVEGAÇÃO] " + e.getMessage());
            }
        }
    }

    private int selecionarOpcaoGlob(Scanner sc) {
        System.out.println("\n        BEM-VINDO À CAFETERIA         \n");
        System.out.println("Em que podemos ajudar?\n");
        System.out.println("[1] Ver Cardápio");
        System.out.println("[2] Realizar pedido");
        System.out.println("[3] Ver Painel de Pedidos");
        System.out.println("[0] Voltar");
        
        while (true) {
            try {
                System.out.print("Opção: ");
                int opcao = Integer.parseInt(sc.nextLine().trim());
                if (opcao >= 0 && opcao <= 3) return opcao;
                System.out.println("Opção inválida! Digite um número entre 0 e 3.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    private void realizarPedido(PedidoService pedidoService) {
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine().trim();
        Pedido pedido = pedidoService.iniciarPedido(nome);

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
                    case "1" -> adicionarComida(pedido, pedidoService);
                    case "2" -> adicionarBebida(pedido, pedidoService);
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
            } catch (RegraNegocioException | ItemNaoEncontradoException e) {
                System.out.println("\n[ERRO] " + e.getMessage());
            }
        }

        escolherMetodoPagamento(pedido, pedidoService);
    }

    private void adicionarComida(Pedido pedido, PedidoService pedidoService) throws RegraNegocioException {
        List<Supplier<ComidaBuilder>> disponiveis = pedidoService.getCardapio().getComidasDisponiveis();
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhuma comida disponível.");
            return;
        }

        System.out.println("Escolha a comida:");
        for (int i = 0; i < disponiveis.size(); i++) {
            ItemCardapioInfo info = disponiveis.get(i).get().obterInformacaoComercial();
            System.out.printf("[%d] %s - R$ %.2f%n", (i + 1), info.getNome(), info.getPrecoBase());
        }

        int indice = lerOpcaoNumerica(disponiveis.size()) - 1;
        ItemCardapioInfo info = disponiveis.get(indice).get().obterInformacaoComercial();

        String subopcao = escolherSubopcao(info);
        String adicional = escolherAdicional(info);
        int quantidade = lerQuantidade();

        pedidoService.adicionarComida(pedido, indice, subopcao, adicional, quantidade);
        System.out.println("Item adicionado ao carrinho!");
    }

    private void adicionarBebida(Pedido pedido, PedidoService pedidoService) throws RegraNegocioException {
        List<Supplier<BebidaBuilder>> disponiveis = pedidoService.getCardapio().getBebidasDisponiveis();
        if (disponiveis.isEmpty()) {
            System.out.println("Nenhuma bebida disponível.");
            return;
        }

        System.out.println("Escolha a bebida:");
        for (int i = 0; i < disponiveis.size(); i++) {
            ItemCardapioInfo info = disponiveis.get(i).get().obterInformacaoComercial();
            System.out.printf("[%d] %s - R$ %.2f%n", (i + 1), info.getNome(), info.getPrecoBase());
        }

        int indice = lerOpcaoNumerica(disponiveis.size()) - 1;
        ItemCardapioInfo info = disponiveis.get(indice).get().obterInformacaoComercial();

        String subopcao = escolherSubopcao(info);
        String adicional = escolherAdicional(info);
        int quantidade = lerQuantidade();

        pedidoService.adicionarBebida(pedido, indice, subopcao, adicional, quantidade);
        System.out.println("Item adicionado ao carrinho!");
    }

    private String escolherSubopcao(ItemCardapioInfo info) {
        List<String> subopcoes = info.getSubopcoes();
        if (subopcoes.isEmpty()) return null;

        System.out.println("Escolha a opção/sabor:");
        for (int i = 0; i < subopcoes.size(); i++) {
            System.out.printf("[%d] %s%n", (i + 1), subopcoes.get(i));
        }
        int escolha = lerOpcaoNumerica(subopcoes.size());
        return subopcoes.get(escolha - 1);
    }

    private String escolherAdicional(ItemCardapioInfo info) {
        List<String> adicionais = info.getAdicionais();
        if (adicionais.isEmpty()) return null;

        System.out.println("Deseja algum adicional?");
        System.out.println("[0] Nenhum");
        for (int i = 0; i < adicionais.size(); i++) {
            System.out.printf("[%d] %s%n", (i + 1), adicionais.get(i));
        }
        int escolha = lerOpcaoNumericaZeroBase(adicionais.size());
        if (escolha == 0) return null;
        return adicionais.get(escolha - 1);
    }

    private void escolherMetodoPagamento(Pedido pedido, PedidoService pedidoService) {
        List<MetodoPagamento> opcoes = List.of(
                new Dinheiro(), new Pix(), new CartaoCredito(), new CartaoDebito()
        );

        System.out.printf("\nTotal do pedido: R$ %.2f%n", pedido.calcularTotal());
        System.out.println("Escolha o método de pagamento:");
        for (int i = 0; i < opcoes.size(); i++) {
            System.out.printf("[%d] %s%n", (i + 1), opcoes.get(i).getDescricao());
        }
        System.out.printf("[%d] Desistir e Cancelar%n", (opcoes.size() + 1));

        int escolha = lerOpcaoNumerica(opcoes.size() + 1);
        if (escolha == opcoes.size() + 1) {
            pedido.cancelarPedido();
            System.out.println("Pedido cancelado.");
        } else {
            MetodoPagamento metodo = opcoes.get(escolha - 1);
            boolean pago = pedidoService.finalizarPedido(pedido, metodo);
            if (pago) {
                System.out.printf("Pedido #%d finalizado!%n", pedido.getId());
            } else {
                System.out.println("Pagamento falhou.");
            }
        }
    }

    private int lerOpcaoNumerica(int max) {
        while (true) {
            try {
                System.out.print("Opção: ");
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= 1 && valor <= max) return valor;
                System.out.println("Opção inválida!");
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private int lerOpcaoNumericaZeroBase(int max) {
        while (true) {
            try {
                System.out.print("Opção: ");
                int valor = Integer.parseInt(sc.nextLine().trim());
                if (valor >= 0 && valor <= max) return valor;
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
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }
}
