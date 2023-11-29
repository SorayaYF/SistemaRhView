package br.com.sistemarh.dto;

import java.util.List;

import lombok.Data;

@Data
public class Paginacao<T> {

	private Integer totalDeItens;
	
	private Integer paginaAtual;
	
	private Integer totalDePaginas;
	
	private List<T> listagem;
	
	public int getTotalDePaginas() {
		return totalDePaginas - 1;
	}
	
}
