package br.com.sistemarh.view.relatorio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartPanel;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sistemarh.client.GraficoClient;
import br.com.sistemarh.dto.AnoDeRepasse;
import br.com.sistemarh.dto.MesDeRepasse;
import br.com.sistemarh.view.componentes.grafico.GraficoDeBarras;
import jakarta.validation.constraints.NotBlank;

@Component
public class ViewGrafico extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JTextField edtAno;
	
	private JPanel pnlGrafico;
	
	private String tokenDeAcesso;
	
	@Autowired
	private GraficoClient graficoClient;
	
	public void mostrarTela(
			@NotBlank(message = "O token de acesso é obrigatório")
			String tokenDeAcesso) {
		this.setVisible(true);
		this.tokenDeAcesso = tokenDeAcesso;
		this.graficoClient.setTokenDeAcesso(tokenDeAcesso);
	} 
	
	private void plotarGraficoPor(AnoDeRepasse anoDeRepasse) {
		
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		GraficoDeBarras grafico = new GraficoDeBarras();
		
		for (MesDeRepasse mesDeRepasse : anoDeRepasse.getMeses()) {
			dataset.addValue(mesDeRepasse.getValorRepassado(), mesDeRepasse.getNome(), "Mês");
		}
		
	    ChartPanel pnlBarras = grafico.plotarPor(dataset,
	            "Pagamentos Mensais", "", "Valor Pago(R$)", 914, 450, 0, 0);

		pnlGrafico.removeAll();
	    pnlGrafico.add(pnlBarras);
	    pnlGrafico.revalidate();
	    pnlGrafico.repaint();
		
	}
	
	private AnoDeRepasse obterDadosDoGrafico(Integer ano) {
		return graficoClient.obterAnoDeRepassePor(ano);			
	}

	public ViewGrafico() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBackground(new Color(0, 45, 109));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setForeground(Color.WHITE);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_1_1.setBackground(new Color(0, 47, 109));
		panel_1_1.setBounds(816, 11, 108, 48);
		contentPane.add(panel_1_1);
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	
				try {
				    String anoTexto = edtAno.getText();

				    if (anoTexto.isEmpty()) {
				        throw new IllegalArgumentException("O ano não pode estar vazio. Por favor, insira um ano válido.");
				    }
					Integer anoSelecionado = Integer.parseInt(edtAno.getText());
			        AnoDeRepasse anoDeRepasse = obterDadosDoGrafico(anoSelecionado);
			        plotarGraficoPor(anoDeRepasse);
				}catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage());
				}

		    }
		});
		btnGerar.setToolTipText("Clique aqui para entrar");
		btnGerar.setForeground(Color.WHITE);
		btnGerar.setBorderPainted(false);
		btnGerar.setBackground(new Color(0, 47, 109));
		btnGerar.setBounds(6, 16, 94, 25);
		panel_1_1.add(btnGerar);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(255, 255, 255));
		panel.setBackground(new Color(0, 45, 109));
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Param\u00EAtros de Pesquina", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBounds(342, 11, 249, 44);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(38, 18, 42, 14);
		panel.add(lblAno);
		lblAno.setForeground(Color.WHITE);
		lblAno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAno.setBackground(new Color(0, 47, 109));
		
		edtAno = new JTextField();
		edtAno.setBounds(98, 16, 100, 20);
		panel.add(edtAno);
		edtAno.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ViewGrafico.class.getResource("/br/com/sistemarh/view/componentes/img/IconeEntregadorViewPrincipal.png")));
		lblNewLabel_1.setBounds(29, 28, 51, 51);
		contentPane.add(lblNewLabel_1);
		
		pnlGrafico = new JPanel();
		pnlGrafico.setBounds(10, 100, 914, 450);
		contentPane.add(pnlGrafico);
		pnlGrafico.setLayout(null);
		setLocationRelativeTo(null);
	}
	
}
