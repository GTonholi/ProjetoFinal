package com.projetocafeteria.model.comida.builders;

import java.util.List;
import java.util.Scanner;
import com.projetocafeteria.model.comida.Torta;
import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
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

        String sabor = switch (opcao) {
            case 1 -> "Frango";
            case 2 -> "Palmito";
            case 3 -> "Maçã";
            default -> throw new ItemNaoEncontradoException("O sabor de número " + opcao + " não existe.");
        };
        this.torta = new SaborTortaDecorator(this.torta, sabor);
        return this;
    }

    @Override
    public Comida construir() {
        return this.torta;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Comida base = new com.projetocafeteria.model.comida.Torta();
        
        return new ItemCardapioInfo(
            base.exibirDescricao(),
            base.getValor(),
            List.of("Frango", "Palmito", "Maçã"),
            List.of()
        );
    }
}