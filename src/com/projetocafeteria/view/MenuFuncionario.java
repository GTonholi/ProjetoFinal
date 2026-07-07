package com.projetocafeteria.view;

import com.projetocafeteria.service.LoginService;
import java.util.Scanner;

public class MenuFuncionario {
    private final LoginService loginService = new LoginService();

    public void run(){
        if(!loginService.isUsuarioLogado()){
            loginService.logarFuncionario();
        }

        if (!loginService.isUsuarioLogado()) {
            System.out.println("Não foi possível autenticar. Encerrando.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        int escolha = selecionarOpcao(sc);

        processarEscolha(escolha);
    }
    
    private int selecionarOpcao(Scanner sc) {
        exibirMenu();
        
        while (true) {
            try {
                System.out.print("Opção: ");
                int opcao = Integer.parseInt(sc.nextLine().trim());
                if (opcao == 1) {
                    return opcao;
                }
                System.out.println("Opção inválida! Digite 1");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número.");
            }
        }
    }

    private void exibirMenu() {
        String nome = loginService.getNomeFuncionarioLogado();
        System.out.println("Bem vindo, " + nome + ". No que podemos ajudar?");
        System.out.println("[1] Atualizar estado do Pedido");
    }

    private void processarEscolha(int escolha) {
        switch (escolha) {
            default -> { System.out.println("Opção Inválida!");
            }
        }
    }
}

