package com.projetocafeteria;

import java.util.Scanner;

import com.projetocafeteria.service.PedidoService;
import com.projetocafeteria.view.MenuCliente;
import com.projetocafeteria.view.MenuFuncionario;
import com.projetocafeteria.view.PainelPedidos;

public class Main{

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            PainelPedidos painelPedidos = new PainelPedidos();
            PedidoService pedidoService = new PedidoService(sc);

            MenuCliente menuCliente = new MenuCliente(sc);
            MenuFuncionario menuFuncionario = new MenuFuncionario(sc);
        
            boolean continuar = true;
            while(continuar){
                int escolha = selecionarOpcao(sc);
                continuar = processarEscolha(escolha, painelPedidos, pedidoService, menuCliente, menuFuncionario);
            }
            System.out.println("Encerrando o sistema. Até logo!");
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
                System.out.println("Opção inválida! Digite 0, 1, 2 ou 3.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nVisualizar painel de pedidos [1]");
        System.out.println("Acessar como:");
        System.out.println("[2] Cliente");
        System.out.println("[3] Funcionário");
        System.out.println("[0] Sair");
    }

    private static boolean processarEscolha(int escolha, PainelPedidos painelPedidos, PedidoService pedidoService, MenuCliente menuCliente, MenuFuncionario menuFuncionario) {
        switch (escolha) {
            case 0 -> { return false; }
            case 1 -> painelPedidos.exibir();
            case 2 -> menuCliente.run(pedidoService, painelPedidos);
            case 3 -> menuFuncionario.run(painelPedidos);
            default -> { System.out.println("Opção Inválida!");
            }
        }
        return true;
    }
}