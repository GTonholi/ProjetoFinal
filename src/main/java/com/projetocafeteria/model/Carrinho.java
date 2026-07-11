package com.projetocafeteria.model;

import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.comida.Comida;

public class Carrinho {
    private final ItemContainer<Bebida> bebidas = new ItemContainer<>();
    private final ItemContainer<Comida> comidas = new ItemContainer<>();

    public void adicionarBebida(Bebida bebida, int quant){
       bebidas.adicionar(bebida, quant);
    }

    public void adicionarComida(Comida comida, int quant){
       comidas.adicionar(comida, quant);
    }

    public double calcularTotal(){
        return bebidas.calcularTotal() + comidas.calcularTotal();
    }

    public boolean estaVazio() {
        return comidas.estaVazio() && bebidas.estaVazio();
    }
}
