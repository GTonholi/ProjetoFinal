package com.projetocafeteria.model.pagamento;

public class Dinheiro implements MetodoPagamento {
    @Override
    public boolean realizarPagamento(double valor) {
        System.out.printf("Pagamento de R$ %.2f recebido em dinheiro.%n", valor);
        return true;
    }

    @Override
    public String getDescricao() {
        return "Dinheiro";
    }
}
