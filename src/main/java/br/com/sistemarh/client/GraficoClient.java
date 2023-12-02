package br.com.sistemarh.client;

import java.util.Map;
import java.util.Objects;

import org.json.JSONObject;
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
public class GraficoClient {

    private final String RESOURCE = "/grafico";

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
    	
    	JSONObject body = new JSONObject();
    	
    	body.put("ano", ano);
		
        if (Objects.nonNull(mes)) {
        	body.put("mes", mes);
        }
        
        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(body.toMap(), aplicador.aplicar(tokenDeAcesso));

        ResponseEntity<DadosDoGrafico> dadosDoGraficoResponseEntity = httpClient.exchange(
                urlDaApi + RESOURCE, HttpMethod.POST, request, DadosDoGrafico.class);

        return dadosDoGraficoResponseEntity.getBody();
    }
}
