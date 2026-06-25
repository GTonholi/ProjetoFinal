package com.projetocafeteria.model;

import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.pagamento.MetodoPagamento;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private Funcionario atendente;
    private List<Bebida> bebidas;
    private List<Comida> comidas;
    private double total;
    private StatusPedido status; 
    private MetodoPagamento metodoPagamento;
}
