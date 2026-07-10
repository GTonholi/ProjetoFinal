package com.projetocafeteria.model.bebida.builders;

import java.util.List;
import java.util.Scanner;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Cappuccino;
import com.projetocafeteria.model.bebida.decorators.AdicionalCappuccinoDecorator;

public class CappuccinoBuilder implements BebidaBuilder {
    private Bebida cappuccino;

    public CappuccinoBuilder() {
        this.cappuccino = new Cappuccino();
    }

    @Override
    public BebidaBuilder interagirComUsuario(Scanner scanner) {
        System.out.println("\nDeseja algum adicional no seu Cappuccino? +R$1.0");
        System.out.println("  [1] Nenhum (Tradicional)\n  [2] Canela\n  [3] Chocolate em Pó");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {}
            case 2 -> this.cappuccino = new AdicionalCappuccinoDecorator(this.cappuccino, "Canela");
            case 3 -> this.cappuccino = new AdicionalCappuccinoDecorator(this.cappuccino, "Chocolate em Pó");
            default -> throw new ItemNaoEncontradoException("Opção inválida (" + opcao + ") para o adicional do Cappuccino.");
        }
        return this;
    }

    @Override
    public Bebida construir() {
        return this.cappuccino;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Bebida base = new com.projetocafeteria.model.bebida.Cappuccino();
        
        return new ItemCardapioInfo(
            base.exibirDescricao(),
            base.getValor(),
            List.of(),
            List.of("Canela" , "Chocolate em Pó")
        );
    }
}