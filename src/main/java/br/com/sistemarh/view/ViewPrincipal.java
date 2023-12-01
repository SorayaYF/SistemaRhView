package br.com.sistemarh.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.sistemarh.view.entregador.ViewConsultaEntregador;
import br.com.sistemarh.view.relatorio.ViewDadosDoGrafico;
import br.com.sistemarh.view.relatorio.ViewRepasse;
import jakarta.validation.constraints.NotBlank;

@Component
public class ViewPrincipal extends JFrame implements Serializable {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private String tokenDeAcesso;

	@Autowired
	private ViewConsultaEntregador viewConsultaEntregador;

	@Autowired
	private ViewDadosDoGrafico viewDadosDoGrafico;

	@Autowired
	private ViewRepasse viewRepasse;

	/**
	 * Create the frame.
	 */
	public ViewPrincipal() {
		setResizable(false);
		setTitle("Menu Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 47, 109));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 255, 255));
		menuBar.setBounds(0, 0, 934, 28);
		menuBar.setBorderPainted(false);
		contentPane.add(menuBar);

		JMenuItem miEntregadores = new JMenuItem("Entregadores");
		miEntregadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewConsultaEntregador.mostrarTela(tokenDeAcesso);
			}
		});
		miEntregadores.setForeground(new Color(255, 255, 255));
		miEntregadores.setBackground(new Color(0, 47, 109));
		menuBar.add(miEntregadores);

		JMenuItem miFrete = new JMenuItem("Frete");
		miFrete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewDadosDoGrafico.mostrarTela(tokenDeAcesso);
			}
		});
		miFrete.setForeground(new Color(255, 255, 255));
		miFrete.setBackground(new Color(0, 47, 109));
		menuBar.add(miFrete);

		JMenuItem miPagamento = new JMenuItem("Pagamento");
		miPagamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewRepasse.mostrarTela(tokenDeAcesso);

			}
		});
		miPagamento.setBackground(new Color(0, 47, 109));
		miPagamento.setForeground(new Color(255, 255, 255));
		menuBar.add(miPagamento);

		JMenuItem miSair = new JMenuItem("Sair");
		miSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		miSair.setForeground(new Color(255, 255, 255));
		miSair.setBackground(new Color(0, 47, 109));
		menuBar.add(miSair);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ViewPrincipal.class
				.getResource("/br/com/sistemarh/view/componentes/img/IconeEntregadorViewPrincipal.png")));
		lblNewLabel.setBounds(440, 270, 51, 51);
		contentPane.add(lblNewLabel);
		setLocationRelativeTo(null);
	}

	public void mostrarTela(@NotBlank(message = "O token de acesso é obrigatório") String tokenDeAcesso) {
		this.setVisible(true);
		this.tokenDeAcesso = tokenDeAcesso;
	}
}
