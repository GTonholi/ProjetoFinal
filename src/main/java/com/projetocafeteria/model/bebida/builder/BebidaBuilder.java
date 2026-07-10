package com.projetocafeteria.model.bebida.builder;

import java.util.Scanner;

import com.projetocafeteria.model.bebida.Bebida;

public interface BebidaBuilder {

    BebidaBuilder interagirComUsuario(Scanner scanner);
    
    Bebida construir();
}