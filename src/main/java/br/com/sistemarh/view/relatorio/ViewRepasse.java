package br.com.sistemarh.view.relatorio;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sistemarh.client.RepasseClient;
import br.com.sistemarh.dto.Repasse;
import jakarta.validation.constraints.NotBlank;
import net.sf.jasperreports.engine.JRException;

@Component
public class ViewRepasse extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField edtAno;
	
	private JTextField edtMes;
	
	private String tokenDeAcesso;
	
	@Autowired
	private RepasseClient repasseClient;
	
	public void mostrarTela(
			@NotBlank(message = "O token de acesso é obrigatório")
			String tokenDeAcesso) {
		this.setVisible(true);
		this.tokenDeAcesso = tokenDeAcesso;
		this.repasseClient.setTokenDeAcesso(tokenDeAcesso);
	}
	
	private void gerarRelatorio() throws IOException, JRException {
	    String anoText = edtAno.getText();
	    String mesText = edtMes.getText();

	    if (anoText.isEmpty()) {
	        JOptionPane.showMessageDialog(ViewRepasse.this, "Por favor, insira o ano.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    int ano = Integer.parseInt(anoText);

	    int mes = Integer.parseInt(mesText);

	    Repasse relatorio = repasseClient.obterRepasse(ano, mes);

	    if (relatorio == null) {
	        JOptionPane.showMessageDialog(ViewRepasse.this, "Não foi possível obter o relatório.", "Erro", JOptionPane.ERROR_MESSAGE);
	        return;
	    }

	    JFileChooser fileChooser = new JFileChooser();
	    fileChooser.setDialogTitle("Escolha o local para salvar o relatório");
	    fileChooser.setSelectedFile(new File("relatorio_" + ano + ".pdf"));

	    FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos PDF", "pdf");
	    fileChooser.setFileFilter(filter);

	    int userSelection = fileChooser.showSaveDialog(ViewRepasse.this);

	    if (userSelection == JFileChooser.APPROVE_OPTION) {
	        File fileToSave = fileChooser.getSelectedFile();

	        try (FileOutputStream fos = new FileOutputStream(fileToSave)) {
	            fos.write(Base64.getDecoder().decode(relatorio.toString()));
	            JOptionPane.showMessageDialog(ViewRepasse.this, "Relatório gerado com sucesso. O arquivo foi salvo em " + fileToSave.getAbsolutePath(), 
	                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);
	        } catch (IOException e) {
	            JOptionPane.showMessageDialog(ViewRepasse.this, "Erro ao salvar o relatório: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	        }
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
		
		edtMes = new JTextField();
		edtMes.setBounds(66, 60, 86, 20);
		panel_1.add(edtMes);
		edtMes.setColumns(10);
		
		JButton btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					gerarRelatorio();
				} catch (IOException | JRException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnGerar.setToolTipText("Clique aqui para gerar o relatório");
		btnGerar.setBounds(290, 38, 89, 23);
		panel_1.add(btnGerar);
		
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
