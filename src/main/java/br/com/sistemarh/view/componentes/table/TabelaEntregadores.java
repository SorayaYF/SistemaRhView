package br.com.sistemarh.view.componentes.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.sistemarh.dto.Entregador;

public class TabelaEntregadores extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	
	private final int QTDE_COLUNAS = 5;
	
	private List<Entregador> entregadores;

	public TabelaEntregadores() {
		this.entregadores = new ArrayList<>();
	}
	
	public TabelaEntregadores(List<Entregador> entregadores) {
		this();
		if (entregadores != null && !entregadores.isEmpty()) {		
			this.entregadores = entregadores;
		}
	}

	@Override
	public int getRowCount() {	
		return entregadores.size();
	}
	
	@Override
	public int getColumnCount() {
		return QTDE_COLUNAS;
	}
	
	@Override
	public String getColumnName(int column) {
		if (column == 0) {
			return "ID";
		}else if (column == 1) {
			return "Nome";
		}else if (column == 2) {
			return "Telefone";
		}else if (column == 3) {
			return "CNH";
		}else if (column == 4) {
			return "CPF";
		}
		throw new IllegalArgumentException("Indíce inválido");
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return entregadores.get(rowIndex).getId();
		}else if (columnIndex == 1) {
			return entregadores.get(rowIndex).getNome();
		} else if (columnIndex == 2) {
			return entregadores.get(rowIndex).getTelefone();
		} else if (columnIndex == 3) {
			return entregadores.get(rowIndex).getNumeroHabilitacao();
		} else if (columnIndex == 4) {
			return entregadores.get(rowIndex).getCpf();
		} 
		throw new IllegalArgumentException("Índice inválido");
	}
	
	public Entregador getPor(int rowIndex) {
		return entregadores.get(rowIndex);
	}	

}
