package com.projetocafeteria.repository;

import java.util.List;
import com.projetocafeteria.model.Funcionario;

/**
 * Interface that defines the contract for an employee repository.
 * <p>
 * This interface abstracts the storage mechanism for {@link Funcionario}
 * instances, adhering to the Dependency Inversion Principle (DIP).
 */
public interface IFuncionarioRepository {

    /**
     * Registers a new employee.
     *
     * @param nome  the employee's display name
     * @param login the employee's login identifier
     * @param senha the employee's password
     * @return the newly created {@link Funcionario}
     */
    Funcionario cadastrar(String nome, String login, String senha);

    /**
     * Returns all registered employees.
     *
     * @return a list of all registered {@link Funcionario} instances
     */
    List<Funcionario> listarTodos();

    /**
     * Searches for an employee whose credentials match the given login and password.
     *
     * @param login the login identifier to authenticate
     * @param senha the password to authenticate
     * @return the matching {@link Funcionario}, or {@code null} if none matches
     */
    Funcionario buscarPorLoginESenha(String login, String senha);
}
