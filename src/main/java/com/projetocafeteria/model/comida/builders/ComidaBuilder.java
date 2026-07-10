package com.projetocafeteria.model.comida.builders;

import java.util.Scanner;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.DisponivelNoCardapio;

public interface ComidaBuilder extends DisponivelNoCardapio {
    ComidaBuilder interagirComUsuario(Scanner scanner);
    Comida construir();
}