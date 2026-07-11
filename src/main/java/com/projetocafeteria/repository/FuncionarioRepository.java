package com.projetocafeteria.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.projetocafeteria.model.Funcionario;

/**
 * Repository responsible for storing and retrieving {@link Funcionario}
 * records.
 * <p>
 * This class abstracts away where and how employee data is persisted.
 * The current implementation keeps records in memory and seeds a couple
 * of test employees on construction, but the class is designed so that a
 * future implementation (e.g. backed by a database) could replace it
 * without requiring changes to {@link com.projetocafeteria.service.LoginService}
 * or any other consumer.
 * <p>
 * Authentication logic itself is delegated to {@link Funcionario#autenticar}
 * rather than being performed here, keeping this class focused solely on
 * data storage and lookup, not on credential-comparison rules.
 * <p>
 * Declared {@code final} because this is currently the sole, definitive
 * in-memory implementation of employee storage.
 * 
 */
public final class FuncionarioRepository {
    private final List<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

     /**
     * Creates a new repository, seeding it with a couple of test
     * employees.
     * <p>
     * 
     */
    public FuncionarioRepository(){
        cadastrar("Ana Souza", "ana", "1234");
        cadastrar("Carlos Lima", "carlos", "abcd");
    }

    /**
     * Registers a new employee, automatically assigning it the next
     * available id.
     *
     * @param nome  the employee's display name
     * @param login the employee's login identifier
     * @param senha the employee's password
     * @return the newly created {@link Funcionario}, with its id already assigned
     */
    public Funcionario cadastrar(String nome, String login, String senha) {
        Funcionario f = new Funcionario(proximoId++, nome, login, senha);
        funcionarios.add(f);
        return f;
    }

    /**
     * Returns all registered employees.
     *
     * @return the unmodifiable list of all registered {@link Funcionario} instances;
     *         never {@code null}
     */
    public List<Funcionario> listarTodos() {
        return Collections.unmodifiableList(funcionarios);
    }

    /**
     * Searches for an employee whose credentials match the given login
     * and password.
     * <p>
     * The actual credential comparison is delegated to
     * {@link Funcionario#autenticar}, so this method has no knowledge of
     * how a match is determined.
     *
     * @param login the login identifier to authenticate
     * @param senha the password to authenticate
     * @return the matching {@link Funcionario}, or {@code null} if no
     *         employee matches the given credentials
     */
    public Funcionario buscarPorLoginESenha(String login, String senha) {
        for (Funcionario f : funcionarios) {
            if (f.autenticar(login, senha)) {
                return f;
        }
        }
        return null;
    }
}
