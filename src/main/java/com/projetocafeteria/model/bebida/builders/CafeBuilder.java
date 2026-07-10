package com.projetocafeteria.model.bebida.builders;

import java.util.List;
import java.util.Scanner;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Cafe;
import com.projetocafeteria.model.bebida.decorators.LeiteDecorator;

public class CafeBuilder implements BebidaBuilder {
    private Bebida cafe;

    public CafeBuilder() {
        this.cafe = new Cafe();
    }

    @Override
    public BebidaBuilder interagirComUsuario(Scanner scanner) {
        System.out.println("Deseja adicionar leite? +R$ 1.5  (1 - Sim / 2 - Não)");
        int querLeite = scanner.nextInt();
        scanner.nextLine();

        if (querLeite != 1 && querLeite != 2) {
            throw new ItemNaoEncontradoException("Opção inválida (" + querLeite + ") para o adicional de leite.");
        }

        if (querLeite == 1) {
            this.cafe = new LeiteDecorator(this.cafe);
        }
        return this;
    }

    @Override
    public Bebida construir() {
        return this.cafe;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Bebida base = new com.projetocafeteria.model.bebida.Cafe();
        
        return new ItemCardapioInfo(
            base.exibirDescricao(),
            base.getValor(),
            List.of("Preto"),
            List.of("Leite (+R$ 1,50)")
        );
    }
}