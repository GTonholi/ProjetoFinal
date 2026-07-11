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

/**
 * The primary View layer class responsible for interacting with the Customer.
 * <p>
 * This class handles all input and output (I/O) related to the customer's 
 * order creation flow. It prompts the customer for choices, displays the 
 * available menu options (fetching data via the service), and reads user input 
 * using a {@link Scanner}. 
 * By extracting I/O operations from the service layer, it strictly adheres 
 * to the Single Responsibility Principle (SRP) and ensures the core domain 
 * remains independent of the presentation mechanism.
 */
public class MenuCliente {
    private final Scanner sc;

    /**
     * Constructs a new MenuCliente, using the shared scanner for user input.
     * 
     * @param sc the shared {@link Scanner} instance used for reading console input
     */
    public MenuCliente(Scanner sc){
        this.sc = sc;
    }
    
    /**
     * Starts the main interaction loop for the customer.
     * <p>
     * Displays a greeting and allows the customer to view the informative menu, 
     * start a new order, view the active orders panel, or return to the main menu.
     * 
     * @param pedidoService the service handling order business logic
     * @param painelPedidos the view component for displaying active orders
     * @param pedidoRepository the data layer for accessing active orders
     */
    public void run(PedidoService pedidoService, PainelPedidos painelPedidos, IPedidoRepository pedidoRepository) {
        boolean rodando = true;
        
        while (rodando) {
            try {
                limparTela();
                int escolha = selecionarOpcaoGlob(sc);
                
                switch (escolha) {
                    case 1 -> {
                        limparTela();
                        pedidoService.mostrarCardapioInformativo();
                        pausar();
                    }
                    case 2 -> {
                        limparTela();
                        realizarPedido(pedidoService);
                        pausar();
                    }
                    case 3 -> {
                        limparTela();
                        painelPedidos.exibir(pedidoRepository.listarPedidosEmAndamento());
                        pausarVoltar();
                    }
                    case 0 -> {
                        System.out.println("\nvoltando a tela inicial...\n");
                        rodando = false;
                    }
                    default -> throw new ItemNaoEncontradoException("Opção inválida: " + escolha);
                }
                
            } catch (ItemNaoEncontradoException e) {
                System.out.println("\n[ERRO DE NAVEGAÇÃO] " + e.getMessage());
                pausar();
            }
        }
    }

    /**
     * Prompts the customer to select a global action from the main customer menu.
     * 
     * @param sc the shared {@link Scanner}
     * @return the selected numeric option (0 to 3)
     */
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

    /**
     * Initiates the full workflow for creating a new order.
     * <p>
     * Collects the customer's name and repeatedly allows them to add food 
     * or drink items until they choose to checkout or cancel the order.
     * 
     * @param pedidoService the service orchestrating order construction
     */
    private void realizarPedido(PedidoService pedidoService) {
        System.out.print("Digite seu nome: ");
        String nome = sc.nextLine().trim();
        Pedido pedido = pedidoService.iniciarPedido(nome);

        boolean continuar = true;
        while (continuar) {
            limparTela();
            System.out.println("\n--- Realizando Pedido de " + nome + " ---");
            System.out.println("[1] Adicionar comida");
            System.out.println("[2] Adicionar bebida");
            System.out.println("[3] Finalizar pedido");
            System.out.println("[4] Cancelar Pedido");
            System.out.print("Opção: ");

            String opcao = sc.nextLine().trim();
            try {
                switch (opcao) {
                    case "1" -> {
                        limparTela();
                        adicionarComida(pedido, pedidoService);
                        pausar();
                    }
                    case "2" -> {
                        limparTela();
                        adicionarBebida(pedido, pedidoService);
                        pausar();
                    }
                    case "3" -> {
                        if (pedido.getCarrinho().estaVazio()) {
                            System.out.println("Seu carrinho está vazio! Adicione itens antes de pagar.");
                            pausar();
                        } else {
                            continuar = false;
                        }
                    }
                    case "4" -> {
                        pedido.cancelarPedido();
                        return;
                    }
                    default -> {
                        System.out.println("Opção inválida! Escolha de 1 a 4.");
                        pausar();
                    }
                }
            } catch (RegraNegocioException | ItemNaoEncontradoException e) {
                System.out.println("\n[ERRO] " + e.getMessage());
                pausar();
            }
        }
        
        limparTela();
        escolherMetodoPagamento(pedido, pedidoService);
    }

    /**
     * Guides the customer through selecting a food item, picking available 
     * sub-options and add-ons, and defining the quantity.
     * 
     * @param pedido the current active order
     * @param pedidoService the service handling order logic
     * @throws RegraNegocioException if business validations fail (e.g., negative quantity)
     */
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

    /**
     * Guides the customer through selecting a drink item, picking available 
     * sub-options and add-ons, and defining the quantity.
     * 
     * @param pedido the current active order
     * @param pedidoService the service handling order logic
     * @throws RegraNegocioException if business validations fail (e.g., negative quantity)
     */
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

    /**
     * Prompts the user to select a sub-option (e.g., flavor or size) if the item supports it.
     * 
     * @param info the commercial information regarding the item
     * @return the string name of the selected sub-option, or {@code null} if none exist
     */
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

    /**
     * Prompts the user to select an add-on (e.g., milk, sugar) if the item supports it.
     * 
     * @param info the commercial information regarding the item
     * @return the string name of the selected add-on, or {@code null} if the user declines
     */
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

    /**
     * Prompts the customer to select a payment method from the available strategies.
     * Applies the chosen payment method and signals the service to complete the checkout.
     * 
     * @param pedido the order being finalized
     * @param pedidoService the service that will process the payment logic
     */
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

    /**
     * Utility method to safely read a 1-based numeric option from the user.
     * 
     * @param max the maximum allowed choice inclusive
     * @return a valid integer between 1 and {@code max}
     */
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

    /**
     * Utility method to safely read a 0-based numeric option from the user.
     * 
     * @param max the maximum allowed choice inclusive
     * @return a valid integer between 0 and {@code max}
     */
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

    /**
     * Utility method to safely read the quantity of an item from the user.
     * 
     * @return the entered quantity integer
     */
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

    private void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    private void pausar() {
        System.out.println("\nPressione ENTER para continuar...");
        sc.nextLine();
    }
    
    private void pausarVoltar() {
        System.out.println("\nPressione ENTER para voltar");
        sc.nextLine();
    }
}

