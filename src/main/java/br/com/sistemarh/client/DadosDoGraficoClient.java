package br.com.sistemarh.client;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.sistemarh.dto.DadosDoGrafico;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Component
public class DadosDoGraficoClient {

    private final String RESOURCE = "/dados-do-grafico";

    @Value("${url-api}")
    private String urlDaApi;

    @Autowired
    private AplicadorDeToken aplicador;

    @Autowired
    private RestTemplate httpClient;

    @Setter
    private String tokenDeAcesso;

    public DadosDoGrafico obterDadosDoGrafico(
    		@NotNull(message = "O ano é obrigatório") 
    		Integer ano,
    		@NotNull(message = "O mês é obrigatório") 
    		Integer mes) {

        StringBuilder queryParams = new StringBuilder();
        queryParams.append("?ano=").append(ano);
		
        if (Objects.nonNull(mes)) {
        	queryParams.append("&mes=").append(mes);
        }
        
        HttpEntity<DadosDoGrafico> request = new HttpEntity<>(aplicador.aplicar(tokenDeAcesso));

        ResponseEntity<DadosDoGrafico> dadosDoGraficoResponseEntity = httpClient.exchange(
                urlDaApi + RESOURCE + queryParams, HttpMethod.GET, request, DadosDoGrafico.class);

        return dadosDoGraficoResponseEntity.getBody();
    }
}
