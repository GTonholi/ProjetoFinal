package com.projetocafeteria.model.bebida.builder;

import java.util.Scanner;

import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Suco;
import com.projetocafeteria.model.bebida.decorator.AcucarDecorator;
import com.projetocafeteria.model.bebida.decorator.SaborSucoDecorator;

public class SucoBuilder implements BebidaBuilder {
    private Bebida suco;

    public SucoBuilder() {
        this.suco = new Suco();
    }

    @Override
    public BebidaBuilder interagirComUsuario(Scanner scanner) {
        System.out.println("Escolha o sabor do suco:");
        System.out.println("1 - Laranja\n2 - Abacaxi\n3 - Morango\n4 - Uva");
        int opcaoSabor = scanner.nextInt();
        
        String sabor = "Natural";
        switch (opcaoSabor) {
            case 1 -> sabor = "Laranja";
            case 2 -> sabor = "Abacaxi";
            case 3 -> sabor = "Morango";
            case 4 -> sabor = "Uva";
        }
        this.suco = new SaborSucoDecorator(this.suco, sabor);

        System.out.println("Deseja adicionar açúcar? (1 - Sim / 2 - Não)");
        int querAcucar = scanner.nextInt();
        if (querAcucar == 1) {
            this.suco = new AcucarDecorator(this.suco);
        }

        return this;
    }

    @Override
    public Bebida construir() {
        return this.suco;
    }
}