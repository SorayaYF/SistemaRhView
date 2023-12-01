package br.com.sistemarh.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credencial {

	@NotBlank(message = "O email é obrigatório")
	private String email;

	@NotBlank(message = "A senha é obrigatória")
	private String senha;

}
