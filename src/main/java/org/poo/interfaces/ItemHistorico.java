package org.poo.interfaces;

import java.time.LocalDate;

public interface ItemHistorico {
    LocalDate getDataEvento();

    String getTipoEvento();

    String getDescricaoDetalhada();
}
