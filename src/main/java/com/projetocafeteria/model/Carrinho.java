package com.projetocafeteria.model;

import java.util.HashMap;

import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.comida.Comida;

public class Carrinho {
    private final HashMap<Bebida, Integer> bebidas = new HashMap<>();
    private final HashMap<Comida, Integer> comidas = new HashMap<>();

    public void AdicionarBebida(Bebida bebida, int quant){
        if(!(bebidas.containsKey(bebida))){
            bebidas.put(bebida, quant); 
        }
        else{
            bebidas.put(bebida, bebidas.get(bebida)+ quant);
        }
    }

    public void AdicionarComida(Comida comida, int quant){
        if(!(comidas.containsKey(comida))){
            comidas.put(comida, quant); 
        }
        else{
            comidas.put(comida, comidas.get(comida)+ quant);
        }
    }

    public double calcularTotal(){
        double totalComida = comidas.entrySet().stream().mapToDouble(e -> e.getKey().getValor() * e.getValue()).sum();
        double totalBebida = bebidas.entrySet().stream().mapToDouble(e -> e.getKey().getValor() * e.getValue()).sum();

        return totalComida + totalBebida;
    }

    public boolean estaVazio() {
        return comidas.isEmpty() && bebidas.isEmpty();
    }
}
