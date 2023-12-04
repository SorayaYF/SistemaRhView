package br.com.sistemarh.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.sistemarh.dto.AnoDeRepasse;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Component
public class GraficoClient {

    private final String RESOURCE = "/grafico";

    @Value("${url-api}")
    private String urlDaApi;

    @Autowired
    private RestTemplate httpClient;

    @Setter
    private String tokenDeAcesso;
    
    public AnoDeRepasse obterAnoDeRepassePor(
    		@NotNull(message = "O ano é obrigatório") 
    		Integer ano) {

        ResponseEntity<AnoDeRepasse> anoDeRepasseEntity = httpClient.exchange(
                urlDaApi + RESOURCE + "/ano/" + ano, HttpMethod.GET, null, AnoDeRepasse.class);

        return anoDeRepasseEntity.getBody();

    }
}
