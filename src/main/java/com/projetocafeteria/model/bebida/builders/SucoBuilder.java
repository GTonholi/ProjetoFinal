package com.projetocafeteria.model.bebida.builders;

import java.util.List;
import java.util.Scanner;

import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.Suco;
import com.projetocafeteria.model.bebida.decorators.AcucarDecorator;
import com.projetocafeteria.model.bebida.decorators.SaborSucoDecorator;
import com.projetocafeteria.exception.ItemNaoEncontradoException;

public class SucoBuilder implements BebidaBuilder {
    private Bebida suco;

    public SucoBuilder() {
        this.suco = new Suco();
    }

    @Override
    public BebidaBuilder interagirComUsuario(Scanner scanner) {
        System.out.println("\nEscolha o sabor do suco:");
        System.out.println("  [1] Laranja\n  [2] Abacaxi\n  [3] Morango\n  [4] Uva");
        System.out.print("Opção: ");
        int opcaoSabor = scanner.nextInt();
        scanner.nextLine();
        
        String sabor = switch (opcaoSabor) {
            case 1 -> "Laranja";
            case 2 -> "Abacaxi";
            case 3 -> "Morango";
            case 4 -> "Uva";
            default -> throw new ItemNaoEncontradoException("O sabor de número " + opcaoSabor + " não existe.");
        };
        
        this.suco = new SaborSucoDecorator(this.suco, sabor);

        System.out.println("\nDeseja adicionar açúcar? (1 - Sim / 2 - Não)");
        System.out.print("Opção: ");
        int querAcucar = scanner.nextInt();
        scanner.nextLine();

        if (querAcucar != 1 && querAcucar != 2) {
            throw new ItemNaoEncontradoException("Opção inválida (" + querAcucar + ") para o adicional de açúcar.");
        }

        if (querAcucar == 1) {
            this.suco = new AcucarDecorator(this.suco);
        }

        return this;
    }

    @Override
    public Bebida construir() {
        return this.suco;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Bebida base = new com.projetocafeteria.model.bebida.Suco();
        
        return new ItemCardapioInfo(
            base.exibirDescricao(),
            base.getValor(),
            List.of("Laranja", "Abacaxi", "Morango", "Uva"),
            List.of("Com Açucar")
        );
    }
}