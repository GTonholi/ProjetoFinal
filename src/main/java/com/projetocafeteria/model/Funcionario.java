package com.projetocafeteria.model;


/**
 * Represents an employee of the cafeteria who can authenticate and
 * access the employee-facing features of the system (e.g. updating order
 * preparation status).
 * <p>
 * Credential validation is encapsulated within this class via
 * {@link #autenticar}, so that consumers such as
 * {@link com.projetocafeteria.repository.FuncionarioRepository} and
 * {@link com.projetocafeteria.service.LoginService} never need to compare
 * login/password values directly.
 * <p>
 * {@code login} and {@code senha} intentionally have no public
 * getters: once authentication succeeds, callers are expected to retain
 * only {@link #getId()} and {@link #getNome()}, never the raw credentials.
 */
public class Funcionario {
    private final int id;
    private final String nome;
    private final String login;
    private final String senha;

    /**
     * Creates a new employee record.
     *
     * @param id    the employee's unique identifier
     * @param nome  the employee's display name
     * @param login the employee's login identifier
     * @param senha the employee's password
     */
    public Funcionario(int id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    /**
     * Checks whether the given credentials match this employee's login
     * and password.
     *
     * @param login the login identifier to check
     * @param senha the password to check
     * @return {@code true} if both the login and password match this
     *         employee's credentials, {@code false} otherwise
     */
    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    /**
     * Returns this employee's unique identifier.
     *
     * @return the employee id
     */
    public int getId() { 
        return id; 
    }

    /**
     * Returns this employee's display name.
     *
     * @return the employee's name
     */
    public String getNome() { 
        return nome; 
    }
}
