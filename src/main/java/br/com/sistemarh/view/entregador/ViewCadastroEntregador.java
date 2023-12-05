package br.com.sistemarh.view.entregador;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sistemarh.client.EntregadorClient;
import br.com.sistemarh.dto.Entregador;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Component
public class ViewCadastroEntregador extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private TextField edtNome;
	
	private JFormattedTextField edtTelefone;
	
	private TextField edtCNH;
	
	private JFormattedTextField edtCPF;
	
	private TextField edtRua;
	
	private TextField edtBairro;
	
	private TextField edtCidade;
	
	private TextField edtEstado;
	
	private TextField edtEmail;
	
	private JCheckBox chckbxSeguroDeVida;
	
	private String tokenDeAcesso;
	
	private JPanel contentPane;
	
	private Entregador entregador;
	
	@Autowired
	private EntregadorClient entregadorClient;
	
	@PostConstruct
	private void inicializar() {		
		this.formatarCampoTelefone();
		this.formatarCampoCPF();
	}
	
	public void colocarEmModoDeInsercao(
			@NotBlank(message = "O token de acesso é obrigatório")
			String tokenDeAcesso) {		
		this.setVisible(true);
		this.tokenDeAcesso = tokenDeAcesso;
		this.entregadorClient.setTokenDeAcesso(tokenDeAcesso);
		this.limparCampos();
	}
	
	public void colocarEmModoDeEdicao(
			@NotBlank(message = "O token de acesso é obrigatório")
			String tokenDeAcesso,
			@NotNull(message = "O entregador não pode ser nulo")
			Entregador entregador) {
		this.setVisible(true);
		this.tokenDeAcesso = tokenDeAcesso;
		this.entregadorClient.setTokenDeAcesso(tokenDeAcesso);
		this.preencherFormularioCom(entregador);
	}
	
    private void formatarCampoTelefone() {
        try {
            MaskFormatter maskTelefone = new MaskFormatter("(##)#####-####");
            maskTelefone.install(edtTelefone);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void formatarCampoCPF() {
        try {
            MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
            maskCPF.install(edtCPF);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
	
	private void preencherFormularioCom(Entregador entregador) {
		this.entregador = entregador;
		this.edtNome.setText(entregador.getNome());
		this.edtTelefone.setText(entregador.getTelefone());
		this.edtCNH.setText(entregador.getNumeroHabilitacao());
		this.edtCPF.setText(entregador.getCpf());
		this.edtBairro.setText(entregador.getEndereco().getBairro());
		this.edtCidade.setText(entregador.getEndereco().getCidade());
		this.edtEstado.setText(entregador.getEndereco().getEstado());
		this.edtRua.setText(entregador.getEndereco().getLogradouro());
		this.chckbxSeguroDeVida.setSelected(entregador.getSeguroDeVida());
		this.edtEmail.setText(entregador.getEmail());
		
	}
	
	private void limparCampos() {
		this.entregador = new Entregador();
		this.edtNome.setText("");
		this.edtTelefone.setText("");
		this.edtCNH.setText("");
		this.edtCPF.setText("");
		this.edtBairro.setText("");
		this.edtCidade.setText("");
		this.edtEstado.setText("");
		this.edtRua.setText("");
		this.chckbxSeguroDeVida.setSelected(false);
		this.edtEmail.setText("");

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
		lblCnh.setBounds(515, 27, 25, 14);
		panel.add(lblCnh);
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf.setBackground(new Color(0, 47, 109));
		lblCpf.setBounds(515, 88, 35, 25);
		panel.add(lblCpf);
		
		edtNome = new TextField();
		edtNome.setBounds(16, 52, 350, 25);
		panel.add(edtNome);
		
		edtTelefone = new JFormattedTextField();
		edtTelefone.setBounds(16, 113, 350, 25);
		panel.add(edtTelefone);
		
		edtCNH = new TextField();
		edtCNH.setBounds(515, 47, 350, 25);
		panel.add(edtCNH);
		
		edtCPF = new JFormattedTextField();
		edtCPF.setBounds(515, 113, 350, 25);
		panel.add(edtCPF);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBackground(new Color(0, 47, 109));
		lblEmail.setBounds(16, 150, 42, 14);
		panel.add(lblEmail);
		
		edtEmail = new TextField();
		edtEmail.setBounds(16, 170, 849, 25);
		panel.add(edtEmail);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(ViewCadastroEntregador.class.getResource("/br/com/sistemarh/view/componentes/img/IconeUsuarioViewLogin.png")));
		lblNewLabel_1.setBounds(433, 211, 48, 48);
		panel.add(lblNewLabel_1);
		
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
		
		edtRua = new TextField();
		edtRua.setBounds(16, 52, 350, 25);
		panel_1.add(edtRua);
		
		edtBairro = new TextField();
		edtBairro.setBounds(16, 113, 350, 25);
		panel_1.add(edtBairro);
		
		edtCidade = new TextField();
		edtCidade.setBounds(515, 52, 350, 25);
		panel_1.add(edtCidade);
		
		edtEstado = new TextField();
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
				entregador.setNome(edtNome.getText());
				entregador.setCpf(edtCPF.getText());			
				entregador.setNumeroHabilitacao(edtCNH.getText());
				entregador.setTelefone(edtTelefone.getText());
				entregador.getEndereco().setBairro(edtBairro.getText());
				entregador.getEndereco().setCidade(edtCidade.getText());
				entregador.getEndereco().setLogradouro(edtRua.getText());
				entregador.getEndereco().setEstado(edtEstado.getText());
				entregador.setSeguroDeVida(chckbxSeguroDeVida.isSelected());
				entregador.setEmail(edtEmail.getText());
				
				try {
					if (entregador.isPersistido()) {
						Entregador entregadorAtualizado = entregadorClient.atualizar(entregador);
						preencherFormularioCom(entregadorAtualizado);
					}else {
						Entregador entregadorSalvo = entregadorClient.inserir(entregador);
						preencherFormularioCom(entregadorSalvo);
					}

					JOptionPane.showMessageDialog(contentPane, "Entregador salvo com sucesso", 
							"Sucesso na Gravação", JOptionPane.INFORMATION_MESSAGE);

				}catch (Exception ex) {
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(),
							"Erro na Gravação", JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSalvar.setToolTipText("Clique aqui para salvar");
		btnSalvar.setForeground(Color.WHITE);
		btnSalvar.setBorderPainted(false);
		btnSalvar.setBackground(new Color(0, 47, 109));
		btnSalvar.setBounds(6, 16, 94, 25);
		panel_1_1.add(btnSalvar);
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(new Color(255, 255, 255));
		panel_3.setBackground(new Color(0, 45, 109));
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(184, 207, 229)), "Acrescentar seguro?", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panel_3.setBounds(750, 495, 172, 54);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		chckbxSeguroDeVida = new JCheckBox("Seguro de vida");
		chckbxSeguroDeVida.setBackground(new Color(0, 47, 109));
		chckbxSeguroDeVida.setForeground(new Color(255, 255, 255));
		chckbxSeguroDeVida.setBounds(8, 22, 156, 24);
		panel_3.add(chckbxSeguroDeVida);
		
		setLocationRelativeTo(null);
	}
}
