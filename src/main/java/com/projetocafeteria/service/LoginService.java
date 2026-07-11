package com.projetocafeteria.service;

import java.util.Scanner;

import com.projetocafeteria.model.Funcionario;
import com.projetocafeteria.repository.FuncionarioRepository;

/**
 * Service responsible for authenticating employees and tracking the
 * current login session state.
 * <p>
 * Delegates credential lookup to {@link FuncionarioRepository}, keeping
 * this class focused solely on the login business rules.
 * <p>
 * Only minimal, non-sensitive employee data (id and name) is retained
 * after a successful login.
 */
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

    /**
     * Checks whether an employee is currently authenticated.
     *
     * @return {@code true} if an employee is logged in, {@code false} otherwise
     */
    public boolean isUsuarioLogado() {
        return this.logado;
    }

     /**
     * Logs out the currently authenticated employee, clearing the session
     * state.
     */
    public void deslogar(){
        this.logado = false;
        this.idFuncionarioLogado = 0;
        this.nomeFuncionarioLogado = null;
    }

    /**
     * Returns the id of the currently logged-in employee.
     *
     * @return the logged-in employee's id
     */
    public int getIdFuncionarioLogado() {
        return idFuncionarioLogado;
    }

    /**
     * Returns the name of the currently logged-in employee.
     *
     * @return the logged-in employee's name, or {@code null} if no employee is logged in
     */
    public String getNomeFuncionarioLogado() {
        return nomeFuncionarioLogado;
    }

    /**
     * Prompts for login credentials and attempts to authenticate the
     * employee, allowing up to three attempts.
     * <p>
     * On successful authentication, the session is marked as logged in
     * and the employee's id and name are retained for later use. If all
     * attempts are exhausted without a match, the method returns without
     * authenticating (the caller is expected to check
     * {@link #isUsuarioLogado()} afterward).
     */
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
