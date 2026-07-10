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
    public ComidaBuilder interagirComUsuario(Scanner scanner) {
        System.out.println("\nEscolha o sabor do bolo:");
        System.out.println("  [1] Chocolate\n  [2] Laranja\n  [3] Formigueiro");
        System.out.print("Opção: ");
        int opcaoSabor = scanner.nextInt();
        scanner.nextLine();

        String sabor = switch (opcaoSabor) {
            case 1 -> "Chocolate";
            case 2 -> "Laranja";
            case 3 -> "Formigueiro";
            default -> throw new ItemNaoEncontradoException("O sabor de número " + opcaoSabor + " não existe.");
        };
        this.bolo = new SaborBoloDecorator(this.bolo, sabor);

        System.out.print("Deseja adicionar cobertura? +R$3.0 (1 - Sim / 2 - Não): ");
        int querCobertura = scanner.nextInt();
        scanner.nextLine();

        if (querCobertura != 1 && querCobertura != 2) {
            throw new ItemNaoEncontradoException("Opção inválida (" + querCobertura + ") para a cobertura.");
        }

        if (querCobertura == 1) {
            this.bolo = new CoberturaDecorator(this.bolo);
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