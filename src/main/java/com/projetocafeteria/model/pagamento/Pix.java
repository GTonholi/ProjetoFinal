package com.projetocafeteria.model.pagamento;

public class Pix implements MetodoPagamento{
    @Override
    public boolean realizarPagamento(double valor) {
        System.out.printf("Pagamento de R$ %.2f realizado via Pix.%n", valor);
        return true;
    }

    @Override
    public String getDescricao() {
        return "Pix";
    }
}