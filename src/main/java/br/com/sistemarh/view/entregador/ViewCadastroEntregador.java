package br.com.sistemarh.view.entregador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sistemarh.client.EntregadorClient;
import br.com.sistemarh.dto.Entregador;
import br.com.sistemarh.view.componentes.textfield.RoundJTextField;
import jakarta.validation.constraints.NotBlank;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@Component
public class ViewCadastroEntregador extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private RoundJTextField edtNome;
	
	private RoundJTextField edtTelefone;
	
	private RoundJTextField edtCNH;
	
	private RoundJTextField edtCPF;
	
	private RoundJTextField edtRua;
	
	private RoundJTextField edtBairro;
	
	private RoundJTextField edtCidade;
	
	private RoundJTextField edtEstado;
	
	private String tokenDeAcesso;
	
	private JPanel contentPane;
	
	private Entregador entregador;
	
	@Autowired
	private EntregadorClient entregadorClient;
	
	
	public void colocarEmModoDeInsercao(
			@NotBlank(message = "O token de acesso é obrigatório")
			String tokenDeAcesso) {
		this.setVisible(true);
		this.tokenDeAcesso = tokenDeAcesso;
		this.entregadorClient.setTokenDeAcesso(tokenDeAcesso);
	}

	/**
	 * Create the frame.
	 */
	public ViewCadastroEntregador() {
		setBackground(new Color(255, 255, 255));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 45, 109));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informa\u00E7\u00F5es Pessoais", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel.setBackground(new Color(0, 47, 109));
		panel.setBounds(12, 12, 914, 289);
		contentPane.add(panel);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBackground(new Color(0, 47, 109));
		lblNewLabel.setBounds(16, 27, 42, 14);
		panel.add(lblNewLabel);
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefone.setBackground(new Color(0, 47, 109));
		lblTelefone.setBounds(16, 88, 70, 25);
		panel.add(lblTelefone);
		
		JLabel lblCnh = new JLabel("CNH:");
		lblCnh.setForeground(Color.WHITE);
		lblCnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCnh.setBackground(new Color(0, 47, 109));
		lblCnh.setBounds(16, 152, 25, 14);
		panel.add(lblCnh);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf.setBackground(new Color(0, 47, 109));
		lblCpf.setBounds(16, 215, 35, 25);
		panel.add(lblCpf);
		
		edtNome = new RoundJTextField(0);
		edtNome.setBounds(16, 52, 350, 25);
		panel.add(edtNome);
		
		edtTelefone = new RoundJTextField(0);
		edtTelefone.setBounds(16, 113, 350, 25);
		panel.add(edtTelefone);
		
		edtCNH = new RoundJTextField(0);
		edtCNH.setBounds(16, 177, 350, 25);
		panel.add(edtCNH);
		
		edtCPF = new RoundJTextField(0);
		edtCPF.setBounds(16, 240, 350, 25);
		panel.add(edtCPF);
		
		JPanel panel_2 = new JPanel();
		panel_2.setForeground(new Color(0, 45, 109));
		panel_2.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Foto", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 45, 109)));
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(515, 25, 350, 240);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\soraya_farag\\Downloads\\icons8-usuário-homem-com-círculo-64 (1).png"));
		lblNewLabel_1.setBounds(133, 57, 64, 64);
		panel_2.add(lblNewLabel_1);
		
		JButton btnAdicionarFoto = new JButton("Adicionar foto");
		btnAdicionarFoto.setIcon(new ImageIcon("C:\\Users\\soraya_farag\\Downloads\\icons8-arquivo-16.png"));
		btnAdicionarFoto.setBackground(new Color(0, 45, 109));
		btnAdicionarFoto.setForeground(new Color(255, 255, 255));
		btnAdicionarFoto.setBounds(97, 152, 145, 26);
		panel_2.add(btnAdicionarFoto);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Endere\u00E7o", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_1.setBackground(new Color(0, 47, 109));
		panel_1.setBounds(12, 313, 914, 170);
		contentPane.add(panel_1);
		
		JLabel lblRua = new JLabel("Rua:");
		lblRua.setForeground(Color.WHITE);
		lblRua.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRua.setBackground(new Color(0, 47, 109));
		lblRua.setBounds(16, 27, 42, 14);
		panel_1.add(lblRua);
		
		JLabel lblBairro = new JLabel("Bairro:");
		lblBairro.setForeground(Color.WHITE);
		lblBairro.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblBairro.setBackground(new Color(0, 47, 109));
		lblBairro.setBounds(16, 88, 70, 25);
		panel_1.add(lblBairro);
		
		JLabel lblCidade = new JLabel("Cidade:");
		lblCidade.setForeground(Color.WHITE);
		lblCidade.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCidade.setBackground(new Color(0, 47, 109));
		lblCidade.setBounds(515, 27, 93, 14);
		panel_1.add(lblCidade);
		
		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstado.setBackground(new Color(0, 47, 109));
		lblEstado.setBounds(515, 88, 76, 25);
		panel_1.add(lblEstado);
		
		edtRua = new RoundJTextField(0);
		edtRua.setBounds(16, 52, 350, 25);
		panel_1.add(edtRua);
		
		edtBairro = new RoundJTextField(0);
		edtBairro.setBounds(16, 113, 350, 25);
		panel_1.add(edtBairro);
		
		edtCidade = new RoundJTextField(0);
		edtCidade.setBounds(515, 52, 350, 25);
		panel_1.add(edtCidade);
		
		edtEstado = new RoundJTextField(0);
		edtEstado.setBounds(515, 113, 350, 25);
		panel_1.add(edtEstado);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setForeground(Color.WHITE);
		panel_1_1.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_1_1.setBackground(new Color(0, 47, 109));
		panel_1_1.setBounds(12, 501, 108, 48);
		contentPane.add(panel_1_1);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				entregador = new Entregador();
				entregador.setNome(edtNome.getText());
				entregador.setCpf(edtCPF.getText());			
				entregador.setNumeroHabilitacao(edtCNH.getText());
				entregador.setTelefone(edtTelefone.getText());
				entregador.getEndereco().setBairro(edtBairro.getText());
				entregador.getEndereco().setCidade(edtCidade.getText());
				entregador.getEndereco().setLogradouro(edtRua.getText());
				entregador.getEndereco().setEstado(edtEstado.getText());
				
				try {
					if (entregador.isPersistido()) {
						Entregador entregadorAtualizado = entregadorClient.atualizar(entregador);
					}else {
						Entregador entregadorSalvo = entregadorClient.inserir(entregador);
					}

					JOptionPane.showMessageDialog(contentPane, "Entregador salvo com sucesso", 
							"Sucesso na Gravação", JOptionPane.INFORMATION_MESSAGE);

				}catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(),
							"Erro na Gravação", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSalvar.setToolTipText("Clique aqui para entrar");
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setBorderPainted(false);
		btnSalvar.setBackground(new Color(0, 47, 109));
		btnSalvar.setBounds(6, 16, 94, 25);
		panel_1_1.add(btnSalvar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Seguro de vida", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(51, 51, 51)));
		panel_3.setBounds(782, 495, 140, 48);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		
		setLocationRelativeTo(null);
	}
}
