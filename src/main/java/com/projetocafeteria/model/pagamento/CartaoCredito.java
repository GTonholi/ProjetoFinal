package com.projetocafeteria.model.pagamento;

public class CartaoCredito implements MetodoPagamento{
    @Override
    public boolean realizarPagamento(double valor) {
        System.out.printf("Pagamento de R$ %.2f aprovado no cartão de crédito.%n", valor);
        return true;
    }

    @Override
    public String getDescricao(){
        return "Cartao de Credito";
    }
}
