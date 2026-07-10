package com.projetocafeteria.model.comida.builders;

import java.util.Scanner;
import com.projetocafeteria.model.comida.Comida;
import com.projetocafeteria.model.comida.MistoQuente;

public class MistoQuenteBuilder implements ComidaBuilder {
    @Override
    public ComidaBuilder interagirComUsuario(Scanner scanner) {
        return this;
    }

    @Override
    public Comida construir() {
        return new MistoQuente();
    }
}