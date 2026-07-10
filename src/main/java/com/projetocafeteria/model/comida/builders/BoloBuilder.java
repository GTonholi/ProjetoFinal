package com.projetocafeteria.model.comida.builders;

import java.util.Scanner;
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
    public ComidaBuilder interagirComUsuario(Scanner scanner) {
        System.out.println("\nEscolha o sabor do bolo:");
        System.out.println("  [1] Chocolate\n  [2] Laranja\n  [3] Formigueiro");
        System.out.print("Opção: ");
        int opcaoSabor = scanner.nextInt();
        scanner.nextLine();
        
        String sabor = "Simples";
        switch (opcaoSabor) {
            case 1 -> sabor = "Chocolate";
            case 2 -> sabor = "Laranja";
            case 3 -> sabor = "Formigueiro";
        }
        this.bolo = new SaborBoloDecorator(this.bolo, sabor);

        System.out.print("Deseja adicionar cobertura? +R$3.0 (1 - Sim / 2 - Não): ");
        int querCobertura = scanner.nextInt();
        scanner.nextLine();
        if (querCobertura == 1) {
            this.bolo = new CoberturaDecorator(this.bolo);
        }

        return this;
    }

    @Override
    public Comida construir() {
        return this.bolo;
    }
}