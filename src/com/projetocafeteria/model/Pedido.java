package com.projetocafeteria.model;

import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.pagamento.MetodoPagamento;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private Funcionario atendente;
    private List<Bebida> bebidas = new ArrayList<>();
    private List<Comida> comidas = new ArrayList<>();
    private double total;
    private StatusPedido status; 
    private MetodoPagamento metodoPagamento;

    public Pedido(){
        this.total = 0.0;
        
    }

    public void AdicionarBebida(Bebida bebida){
        bebidas.add(bebida);
    }

    public void AdicionarComida(Comida comida){
        comidas.add(comida);
    }
}
