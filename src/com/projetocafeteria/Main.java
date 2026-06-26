package com.projetocafeteria;

import com.projetocafeteria.service.PedidoService;
import com.projetocafeteria.view.MenuCliente;
import com.projetocafeteria.view.MenuFuncionario;
import com.projetocafeteria.view.PainelPedidos;
import java.util.Scanner;

public class Main{

    public static void main(String[] args) {
        PainelPedidos painelPedidos = new PainelPedidos();
        MenuCliente menuCliente = new MenuCliente();
        MenuFuncionario menuFuncionario = new MenuFuncionario();
        
        try (Scanner sc = new Scanner(System.in)) {
            int escolha = selecionarOpcao(sc);
            processarEscolha(escolha, painelPedidos, menuCliente, menuFuncionario);
        }
    }

    private static int selecionarOpcao(Scanner sc) {
        exibirMenu();
        
        while (true) {
            try {
                System.out.print("Opção: ");
                int opcao = Integer.parseInt(sc.nextLine().trim());
                if (opcao == 1 || opcao == 2 || opcao == 3) {
                    return opcao;
                }
                System.out.println("Opção inválida! Digite 1, 2 ou 3.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("Visualizar painel de pedidos [1]");
        System.out.println("Acessar como:");
        System.out.println("[2] Cliente");
        System.out.println("[3] Funcionário");
    }

    private static void processarEscolha(int escolha, PainelPedidos painelPedidos, MenuCliente menuCliente, MenuFuncionario menuFuncionario) {
        PedidoService pedidoService = new PedidoService();
        switch (escolha) {
            case 1 -> painelPedidos.exibir();
            case 2 -> menuCliente.run(pedidoService, painelPedidos);
            case 3 -> menuFuncionario.run();
            default -> { System.out.println("Opção Inválida!");
            }
        }
    }
}