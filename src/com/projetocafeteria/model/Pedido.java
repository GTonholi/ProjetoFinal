package com.projetocafeteria.model;

import com.projetocafeteria.model.status.StatusPedido;
import com.projetocafeteria.model.status.StatusPendente;

public class Pedido {
    private static int proximoId = 1;

    private final int id;
    private final Cliente cliente;
    //private Funcionario atendente; // ainda não sei se vai ser necessário
    private StatusPedido status; 
    private Preparo preparo;
    private final Carrinho carrinho = new Carrinho();
    //private MetodoPagamento metodoPagamento;

    public Pedido(Cliente cliente){
        this.preparo = Preparo.PENDENTE;
        this.cliente = cliente;
        this.id = proximoId++;
        this.status = new StatusPendente();
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

    public boolean estaCancelado(){
        return status.estaCancelado();
    }

    public boolean pedidoEstaEntregue(){
        return this.preparo.equals(Preparo.ENTREGUE);
    }

    public void avancarPreparo() {
        Preparo[] valores = Preparo.values();
        int proximoIndex = preparo.ordinal() + 1;

        if (proximoIndex < valores.length) {
            this.preparo = valores[proximoIndex];
        } else {
            System.out.println("Pedido já está no estado final (" + preparo + ").");
        }
    }

    public int getId(){
        return id;
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

    public Carrinho getCarrinho() {
        return carrinho;
    }
}
