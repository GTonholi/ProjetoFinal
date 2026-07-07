package com.projetocafeteria.model;

import com.projetocafeteria.model.status.StatusPedido;

public class Pedido {
    private int id;
    private Cliente cliente;
    private Funcionario atendente; // ainda não sei se vai ser necessário
    private StatusPedido status; 
    private Preparo preparo;
    private final Carrinho carrinho = new Carrinho();
    //private MetodoPagamento metodoPagamento;

    public Pedido(){
        this.preparo = Preparo.PENDENTE;
    }

    public void realizarPagamento(){
        status.pagar(this);
    }

    public void cancelarPedido(){
        status.cancelar(this);
    }

    public void setStatus(StatusPedido status){
        this.status = status;
    }

    public void prepararPedido(){
        this.preparo = Preparo.PREPARANDO;
    }

    public String getNomeCliente(){
        return cliente.getNome();
    }

    public double calcularTotal(){
        return carrinho.calcularTotal();
    }

    public Preparo consultarPreparo(){
        return preparo;
    }
}
