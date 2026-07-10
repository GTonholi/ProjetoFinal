package com.projetocafeteria.model.comida.builders;

import java.util.Scanner;
import com.projetocafeteria.model.comida.Torta;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.comida.decorators.SaborTortaDecorator;

public class TortaBuilder implements ComidaBuilder {
    private Comida torta;

    public TortaBuilder() {
        this.torta = new Torta();
    }

    @Override
    public ComidaBuilder interagirComUsuario(Scanner scanner) {
        System.out.println("\nEscolha o sabor da torta:");
        System.out.println("  [1] Frango\n  [2] Palmito\n  [3] Maçã");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        String sabor = "Tradicional";
        switch (opcao) {
            case 1 -> sabor = "Frango";
            case 2 -> sabor = "Palmito";
            case 3 -> sabor = "Maçã";
        }
        this.torta = new SaborTortaDecorator(this.torta, sabor);
        return this;
    }

    @Override
    public Comida construir() {
        return this.torta;
    }
}