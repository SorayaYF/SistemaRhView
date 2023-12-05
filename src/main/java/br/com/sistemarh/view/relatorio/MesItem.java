package br.com.sistemarh.view.relatorio;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MesItem {

    private int numeroMes;
    
    private String nomeMes;
    
    @Override
    public String toString() {
        return nomeMes;
    }
}
