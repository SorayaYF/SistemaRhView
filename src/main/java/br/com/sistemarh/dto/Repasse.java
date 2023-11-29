package br.com.sistemarh.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class Repasse {
	
    @Positive(message = "O ano deve ser positivo")
    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @Positive(message = "O mês deve ser positivo")
    @NotNull(message = "O mês é obrigatório")
    private Integer mes;

}
