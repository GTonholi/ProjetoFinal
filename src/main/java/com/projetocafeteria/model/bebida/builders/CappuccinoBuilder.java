package com.projetocafeteria.model.bebida.builders;

import java.util.Scanner;
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

        if (opcao == 2) {
            this.cappuccino = new AdicionalCappuccinoDecorator(this.cappuccino, "Canela");
        } else if (opcao == 3) {
            this.cappuccino = new AdicionalCappuccinoDecorator(this.cappuccino, "Chocolate em Pó");
        }
        return this;
    }

    @Override
    public Bebida construir() {
        return this.cappuccino;
    }
}