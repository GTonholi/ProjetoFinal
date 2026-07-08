package com.projetocafeteria.service;

import com.projetocafeteria.model.Funcionario;
import com.projetocafeteria.repository.FuncionarioRepository;
import java.util.Scanner;

public class LoginService {
    private boolean logado;
    private int idFuncionarioLogado;
    private String nomeFuncionarioLogado;
    private final FuncionarioRepository funcionarioRepository;

    public LoginService() {
        this.funcionarioRepository = new FuncionarioRepository();
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
        Scanner sc = new Scanner(System.in);
        int tentativas = 3;

        while (tentativas > 0 && !logado) {
            System.out.print("Login: ");
            String login = sc.nextLine().trim();
            System.out.print("Senha: ");
            String senha = sc.nextLine().trim();

            Funcionario encontrado = funcionarioRepository.buscarPorLoginESenha(login, senha);

            if (encontrado != null) {
                this.idFuncionarioLogado = encontrado.getId();
                this.nomeFuncionarioLogado = encontrado.getNome();
                this.logado = true;
                System.out.println("Login realizado com sucesso!");
            } else {
                tentativas--;
                System.out.println("Login ou senha inválidos. Tentativas restantes: " + tentativas);
            }
        }
    }
}
