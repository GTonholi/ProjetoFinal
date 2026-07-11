package com.projetocafeteria;

import java.util.Scanner;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.service.PedidoService;
import com.projetocafeteria.view.MenuCliente;
import com.projetocafeteria.view.MenuFuncionario;
import com.projetocafeteria.view.PainelPedidos;

/**
 * Entry point of the cafeteria management system.
 * <p>
 * This class is responsible for bootstrapping the application's core
 * dependencies (shared {@link Scanner}, {@link PainelPedidos},
 * {@link PedidoService}, and the client/employee menu views) and running
 * the main application loop, which repeatedly presents the global menu
 * until the user chooses to exit.
 * <p>
 * The main loop is resilient to invalid menu selections: an
 * {@link ItemNaoEncontradoException} thrown during option processing is
 * caught and reported to the user without terminating the program.
 *
 */
public class Main{

    /**
     * Application entry point.
     */
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            PainelPedidos painelPedidos = new PainelPedidos();
            PedidoService pedidoService = new PedidoService(sc);

            MenuCliente menuCliente = new MenuCliente(sc);
            MenuFuncionario menuFuncionario = new MenuFuncionario(sc);
        
            boolean continuar = true;
            while(continuar){
                try { 
                    int escolha = selecionarOpcao(sc);
                    continuar = processarEscolha(escolha, painelPedidos, pedidoService, menuCliente, menuFuncionario);
                } catch (ItemNaoEncontradoException e) {
                    System.out.println("\n[AVISO DO SISTEMA] " + e.getMessage());
                    System.out.println("Retornando ao menu principal da cafeteria...");
                }
            }
            System.out.println("\nEncerrando o sistema. Até logo!");
        }
    }

    /**
     * Prompts the user to select an option from the main menu and validates
     * the input until a valid numeric option (0 to 3) is provided.
     * <p>
     * Non-numeric input is rejected with a warning message, and the prompt
     * is repeated until valid input is entered.
     *
     * @param sc the shared {@link Scanner} used to read user input
     * @return the validated menu option selected by the user (0, 1, 2, or 3)
     */
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

    /**
     * Prints the main menu options to the console.
     */
    private static void exibirMenu() {
        System.out.println("\n\n[1] Visualizar painel de pedidos ");
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
            default -> throw new ItemNaoEncontradoException("A opção global " + escolha + " não é válida.");
        }
        return true;
    }
}