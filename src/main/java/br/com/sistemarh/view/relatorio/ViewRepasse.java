package br.com.sistemarh.view.relatorio;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;

@Component
public class ViewRepasse extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField edtAno;
	
	private JComboBox<String> cbMes;
	
	private String tokenDeAcesso;
	
	public void mostrarTela(
			@NotBlank(message = "O token de acesso é obrigatório")
			String tokenDeAcesso) {
		this.setVisible(true);
		this.tokenDeAcesso = tokenDeAcesso;
	}
	
	private static String obterNomeMes(int numeroMes) {
	    String[] nomesMeses = {
	            "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
	            "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
	    };
	    return nomesMeses[numeroMes - 1];
	}
	
	private void preencherComboBoxMes() {
	    cbMes.removeAllItems();
	    for (int i = 1; i <= 12; i++) {
	        cbMes.addItem(obterNomeMes(i));
	    }
	}

	/**
	 * Create the frame.
	 */
	public ViewRepasse() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 45, 109));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBackground(new Color(0, 45, 109));
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Param\u00EAtros de Pesquina", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_1.setBounds(15, 21, 430, 99);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblAno = new JLabel("Ano:");
		lblAno.setBounds(18, 28, 38, 20);
		panel_1.add(lblAno);
		lblAno.setForeground(Color.WHITE);
		lblAno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAno.setBackground(new Color(0, 47, 109));
		
		edtAno = new JTextField();
		edtAno.setBounds(66, 28, 86, 20);
		panel_1.add(edtAno);
		edtAno.setColumns(10);
		
		JLabel lblMs = new JLabel("Mês:");
		lblMs.setBounds(18, 60, 38, 20);
		panel_1.add(lblMs);
		lblMs.setForeground(Color.WHITE);
		lblMs.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMs.setBackground(new Color(0, 47, 109));
		
		cbMes = new JComboBox<String>();
		cbMes.setBounds(66, 59, 86, 22);
		panel_1.add(cbMes);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 45, 109));
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Resultados da Pesquisa", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_2.setBounds(457, 12, 465, 537);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 26, 441, 499);
		panel_2.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ViewRepasse.class.getResource("/br/com/sistemarh/view/componentes/img/IconeEntregadorViewPrincipal.png")));
		lblNewLabel_1.setBounds(202, 284, 51, 51);
		contentPane.add(lblNewLabel_1);
		setLocationRelativeTo(null);
	}
}
