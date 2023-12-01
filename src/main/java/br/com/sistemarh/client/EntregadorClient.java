package br.com.sistemarh.client;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.sistemarh.dto.Entregador;
import br.com.sistemarh.dto.Paginacao;
import br.com.sistemarh.enums.Status;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Setter;

@Component
public class EntregadorClient {

	private final String RESOURCE = "/entregador";
	
	@Value("${url-api}")
	private String urlDaApi;
	
	@Autowired
	private AplicadorDeToken aplicador;
	
	@Autowired
	private RestTemplate httpClient;
	
	@Setter
	private String tokenDeAcesso;
	
	public Entregador inserir(
			@Valid
			@NotNull(message = "O novo entregador não pode ser nulo")
			Entregador novoEntregador) {
		
		HttpEntity<Entregador> request = new HttpEntity<Entregador>(
				novoEntregador, aplicador.aplicar(tokenDeAcesso));
		
		URI location = httpClient.postForLocation(urlDaApi + RESOURCE, request);		

		request = new HttpEntity<Entregador>(aplicador.aplicar(tokenDeAcesso));		
		
		ResponseEntity<Entregador> entregadorSalvo = httpClient.exchange(
				urlDaApi + location, HttpMethod.GET, request, Entregador.class);
		
		return entregadorSalvo.getBody();

	}
	
	public Entregador atualizar(
			@Valid
			@NotNull(message = "A entregador salva não pode ser nula")
			Entregador entregadorSalvo) {
		
		HttpEntity<Entregador> request = new HttpEntity<Entregador>(
				entregadorSalvo, aplicador.aplicar(tokenDeAcesso));
		
		ResponseEntity<Entregador> entregadorAtualizada = httpClient.exchange(
				urlDaApi + RESOURCE, HttpMethod.PUT, request, Entregador.class);
		
		return entregadorAtualizada.getBody();
		
	}
	
	public void atualizarPor(
			@NotNull(message = "O id do entregador é obrigatório")
			@Positive(message = "O id do entregador deve positivo")
			Integer id,	
			@NotNull(message = "O status do entregador é obrigatório")
			Status status) {
		
		HttpEntity<Entregador> request = new HttpEntity<Entregador>(aplicador.aplicar(tokenDeAcesso));
		
		this.httpClient.patchForObject(urlDaApi + RESOURCE + "/id/" + id + "/status/" + status, request, Void.class);
		
	}
	
	public Entregador removerPor(
			@NotNull(message = "O id do entregador é obrigatório")
			@Positive(message = "O id do entregador deve positivo")
			Integer id) {			
		
		HttpEntity<Entregador> request = new HttpEntity<Entregador>(aplicador.aplicar(tokenDeAcesso));
		
		ResponseEntity<Entregador> entregadorRemovido = httpClient.exchange(
				urlDaApi + RESOURCE + "/id/" + id, HttpMethod.DELETE, request, Entregador.class);
		
		return entregadorRemovido.getBody();
		
	}
	
	public Paginacao<Entregador> listarPor(
			@NotBlank(message = "O nome deve conter mais de 3 caracteres") 
			String nome,
            String numeroHabilitacao,
            String cpf,
            String telefone,
			@PositiveOrZero(message = "A página de registros deve ser positiva") 
			Integer pagina){
		
		StringBuilder queryParams = new StringBuilder();
		queryParams.append("?nome=").append(nome);
		
        if (StringUtils.isNotBlank(numeroHabilitacao)) {
            queryParams.append("&numeroHabilitacao=").append(numeroHabilitacao);
        }
        if (StringUtils.isNotBlank(cpf) && !cpf.equals("   .   .   -  ")) {
            queryParams.append("&cpf=").append(cpf);
        }
        if (StringUtils.isNotBlank(telefone) && !telefone.equals("(  )     -    ")) {
            queryParams.append("&telefone=").append(telefone);
        }
		queryParams.append("&pagina=").append(pagina);
		
		HttpEntity<Paginacao<Entregador>> request = new HttpEntity<Paginacao<Entregador>>(
				aplicador.aplicar(tokenDeAcesso));
		
		ResponseEntity<Paginacao<Entregador>> entregadoresEncontrados = httpClient.exchange(
				urlDaApi + RESOURCE + queryParams, HttpMethod.GET, request, 
				new ParameterizedTypeReference<Paginacao<Entregador>>(){});
		
		return entregadoresEncontrados.getBody();

	}
	
	public Entregador buscarPor(
			@NotNull(message = "O id do entregador é obrigatório")
			@Positive(message = "O id do entregador deve ser maior que zero")
			Integer id) {
		
		HttpEntity<Entregador> request = new HttpEntity<Entregador>(aplicador.aplicar(tokenDeAcesso));		
		
		ResponseEntity<Entregador> entregadorEncontrado = httpClient.exchange(
				urlDaApi + RESOURCE + "/id/" + id, HttpMethod.GET, request, Entregador.class);
		
		return entregadorEncontrado.getBody();
		
	}
	
}
