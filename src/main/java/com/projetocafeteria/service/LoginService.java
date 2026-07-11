package com.projetocafeteria.service;

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

    public LoginService() {
        this.funcionarioRepository = new FuncionarioRepository();
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
    public void deslogar() {
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
     * @return the logged-in employee's name, or {@code null} if no employee is
     *         logged in
     */
    public String getNomeFuncionarioLogado() {
        return nomeFuncionarioLogado;
    }

    /**
     * Attempts to authenticate the employee using the provided credentials.
     * <p>
     * On successful authentication, the session is marked as logged in
     * and the employee's id and name are retained for later use.
     * 
     * @param login the login identifier to authenticate
     * @param senha the password to authenticate
     * @return {@code true} if authentication succeeds, {@code false} otherwise
     */
    public boolean logarFuncionario(String login, String senha) {
        Funcionario encontrado = funcionarioRepository.buscarPorLoginESenha(login, senha);

        if (encontrado != null) {
            this.idFuncionarioLogado = encontrado.getId();
            this.nomeFuncionarioLogado = encontrado.getNome();
            this.logado = true;
            return true;
        }
        return false;
    }
}
