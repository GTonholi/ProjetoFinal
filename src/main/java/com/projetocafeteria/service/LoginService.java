package com.projetocafeteria.service;

import java.util.Scanner;

import com.projetocafeteria.model.Funcionario;
import com.projetocafeteria.repository.FuncionarioRepository;

public class LoginService {
    private boolean logado;
    private int idFuncionarioLogado;
    private String nomeFuncionarioLogado;
    private final FuncionarioRepository funcionarioRepository;
    private final Scanner sc;

    public LoginService(Scanner sc) {
        this.funcionarioRepository = new FuncionarioRepository();
        this.sc = sc;
    }

    public boolean isUsuarioLogado() {
        return this.logado;
    }

    public void deslogar(){
        this.logado = false;
        this.idFuncionarioLogado = 0;
        this.nomeFuncionarioLogado = null;
    }

    public int getIdFuncionarioLogado() {
        return idFuncionarioLogado;
    }

    public String getNomeFuncionarioLogado() {
        return nomeFuncionarioLogado;
    }

    public void logarFuncionario() {
        int tentativas = 3;

        while (tentativas > 0 && !logado) {
            System.out.print("\n\nLogin: ");
            String login = sc.nextLine().trim();
            System.out.print("Senha: ");
            String senha = sc.nextLine().trim();

            Funcionario encontrado = funcionarioRepository.buscarPorLoginESenha(login, senha);

            if (encontrado != null) {
                this.idFuncionarioLogado = encontrado.getId();
                this.nomeFuncionarioLogado = encontrado.getNome();
                this.logado = true;
                System.out.println("\nLogin realizado com sucesso!");
            } else {
                tentativas--;
                System.out.println("\nLogin ou senha inválidos. Tentativas restantes: " + tentativas);
            }
        }
    }
}
