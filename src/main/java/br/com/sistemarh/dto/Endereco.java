package br.com.sistemarh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Endereco {

    @NotBlank(message = "O logradouro é obrigatória")
    private String logradouro;

    @NotBlank(message = "O bairro é obrigatória")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória")
    private String cidade;

    @NotBlank(message = "O estado é obrigatório")
    private String estado;
}
