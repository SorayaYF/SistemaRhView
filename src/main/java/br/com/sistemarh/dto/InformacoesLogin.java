package br.com.sistemarh.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InformacoesLogin {

    @NotBlank(message = "O login é obrigatório")
    private String login;

    @NotBlank(message = "A senha é obrigatória")
    private String senha;
}
