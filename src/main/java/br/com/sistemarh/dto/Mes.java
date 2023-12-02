package br.com.sistemarh.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Mes {

    private String nome;

    private BigDecimal volumeMovimentadoDeRepasses;
}
