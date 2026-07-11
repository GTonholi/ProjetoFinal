package com.projetocafeteria.view;

import java.util.Scanner;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.service.PedidoService;

/**
 * View responsible for the customer-facing interaction flow.
 * <p>
 * Presents a menu loop allowing the customer to view the informative menu,
 * place a new order, or view the order panel, until the customer chooses
 * to return to the application's main screen.
 * <p>
 * The menu loop is resilient to invalid selections: an
 * {@link ItemNaoEncontradoException} thrown while processing an option is
 * caught and reported without exiting the customer session.
 */
public class MenuCliente {
    private final Scanner sc;

    public MenuCliente(Scanner sc){
        this.sc = sc;
    }
    
    /**
     * Runs the customer interaction flow, repeatedly presenting the client
     * menu until the customer chooses to return to the main screen.
     *
     * @param pedidoService the service responsible for order creation and
     *                      displaying the informative menu
     * @param painelPedidos the shared order panel, used to display registered orders
     */
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

    /**
     * Prompts the customer to select an option from the client menu and
     * validates the input until a valid numeric option is provided.
     *
     * @param sc the shared {@link Scanner} used to read user input
     * @return the validated menu option selected by the customer
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
                System.out.println("Opção inválida! Digite um número entre 0 e 3.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    /**
     * Prints the client menu options to the console.
     */
    private static void exibirMenu() {
        System.out.println("\n        BEM-VINDO À CAFETERIA         \n");
        System.out.println("Em que podemos ajudar?\n");
        System.out.println("[1] Ver Cardápio");
        System.out.println("[2] Realizar pedido");
        System.out.println("[3] Ver Painel de Pedidos");
        System.out.println("[0] Voltar");
    }

    /**
     * Executes the action corresponding to the selected client menu
     * option.
     *
     * @param escolha       the validated menu option selected by the customer
     * @param pedidoService the service responsible for order creation and
     *                      displaying the informative menu
     * @param painelPedidos the shared order panel, used to display registered orders
     * @return {@code true} if the client menu loop should continue running,
     *         {@code false} if the customer chose to return to the main screen
     * @throws ItemNaoEncontradoException if the provided option does not
     *                                    match any valid menu case
     */
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
