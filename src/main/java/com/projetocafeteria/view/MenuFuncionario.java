package com.projetocafeteria.view;

import java.util.List;
import java.util.Scanner;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.Pedido;
import com.projetocafeteria.service.LoginService;

/**
 * View responsible for the employee-facing interaction flow.
 * <p>
 * Handles employee authentication (delegated to {@link LoginService}) and,
 * once logged in, presents a menu loop allowing the employee to update the
 * preparation status of in-progress orders or log out of the system.
 * <p>
 * The menu loop is resilient to invalid selections and missing orders:
 * an {@link ItemNaoEncontradoException} thrown while processing an option
 * is caught and reported without exiting the employee session.
 */
public class MenuFuncionario {
    private final LoginService loginService;
    private final Scanner sc;

    public MenuFuncionario(Scanner sc) {
        this.sc = sc;
        this.loginService = new LoginService();
    }

    /**
     * Runs the employee interaction flow: authenticates the employee if
     * not already logged in, then repeatedly presents the employee menu
     * until the employee chooses to log out.
     * <p>
     *
     * @param painelPedidos the shared order panel, used to list and update
     *                      in-progress orders
     */
    public void run(PainelPedidos painelPedidos, com.projetocafeteria.repository.IPedidoRepository pedidoRepository) {
        limparTela();
        if (!loginService.isUsuarioLogado()) {
            logarFuncionario();
        }

        if (!loginService.isUsuarioLogado()) {
            System.out.println("Não foi possível autenticar. Encerrando.");
            pausar();
            return;
        }

        boolean logado = true;
        while (logado && loginService.isUsuarioLogado()) {
            try {
                limparTela();
                int escolha = selecionarOpcao();

                if (escolha == 2) {
                    loginService.deslogar();
                    logado = false;
                    System.out.println("\nLogout efetuado com sucesso.");
                    pausar();
                } else {
                    limparTela();
                    processarEscolha(escolha, painelPedidos, pedidoRepository);
                    pausar();
                }

            } catch (ItemNaoEncontradoException e) {
                System.out.println("\n[ERRO DE SISTEMA] " + e.getMessage());
                System.out.println("Retornando ao menu do funcionário...");
                pausar();
            }
        }
    }

    private void logarFuncionario() {
        int tentativas = 3;
        while (tentativas > 0 && !loginService.isUsuarioLogado()) {
            System.out.print("\n\nLogin: ");
            String login = sc.nextLine().trim();
            System.out.print("Senha: ");
            String senha = sc.nextLine().trim();

            if (loginService.logarFuncionario(login, senha)) {
                System.out.println("\nLogin realizado com sucesso!");
            } else {
                tentativas--;
                System.out.println("\nLogin ou senha inválidos. Tentativas restantes: " + tentativas);
            }
        }
    }

    /**
     * Prompts the employee to select an option from the employee menu and
     * validates the input until a valid numeric option is provided.
     *
     * @return the validated menu option selected by the employee
     */
    private int selecionarOpcao() {
        exibirMenu();

        while (true) {
            try {
                System.out.print("Opção: ");
                int opcao = Integer.parseInt(sc.nextLine().trim());
                if (opcao == 1 || opcao == 2) {
                    return opcao;
                }
                System.out.println("Opção inválida! Digite 1 ou 2");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    /**
     * Prints the employee menu options to the console, including a
     * personalized greeting with the logged-in employee's name.
     */
    private void exibirMenu() {
        String nome = loginService.getNomeFuncionarioLogado();
        System.out.println("\n-----------------------------------------------");
        System.out.println("Bem vindo, " + nome + ". No que podemos ajudar?");
        System.out.println("[1] Atualizar estado do Pedido");
        System.out.println("[2] Deslogar do sistema");
    }

    /**
     * Executes the action corresponding to the selected employee menu
     * option.
     *
     * @param escolha       the validated menu option selected by the employee
     * @param painelPedidos the shared order panel, used to update order status
     * @throws ItemNaoEncontradoException if the provided option does not
     *                                    match any valid menu case
     */
    private void processarEscolha(int escolha, PainelPedidos painelPedidos,
            com.projetocafeteria.repository.IPedidoRepository pedidoRepository) {
        switch (escolha) {
            case 1 -> atualizarEstadoPedido(painelPedidos, pedidoRepository);
            default -> throw new ItemNaoEncontradoException("A opção de menu " + escolha + " não existe.");
        }
    }

    /**
     * Guides the employee through updating the preparation status of an
     * in-progress order.
     * <p>
     * Lists all orders currently in progress, prompts for the id of the
     * order to update, and advances its preparation status by one step
     * if it has not yet been delivered.
     *
     * @param painelPedidos    the shared order panel, used to list orders
     * @param pedidoRepository the repository used to locate orders
     * @throws ItemNaoEncontradoException if no order matches the id provided
     *                                    by the employee
     */
    private void atualizarEstadoPedido(PainelPedidos painelPedidos,
            com.projetocafeteria.repository.IPedidoRepository pedidoRepository) {
        List<Pedido> emAndamento = pedidoRepository.listarPedidosEmAndamento();

        if (emAndamento.isEmpty()) {
            System.out.println("Nenhum pedido em andamento no momento.");
            return;
        }

        System.out.println("=== Pedidos em andamento ===");
        painelPedidos.exibir(emAndamento);

        System.out.print("\n\nDigite o id do pedido que deseja atualizar: ");
        int id = lerId();

        Pedido pedido = pedidoRepository.buscarPorId(id);

        if (pedido == null) {
            throw new ItemNaoEncontradoException("O pedido com o ID #" + id + " não foi localizado no painel.");
        }

        if (pedido.pedidoEstaEntregue()) {
            System.out.println("Esse pedido já foi entregue.");
            return;
        }

        pedido.avancarPreparo();
        System.out.println("Pedido #" + pedido.getId() + " atualizado para: " + pedido.consultarPreparo());
    }

    /**
     * Reads an order id from user input, retrying until a valid integer
     * is provided.
     *
     * @return the order id entered by the employee
     */
    private int lerId() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }

    private void limparTela() {
        System.out.print("\033[H\033[2J\033[95m");
        System.out.flush();
    }
    
    private void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }
}
