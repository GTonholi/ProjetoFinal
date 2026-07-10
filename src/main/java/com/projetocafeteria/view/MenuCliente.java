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
                
                processarEscolha(escolha, pedidoService, painelPedidos);
                
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
                if (opcao == 1 || opcao == 2) {
                    return opcao;
                }
                System.out.println("Opção inválida! Digite 1 ou 2.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\n        BEM-VINDO À CAFETERIA         \n");
        System.out.println("Em que podemos ajudar?\n");
        System.out.println("[1] Realizar pedido");
        System.out.println("[2] Ver Painel de Pedidos");
    }

    private static void processarEscolha(int escolha, PedidoService pedidoService, PainelPedidos painelPedidos) {
        switch (escolha) {
            case 1 -> pedidoService.realizarPedido(painelPedidos);
            case 2 -> painelPedidos.exibir();
            default -> throw new ItemNaoEncontradoException("A opção de menu " + escolha + " não é reconhecida pelo sistema.");
        }
    }
}
