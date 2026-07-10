package com.projetocafeteria.model.comida.builders;

import java.util.Scanner;
import com.projetocafeteria.model.comida.Comida;

public interface ComidaBuilder {
    ComidaBuilder interagirComUsuario(Scanner scanner);
    Comida construir();
}