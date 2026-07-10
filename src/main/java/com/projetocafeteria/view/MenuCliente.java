package com.projetocafeteria.view;

import java.util.Scanner;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.service.PedidoService;

public class MenuCliente {
    private final Scanner sc;

    public MenuCliente(Scanner sc){
        this.sc = sc;
    }
    
    public void run(PedidoService pedidoService, PainelPedidos painelPedidos) {
        boolean rodando = true;
        
        while (rodando) {
            try {
                int escolha = selecionarOpcao(sc);
                
                rodando = processarEscolha(escolha, pedidoService, painelPedidos);
                
            } catch (ItemNaoEncontradoException e) {
                System.out.println("\n[ERRO DE NAVEGAÇÃO] " + e.getMessage());
            }
        }
    }
    private static int selecionarOpcao(Scanner sc) {
        exibirMenu();
        
        while (true) {
            try {
                System.out.print("Opção: ");
                int opcao = Integer.parseInt(sc.nextLine().trim());
                if (opcao >= 0 && opcao <= 3) {
                    return opcao;
                }
                System.out.println("Opção inválida! Digite um número entre 0 e 3.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n        BEM-VINDO À CAFETERIA         \n");
        System.out.println("Em que podemos ajudar?\n");
        System.out.println("[1] Ver Cardápio");
        System.out.println("[2] Realizar pedido");
        System.out.println("[3] Ver Painel de Pedidos");
        System.out.println("[0] Voltar");
    }

    private static boolean processarEscolha(int escolha, PedidoService pedidoService, PainelPedidos painelPedidos) {
        switch (escolha) {
            case 1 -> pedidoService.mostrarCardapioInformativo();
            case 2 -> pedidoService.realizarPedido(painelPedidos);
            case 3 -> painelPedidos.exibir();
            case 0 -> {System.out.println("\nvoltando a tela inicial...\n"); return false;}
            default -> throw new ItemNaoEncontradoException("Opção inválida: " + escolha);
        }

        return true;
    }
}
