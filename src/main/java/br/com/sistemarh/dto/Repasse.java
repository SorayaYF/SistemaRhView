package br.com.sistemarh.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class Repasse {

    @Positive(message = "O ano deve ser positivo")
    @NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @Min(value = 1, message = "O mês deve ser maior ou igual a 1")
    @Max(value = 12, message = "O mês deve ser menor ou igual a 12")
    @Positive(message = "O mês deve ser positivo")
    private Integer mes;
    
    private String relatorio;

}
