package com.projetocafeteria.model.comida.builders;

import java.util.List;
import java.util.Scanner;

import com.projetocafeteria.exception.ItemNaoEncontradoException;
import com.projetocafeteria.model.ItemCardapioInfo;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.comida.PaoDeQueijo;
import com.projetocafeteria.model.comida.decorators.RecheioPaoDeQueijoDecorator;

public class PaoDeQueijoBuilder implements ComidaBuilder {
    private Comida paoDeQueijo;

    public PaoDeQueijoBuilder() {
        this.paoDeQueijo = new PaoDeQueijo();
    }

    @Override
    public ComidaBuilder interagirComUsuario(Scanner scanner) {
        System.out.println("\nDeseja rechear com Requeijão cremoso? +R$2.5 (1 - Sim / 2 - Não)");
        System.out.print("Opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        if (opcao != 1 && opcao != 2) {
            throw new ItemNaoEncontradoException("Opção inválida (" + opcao + ") para o adicional de Requeijão.");
        }

        if (opcao == 1) {
            this.paoDeQueijo = new RecheioPaoDeQueijoDecorator(this.paoDeQueijo);
        }
        return this;
    }

    @Override
    public Comida construir() {
        return this.paoDeQueijo;
    }

    @Override
    public ItemCardapioInfo obterInformacaoComercial() {
        Comida base = new com.projetocafeteria.model.comida.PaoDeQueijo();
        
        return new ItemCardapioInfo(
            base.exibirDescricao(),
            base.getValor(),
            List.of(),
            List.of("Recheado com Requeijão (+R$ 2,50)")
        );
    }
}