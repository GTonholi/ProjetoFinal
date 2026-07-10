package com.projetocafeteria.model.bebida.builders;

import java.util.Scanner;
import com.projetocafeteria.model.bebida.Bebida;
import com.projetocafeteria.model.DisponivelNoCardapio;

public interface BebidaBuilder extends DisponivelNoCardapio {
    BebidaBuilder interagirComUsuario(Scanner scanner);
    Bebida construir();
}