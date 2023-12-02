package br.com.sistemarh.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sistemarh.client.LoginClient;
import br.com.sistemarh.dto.Credencial;
import br.com.sistemarh.view.componentes.textfield.RoundJPasswordField;
import br.com.sistemarh.view.componentes.textfield.RoundJTextField;

@Component
public class ViewLogin extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private RoundJTextField edtLogin;

	private RoundJPasswordField edtSenha;

	@Autowired
	private ViewPrincipal viewPrincipal;

	@Autowired
	private LoginClient loginClient;

	/**
	 * Create the frame.
	 */
	public ViewLogin() {
		setResizable(false);
		setTitle("Sistema RH - Entregadores");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeDeUsuario = new JLabel("Login");
		lblNomeDeUsuario.setBackground(new Color(0, 47, 109));
		lblNomeDeUsuario.setForeground(new Color(255, 255, 255));
		lblNomeDeUsuario.setBounds(367, 225, 111, 16);
		contentPane.add(lblNomeDeUsuario);

		edtLogin = new RoundJTextField(0);
		edtLogin.setHorizontalAlignment(SwingConstants.CENTER);
		edtLogin.setBounds(367, 252, 200, 25);
		contentPane.add(edtLogin);
		edtLogin.setColumns(10);

		JLabel lblSenha = new JLabel("Senha");
		lblSenha.setBackground(new Color(0, 47, 109));
		lblSenha.setForeground(new Color(255, 255, 255));
		lblSenha.setBounds(367, 300, 111, 16);
		contentPane.add(lblSenha);

		edtSenha = new RoundJPasswordField(0);
		edtSenha.setHorizontalAlignment(SwingConstants.CENTER);
		edtSenha.setBounds(367, 325, 200, 25);
		contentPane.add(edtSenha);

		JButton btnLogin = new JButton("Entrar");
		btnLogin.setBackground(new Color(0, 47, 109));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBorderPainted(false);
		btnLogin.setToolTipText("Clique aqui para entrar");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					String login = edtLogin.getText();
//					String senha = new String(edtSenha.getPassword());
					String login = "admin8585@gmail.com";
					String senha = "Tmurilo123";
					Credencial credencial = new Credencial(login, senha);
					String tokenDeAcesso = loginClient.autenticar(credencial);
					JOptionPane.showMessageDialog(contentPane, "Login realizado com sucesso", 
							"Acesso Liberado", JOptionPane.INFORMATION_MESSAGE);
					viewPrincipal.mostrarTela(tokenDeAcesso);
					dispose();
				}catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(contentPane, ex.getMessage(), 
							"Falha no Login", JOptionPane.ERROR_MESSAGE);
					limparCampos();
				}
			}
		});
		btnLogin.setBounds(415, 373, 100, 25);
		contentPane.add(btnLogin);

		JLabel imgEntregador = new JLabel("");
		imgEntregador.setBounds(430, 150, 64, 64);
		imgEntregador.setIcon(new ImageIcon(
				ViewLogin.class.getResource("/br/com/sistemarh/view/componentes/img/IconeEntregadorViewLogin.png")));
		contentPane.add(imgEntregador);

		JLabel imgUsuario = new JLabel("");
		imgUsuario.setIcon(new ImageIcon(
				ViewLogin.class.getResource("/br/com/sistemarh/view/componentes/img/IconeUsuarioViewLogin.png")));
		imgUsuario.setBounds(307, 225, 48, 48);
		contentPane.add(imgUsuario);

		JLabel imgSenha = new JLabel("");
		imgSenha.setIcon(new ImageIcon(
				ViewLogin.class.getResource("/br/com/sistemarh/view/componentes/img/IconeChaveViewLogin.png")));
		imgSenha.setBounds(307, 300, 48, 48);
		contentPane.add(imgSenha);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(new Color(0, 47, 109));
		lblNewLabel.setIcon(new ImageIcon(ViewLogin.class.getResource("/br/com/sistemarh/view/componentes/img/FundoLogin.jpeg")));
		lblNewLabel.setBounds(12, 12, 910, 537);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
	}

	private void limparCampos() {
		this.edtLogin.setText("");
		this.edtSenha.setText("");
		this.edtLogin.requestFocus();
	}
}
