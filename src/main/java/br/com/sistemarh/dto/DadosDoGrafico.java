package br.com.sistemarh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.ToString;

@Data
@JsonInclude(Include.NON_NULL)
public class DadosDoGrafico {

	@Positive(message = "O ano deve ser positivo")
	@NotNull(message = "O ano é obrigatório")
    private Integer ano;

	@Valid
	@ToString.Exclude
	private Mes mes;

}
