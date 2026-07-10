package com.projetocafeteria.model.bebida.builder;

import java.util.Scanner;

import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Cafe;
import com.projetocafeteria.model.bebida.decorator.LeiteDecorator;

public class CafeBuilder implements BebidaBuilder {
    private Bebida cafe;

    public CafeBuilder() {
        this.cafe = new Cafe();
    }

    @Override
    public BebidaBuilder interagirComUsuario(Scanner scanner) {
        System.out.println("Deseja adicionar leite? +R$ 1.5  (1 - Sim / 2 - Não)");
        int querLeite = scanner.nextInt();
        if (querLeite == 1) {
            this.cafe = new LeiteDecorator(this.cafe);
        }
        return this;
    }

    @Override
    public Bebida construir() {
        return this.cafe;
    }
}