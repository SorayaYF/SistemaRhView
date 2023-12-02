package br.com.sistemarh.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class AnoDeRepasse {

	private Integer ano;
	
	private List<MesDeRepasse> meses;
	
	public AnoDeRepasse() {
		this.meses = new ArrayList<MesDeRepasse>();
	}
	
}
