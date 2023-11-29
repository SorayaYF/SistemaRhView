package br.com.sistemarh.view.componentes.grafico;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoDeBarras {
	
	public ChartPanel plotarPor(DefaultCategoryDataset dataset, 
			String tituloDografico, String tituloColunaX, String tituloColunaY,
			int largura, int altura, int posicaoX, int posicaoY) {
		
		JFreeChart graficoDeBarras = ChartFactory.createBarChart(tituloDografico, 
				tituloColunaX, tituloColunaY, dataset, PlotOrientation.VERTICAL, true, true, false);
		
		ChartPanel chartPanel = new ChartPanel(graficoDeBarras);
		
		chartPanel.setBounds(posicaoX, posicaoY, largura, altura);
		
		return chartPanel;		
		
	}

}
