package com.projetocafeteria.model.bebida.builders;

import java.util.List;
import java.util.Scanner;

import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.bebida.decorators.ComGasDecorator;
import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.bebida.Agua;;

public class AguaBuilder implements BebidaBuilder {
    private Bebida agua;

    public AguaBuilder() {
        this.agua = new Agua();
    }

    @Override
    public BebidaBuilder interagirComUsuario(Scanner scanner) {
        System.out.println("Como você deseja a sua água?");
        System.out.println("  [1] Sem Gás");
        System.out.println("  [2] Com Gás");
        System.out.print("Opção: ");
        
        int opcaoGas = scanner.nextInt();
        scanner.nextLine();

        if (opcaoGas != 1 && opcaoGas != 2) {
            throw new ItemNaoEncontradoException("Opção inválida (" + opcaoGas + ") para o tipo de água.");
        }

        if (opcaoGas == 2) {
            this.agua = new ComGasDecorator(this.agua);
        }
        
        return this;
    }

    @Override
    public Bebida construir() {
        return this.agua;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Bebida base = new com.projetocafeteria.model.bebida.Agua();
        
        return new ItemCardapioInfo(
            base.exibirDescricao(),
            base.getValor(),
            List.of("Sem Gás", "Com Gás"),
            List.of()
        );
    }
}