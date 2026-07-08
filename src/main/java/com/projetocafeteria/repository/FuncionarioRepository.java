package com.projetocafeteria.repository;

import java.util.ArrayList;
import java.util.List;

import com.projetocafeteria.model.Funcionario;

public final class FuncionarioRepository {
    private final List<Funcionario> funcionarios = new ArrayList<>();
    private int proximoId = 1;

    public FuncionarioRepository(){
        cadastrar("Ana Souza", "ana", "1234");
        cadastrar("Carlos Lima", "carlos", "abcd");
    }

    public Funcionario cadastrar(String nome, String login, String senha) {
        Funcionario f = new Funcionario(proximoId++, nome, login, senha);
        funcionarios.add(f);
        return f;
    }

    public List<Funcionario> listarTodos() {
        return funcionarios;
    }

    public Funcionario buscarPorLoginESenha(String login, String senha) {
        for (Funcionario f : funcionarios) {
            if (f.autenticar(login, senha)) {
                return f;
        }
        }
        return null;
    }
}
