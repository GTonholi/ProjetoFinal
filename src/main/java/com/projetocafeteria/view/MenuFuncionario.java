package com.projetocafeteria.view;

import java.util.List;
import java.util.Scanner;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.Pedido;
import com.projetocafeteria.service.LoginService;

public class MenuFuncionario {
    private final LoginService loginService;
    private final Scanner sc;

    public MenuFuncionario(Scanner sc){
        this.sc = sc;
        this.loginService = new LoginService(this.sc);
    }

    public void run(PainelPedidos painelPedidos){
        if(!loginService.isUsuarioLogado()){
            loginService.logarFuncionario();
        }

        if (!loginService.isUsuarioLogado()) {
            System.out.println("Não foi possível autenticar. Encerrando.");
            return;
        }

        boolean logado = true;
        while (logado && loginService.isUsuarioLogado()) {
            try {
                int escolha = selecionarOpcao();
                
                if (escolha == 2) {
                    loginService.deslogar();
                    logado = false;
                    System.out.println("\nLogout efetuado com sucesso.");
                } else {
                    processarEscolha(escolha, painelPedidos);
                }
                
            } catch (ItemNaoEncontradoException e) {
                System.out.println("\n[ERRO DE SISTEMA] " + e.getMessage());
                System.out.println("Retornando ao menu do funcionário...");
            }
        }
    }
    
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

    private void exibirMenu() {
        String nome = loginService.getNomeFuncionarioLogado();
        System.out.println("\n-----------------------------------------------");
        System.out.println("Bem vindo, " + nome + ". No que podemos ajudar?");
        System.out.println("[1] Atualizar estado do Pedido");
        System.out.println("[2] Deslogar do sistema");
    }

    private void processarEscolha(int escolha, PainelPedidos painelPedidos) {
        switch (escolha) {
            case 1 -> atualizarEstadoPedido(painelPedidos);
            default -> throw new ItemNaoEncontradoException("A opção de menu " + escolha + " não existe.");
        }
    }

    private void atualizarEstadoPedido(PainelPedidos painelPedidos) {
        List<Pedido> emAndamento = painelPedidos.listarPedidosEmAndamento();

        if(emAndamento.isEmpty()){
            System.out.println("Nenhum pedido em andamento no momento.");
            return;
        }

        System.out.println("=== Pedidos em andamento ===");
        for(Pedido pedido : emAndamento) {
            System.out.printf("\nPedido #"+ pedido.getId() +" - Cliente: "+ pedido.getNomeCliente() +" - Estado atual: "+ pedido.consultarPreparo());
        }

        System.out.print("\n\nDigite o id do pedido que deseja atualizar: ");
        int id = lerId();

        Pedido pedido = painelPedidos.buscarPorId(id);

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

    private int lerId() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }
}

