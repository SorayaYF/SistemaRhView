package br.com.sistemarh.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.sistemarh.enums.Status;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonInclude(Include.NON_NULL)
public class Entregador {

	private Integer id;
	
	@NotBlank(message = "O nome do entregador é obrigatório")
	private String nome;
	
	@NotBlank(message = "O telefone do entregador é obrigatório")
	private String telefone;
	
	@NotBlank(message = "A CNH do entregador é obrigatória")
	private String numeroHabilitacao;
	
	@NotBlank(message = "O CPF do entregador é obrigatório")
	private String cpf;
	
    @NotNull(message = "O seguro de vida é obrigatório")
    private Boolean seguroDeVida;
	
	@Valid
	@ToString.Exclude
	private Endereco endereco;
	
	private Status status;
	
	public Entregador() {
		this.status = Status.A;
		this.endereco = new Endereco();
	}
	
	public boolean isPersistido() {
		return getId() != null && getId() > 0;
	}
	
	public boolean isAtivo() {
		return getStatus() == Status.A;
	}
	
}
