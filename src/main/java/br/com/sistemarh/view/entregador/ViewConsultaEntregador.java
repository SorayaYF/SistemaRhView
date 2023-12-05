package br.com.sistemarh.view.entregador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import br.com.sistemarh.client.EntregadorClient;
import br.com.sistemarh.dto.Entregador;
import br.com.sistemarh.dto.Paginacao;
import br.com.sistemarh.enums.Status;
import br.com.sistemarh.view.componentes.table.TabelaEntregadores;
import br.com.sistemarh.view.componentes.textfield.RoundJTextField;
import jakarta.annotation.PostConstruct;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.NotBlank;

@Component
public class ViewConsultaEntregador extends JFrame implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final int PRIMEIRA_PAGINA = 0;
	
	private JPanel contentPane;
	
	private String tokenDeAcesso;	

	private RoundJTextField edtNome;
	
	private JFormattedTextField edtTelefone;
	
	private RoundJTextField edtCNH;
	
	private JFormattedTextField edtCPF;
	
	private JPanel panelInformacoes;
	
	private JPanel panelAcoes;
	
	private JTable tbEntregadores;
	
	private JScrollPane scrollPane;
	
	private JPanel pnlRegistros;
	
	private Paginacao<Entregador> paginacao;
	
	private int paginaAtual;
	
	Font fonteCabecalho = new Font("Arial", Font.BOLD, 12);
	
	Color corFonte = new Color(0, 47, 109);
	
	@Autowired
	private EntregadorClient entregadorClient;
		
	@Lazy
	@Autowired
	private ViewCadastroEntregador viewCadastro;
	
	@PostConstruct
	private void inicializar() {
		this.configurarTabela();
		this.formatarCampoCPF();
		this.formatarCampoTelefone();
	}
	
	public void mostrarTela(
			@NotBlank(message = "O token de acesso é obrigatório")
			String tokenDeAcesso) {
		this.setVisible(true);
		this.tokenDeAcesso = tokenDeAcesso;
		this.entregadorClient.setTokenDeAcesso(tokenDeAcesso);
	}
	
	private void listarEntregadores(int pagina) {
		try {
	        if (edtNome.getText().isEmpty()) {
	        	 throw new IllegalArgumentException("O campo 'Nome' não pode estar vazio");
	        } else {
				paginacao = entregadorClient.listarPor(edtNome.getText(), edtCNH.getText(), edtCPF.getText(), edtTelefone.getText(), pagina);			
				TabelaEntregadores model = new TabelaEntregadores(paginacao.getListagem());
				tbEntregadores.setModel(model);			
				configurarTabela();	        	
	        }
		}catch (ConstraintViolationException cve) {
			StringBuilder msgErro = new StringBuilder("Os seguintes erros ocorreram:\n");			
			for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
				msgErro.append("  -").append(cv.getMessage()).append("\n");
			}
			JOptionPane.showMessageDialog(contentPane, msgErro, 
					"Falha na Listagem", JOptionPane.ERROR_MESSAGE);
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(contentPane, ex.getMessage(), 
					"Falha na Listagem", JOptionPane.ERROR_MESSAGE);
		}

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
	
	private void configurarColuna(int indice, int largura) {
		this.tbEntregadores.getColumnModel().getColumn(indice).setResizable(true);
		this.tbEntregadores.getColumnModel().getColumn(indice).setPreferredWidth(largura);
	}
	
	private void configurarTabela() {
		final int COLUNA_ID = 0;
		final int COLUNA_NOME = 1;
		final int COLUNA_STATUS = 5;
		this.tbEntregadores.getTableHeader().setReorderingAllowed(false);
		this.tbEntregadores.getTableHeader().setResizingAllowed(false);;
		this.tbEntregadores.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.tbEntregadores.getTableHeader().setBackground(Color.WHITE);
	    this.tbEntregadores.getTableHeader().setForeground(corFonte);
	    this.tbEntregadores.getTableHeader().setFont(fonteCabecalho);
		this.configurarColuna(COLUNA_ID, 25);
		this.configurarColuna(COLUNA_NOME, 150);
		this.configurarColuna(COLUNA_STATUS, 5);
	}

	/**
	 * Create the frame.
	 */
	public ViewConsultaEntregador() {
		setResizable(false);
		setTitle("Gerenciar Entregadores - Listagem");
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 950, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 47, 109));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelInformacoes = new JPanel();
		panelInformacoes.setBackground(new Color(0, 47, 109));
		panelInformacoes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Informa\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelInformacoes.setBounds(10, 11, 914, 157);
		contentPane.add(panelInformacoes);
		panelInformacoes.setLayout(null);
		
		edtNome = new RoundJTextField(0);
		edtNome.setBounds(16, 52, 350, 25);
		panelInformacoes.add(edtNome);
		
		JLabel lblNewLabel = new JLabel("Nome:");
		lblNewLabel.setBounds(16, 27, 42, 14);
		panelInformacoes.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setBackground(new Color(0, 47, 109));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(16, 88, 70, 25);
		panelInformacoes.add(lblTelefone);
		lblTelefone.setForeground(Color.WHITE);
		lblTelefone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefone.setBackground(new Color(0, 47, 109));
		
		JLabel lblCnh = new JLabel("CNH:");
		lblCnh.setBounds(515, 27, 25, 14);
		panelInformacoes.add(lblCnh);
		lblCnh.setForeground(Color.WHITE);
		lblCnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCnh.setBackground(new Color(0, 47, 109));
		
		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(515, 88, 35, 25);
		panelInformacoes.add(lblCpf);
		lblCpf.setForeground(Color.WHITE);
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf.setBackground(new Color(0, 47, 109));
		
		edtTelefone = new JFormattedTextField();
		edtTelefone.setBounds(16, 115, 350, 25);
		panelInformacoes.add(edtTelefone);
		
		edtCNH = new RoundJTextField(0);
		edtCNH.setBounds(515, 52, 350, 25);
		panelInformacoes.add(edtCNH);
		
		edtCPF = new JFormattedTextField();
		edtCPF.setBounds(515, 115, 350, 25);
		panelInformacoes.add(edtCPF);
		
		panelAcoes = new JPanel();
		panelAcoes.setForeground(new Color(255, 255, 255));
		panelAcoes.setBackground(new Color(0, 47, 109));
		panelAcoes.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "A\u00E7\u00F5es", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		panelAcoes.setBounds(10, 502, 500, 48);
		contentPane.add(panelAcoes);
		panelAcoes.setLayout(null);
		
		JButton btnListar = new JButton("Listar");
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listarEntregadores(PRIMEIRA_PAGINA);
			}
		});
		btnListar.setToolTipText("Clique aqui para listar os entregadores");
		btnListar.setForeground(Color.WHITE);
		btnListar.setBorderPainted(false);
		btnListar.setBackground(new Color(0, 47, 109));
		btnListar.setBounds(10, 17, 100, 25);
		panelAcoes.add(btnListar);
		
		JButton btnNovo = new JButton("Novo");
		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				viewCadastro.colocarEmModoDeInsercao(tokenDeAcesso);
			}
		});
		btnNovo.setBounds(120, 16, 100, 25);
		panelAcoes.add(btnNovo);
		btnNovo.setToolTipText("Clique aqui para cadastrar um novo entregador");
		btnNovo.setForeground(Color.WHITE);
		btnNovo.setBorderPainted(false);
		btnNovo.setBackground(new Color(0, 47, 109));
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tbEntregadores.getSelectedRow();
				if (linhaSelecionada >= 0) {
					TabelaEntregadores model = (TabelaEntregadores)tbEntregadores.getModel();
					Entregador entregadorSelecionado = model.getPor(linhaSelecionada);
					if(entregadorSelecionado.getStatus() == Status.I) {
						JOptionPane.showMessageDialog(contentPane, "Alteração inválida. Escolha um entregador ativo.", "Erro", JOptionPane.ERROR_MESSAGE);
					} else {
						viewCadastro.colocarEmModoDeEdicao(tokenDeAcesso, entregadorSelecionado);
						dispose();
					}
				}else {
					JOptionPane.showMessageDialog(contentPane, "Selecione um entregador para edição");
				}
			}							
		});
		btnAlterar.setBounds(230, 16, 100, 25);
		panelAcoes.add(btnAlterar);
		btnAlterar.setToolTipText("Clique aqui para alterar um entregador já existente");
		btnAlterar.setForeground(Color.WHITE);
		btnAlterar.setBorderPainted(false);
		btnAlterar.setBackground(new Color(0, 47, 109));
		
		JButton btnDesativar = new JButton("Desativar/Ativar");
		btnDesativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = tbEntregadores.getSelectedRow();
				
				if (linhaSelecionada >= 0) {

					int opcao = JOptionPane.showConfirmDialog(contentPane, 
							"Deseja atualizar o status do entregador selecionado?", 
							"Atualização", JOptionPane.YES_NO_OPTION);

					boolean isConfirmacaoRealizada = opcao == 0;

					if (isConfirmacaoRealizada) {
						
						try {
							TabelaEntregadores model = (TabelaEntregadores)tbEntregadores.getModel();
							Entregador entregadorSelecionado = model.getPor(linhaSelecionada);
							Status novoStatus = entregadorSelecionado.isAtivo() ? Status.I : Status.A;
							entregadorClient.atualizarPor(entregadorSelecionado.getId(), novoStatus);
							listarEntregadores(paginaAtual);
							JOptionPane.showMessageDialog(contentPane, "O status do entregador foi atualizado com sucesso", 
									"Sucesso na mudança do Status", JOptionPane.INFORMATION_MESSAGE);
						}catch (ConstraintViolationException cve) {
							StringBuilder msgErro = new StringBuilder("Os seguintes erros ocorreram:\n");			
							for (ConstraintViolation<?> cv : cve.getConstraintViolations()) {
								msgErro.append("  -").append(cv.getMessage()).append("\n");
							}
							JOptionPane.showMessageDialog(contentPane, msgErro, 
									"Falha na Listagem", JOptionPane.ERROR_MESSAGE);
						}catch (Exception ex) {
							JOptionPane.showMessageDialog(contentPane, ex.getMessage(),
									"Erro na Atualização", JOptionPane.ERROR_MESSAGE);
						}
						
					}

				}else {
					JOptionPane.showMessageDialog(contentPane, "Selecione uma linha para desativação");
				}
				
			}				
		});
		btnDesativar.setBounds(340, 17, 150, 25);
		panelAcoes.add(btnDesativar);
		btnDesativar.setToolTipText("Clique aqui para desativar ou ativar um entregador existente");
		btnDesativar.setForeground(Color.WHITE);
		btnDesativar.setBorderPainted(false);
		btnDesativar.setBackground(new Color(0, 47, 109));
		
		
		pnlRegistros = new JPanel();
		pnlRegistros.setForeground(new Color(255, 255, 255));
		pnlRegistros.setBackground(new Color(0, 45, 109));
		pnlRegistros.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registros Encontrados", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 255, 255)));
		pnlRegistros.setBounds(10, 179, 914, 322);
		contentPane.add(pnlRegistros);
		pnlRegistros.setLayout(null);
		
		tbEntregadores = new JTable(new TabelaEntregadores());
		scrollPane = new JScrollPane(tbEntregadores);
		scrollPane.setBounds(12, 21, 892, 256);
		pnlRegistros.add(scrollPane);
		
		JButton btnPrimeiro = new JButton("<<");
		btnPrimeiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual = PRIMEIRA_PAGINA;
				listarEntregadores(paginaAtual);
			}
		});
		btnPrimeiro.setBounds(314, 288, 49, 23);
		pnlRegistros.add(btnPrimeiro);
		
		JButton btnAnterior = new JButton("<");
		btnAnterior.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paginaAtual > 0) {
					paginaAtual--;
					listarEntregadores(paginaAtual);
				}
			}
		});
		btnAnterior.setBounds(373, 288, 49, 23);
		pnlRegistros.add(btnAnterior);
		
		JButton btnProximo = new JButton(">");
		btnProximo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (paginaAtual < paginacao.getTotalDePaginas()) {
					paginaAtual++;
					listarEntregadores(paginaAtual);
				}
			}
		});
		btnProximo.setBounds(491, 288, 49, 23);
		pnlRegistros.add(btnProximo);
		
		JButton btnUltimo = new JButton(">>");
		btnUltimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual = paginacao.getTotalDePaginas();
				listarEntregadores(paginaAtual);
			}
		});
		btnUltimo.setBounds(550, 288, 49, 23);
		pnlRegistros.add(btnUltimo);
		
		configurarTabela();
		setLocationRelativeTo(null);
	}
}
