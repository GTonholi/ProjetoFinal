package com.projetocafeteria.model;

public class Funcionario {
    private final int id;
    private final String nome;
    private final String login;
    private final String senha;

    public Funcionario(int id, String nome, String login, String senha) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
    }

    public boolean autenticar(String login, String senha) {
        return this.login.equals(login) && this.senha.equals(senha);
    }

    public int getId() { 
        return id; 
    }
    public String getNome() { 
        return nome; 
    }
}
