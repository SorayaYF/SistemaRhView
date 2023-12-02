package br.com.sistemarh.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class MesDeRepasse {
	
	private String nome;
	
	private BigDecimal valorRepassado;

}
