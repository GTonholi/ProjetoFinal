package com.projetocafeteria.model;

import java.util.List;

public class ItemCardapioInfo {
    private final String nome;
    private final double precoBase;
    private final List<String> subopcoes;
    private final List<String> adicionais;

    public ItemCardapioInfo(String nome, double precoBase, List<String> subopcoes, List<String> adicionais) {
        this.nome = nome;
        this.precoBase = precoBase;
        this.subopcoes = subopcoes;
        this.adicionais = adicionais;
    }

    public String getNome() { return nome; }
    public double getPrecoBase() { return precoBase; }
    public List<String> getSubopcoes() { return subopcoes; }
    public List<String> getAdicionais() { return adicionais; }
}