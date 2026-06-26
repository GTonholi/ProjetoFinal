package com.projetocafeteria.view;

import com.projetocafeteria.service.PedidoService;
import java.util.Scanner;

public class MenuCliente {
    
    public void run(PedidoService pedidoService, PainelPedidos painelPedidos){
        Scanner sc = new Scanner(System.in);
        int escolha = selecionarOpcao(sc);

        processarEscolha(escolha, painelPedidos);
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
        System.out.println("Bem vindo! No que podemos ajudar?");
        System.out.println("[1] Realizar pedido");
        System.out.println("[2] Ver Painel de Pedidos");
    }

    private static void processarEscolha(int escolha, PainelPedidos painelPedidos) {
        PedidoService pedidoService = new PedidoService();
        switch (escolha) {
            case 1 -> pedidoService.realizarPedido();
            case 2 -> painelPedidos.exibir();
            default -> { System.out.println("Opção Inválida!");
            }
        }
    }
}
