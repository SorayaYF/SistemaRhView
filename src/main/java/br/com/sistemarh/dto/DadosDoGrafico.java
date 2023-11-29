package br.com.sistemarh.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class DadosDoGrafico {

	@Positive(message = "O ano deve ser positivo")
	@NotNull(message = "O ano é obrigatório")
    private Integer ano;

    @Positive(message = "O mês deve ser positivo")
    private Integer mes;

    private BigDecimal volumeMovimentadoDeRepasses;

}
