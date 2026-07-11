package com.projetocafeteria.model.comida.builders;

import java.util.List;
import java.util.Scanner;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.comida.Bolo;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.comida.decorators.CoberturaDecorator;
import com.projetocafeteria.model.comida.decorators.SaborBoloDecorator;

public class BoloBuilder implements ComidaBuilder {
    private Comida bolo;

    public BoloBuilder() {
        this.bolo = new Bolo();
    }

    @Override
    public ComidaBuilder comSubopcao(String nomeSubopcao) {
        if (nomeSubopcao == null) return this;
        
        switch (nomeSubopcao) {
            case "Chocolate":
            case "Laranja":
            case "Formigueiro":
                this.bolo = new SaborBoloDecorator(this.bolo, nomeSubopcao);
                break;
            default:
                throw new ItemNaoEncontradoException("O sabor " + nomeSubopcao + " não existe para este bolo.");
        }
        return this;
    }

    @Override
    public ComidaBuilder comAdicional(String nomeAdicional) {
        if (nomeAdicional == null) return this;
        
        if (nomeAdicional.equals("Cobertura (+R$ 3,00)")) {
            this.bolo = new CoberturaDecorator(this.bolo);
        } else {
            throw new ItemNaoEncontradoException("O adicional " + nomeAdicional + " não é válido para este bolo.");
        }
        return this;
    }

    @Override
    public Comida construir() {
        return this.bolo;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Comida base = new com.projetocafeteria.model.comida.Bolo();
        
        return new ItemCardapioInfo(
            base.exibirDescricao(),
            base.getValor(),
            List.of("Chocolate", "Laranja", "Formigueiro"),
            List.of("Cobertura (+R$ 3,00)")
        );
    }
}