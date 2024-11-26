package views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.MaskFormatter;

import custom.component.JButtonCustom;
import models.ContaBancaria;
import models.Despesa;
import models.MovimentoFinanceiro;
import models.Receita;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import javax.swing.DefaultComboBoxModel;
import models.enums.Categoria;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JTextArea;

public class TelaInicial extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDescricao;
	public ContaBancaria conta = new ContaBancaria();
	public final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private JTextField txtValor;
	private File arquivoImportado = null;
	public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //criando formato da data
	public MaskFormatter mascaraData = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaInicial frame = new TelaInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	

	/**
	 * Create the frame.
	 */
	public TelaInicial() {	
		File arquivo = new File("arquivoContaBancaria.csv");      
	    if(arquivo.exists()){
	      conta.lerArquivo(arquivo);
	    }
				
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 449);
		contentPane = new JPanel();
		contentPane.setForeground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(190, -25, 573, 435);
		contentPane.add(tabbedPane);
		
		MaskFormatter mascaraDinheiro = null;
		//MaskFormatter mascaraData = null;

		try {                
			mascaraDinheiro = new MaskFormatter("R$ ######.00");
			mascaraData = new MaskFormatter("##/##/####");
			mascaraDinheiro.setPlaceholderCharacter('0');
			mascaraData.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(null, "Preenchimento incorreto dos campos", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		JPanel panImportar = new JPanel();
		panImportar.setBackground(new Color(212, 212, 212));
		tabbedPane.addTab("New tab", null, panImportar, "Importar arquivo");
		panImportar.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Importe o seu arquivo .csv com suas despesas e receitas");
		lblNewLabel_1.setForeground(new Color(64, 128, 128));
		lblNewLabel_1.setBackground(new Color(64, 128, 128));
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel_1.setBounds(35, 27, 510, 60);
		panImportar.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Selecionar Arquivo");
		btnNewButton.setBackground(new Color(84, 167, 167));
		btnNewButton.setBounds(190, 205, 202, 23);
		panImportar.add(btnNewButton);
		
		JLabel lblMensangemImportacaoSucesso = new JLabel("Arquivo importado com sucesso!");
		lblMensangemImportacaoSucesso.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblMensangemImportacaoSucesso.setForeground(new Color(0, 128, 0));
		lblMensangemImportacaoSucesso.setBounds(202, 250, 189, 14);
		panImportar.add(lblMensangemImportacaoSucesso);
		lblMensangemImportacaoSucesso.setVisible(false);
		
		JLabel lblMensangemImportacaoErro = new JLabel("Arquivo não importado");
		lblMensangemImportacaoErro.setForeground(new Color(255, 0, 0));
		lblMensangemImportacaoErro.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblMensangemImportacaoErro.setBounds(226, 275, 149, 14);
		panImportar.add(lblMensangemImportacaoErro);
		lblMensangemImportacaoErro.setVisible(false);
		
		JPanel panListar = new JPanel();
		panListar.setBackground(new Color(192, 192, 192));
		tabbedPane.addTab("New tab", null, panListar, null);
		panListar.setLayout(null);
		
		JLabel lblNewLabel_7 = new JLabel("Escolha uma opção de listagem");
		lblNewLabel_7.setForeground(new Color(64, 128, 128));
		lblNewLabel_7.setFont(new Font("Dialog", Font.BOLD, 22));
		lblNewLabel_7.setBounds(120, 24, 398, 29);
		panListar.add(lblNewLabel_7);
		
		JRadioButton rdbtnDespesaListar = new JRadioButton("Despesa");
		rdbtnDespesaListar.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnDespesaListar.setSelected(true);
		rdbtnDespesaListar.setBounds(38, 81, 81, 23);
		panListar.add(rdbtnDespesaListar);
		
		JRadioButton rdbtnAmbasListar = new JRadioButton("Ambas");
		rdbtnAmbasListar.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnAmbasListar.setBounds(223, 81, 81, 23);
		panListar.add(rdbtnAmbasListar);
		
		JRadioButton rdbtnReceitaListar = new JRadioButton("Receita");
		rdbtnReceitaListar.setFont(new Font("Dialog", Font.PLAIN, 13));
		rdbtnReceitaListar.setBounds(130, 81, 81, 23);
		panListar.add(rdbtnReceitaListar);
		
		ButtonGroup groupListar = new ButtonGroup();
		groupListar.add(rdbtnDespesaListar);
		groupListar.add(rdbtnReceitaListar);
		groupListar.add(rdbtnAmbasListar);
		
		JButton btnListarMovimentos = new JButton("Listar");	
		btnListarMovimentos.setBackground(new Color(84, 167, 167));
		btnListarMovimentos.setBounds(329, 81, 189, 23);
		panListar.add(btnListarMovimentos);
		
		JFormattedTextField txtfDataFiltroSaldo = new JFormattedTextField(mascaraData);
		txtfDataFiltroSaldo.setFont(new Font("Dialog", Font.PLAIN, 13));
		txtfDataFiltroSaldo.setBounds(212, 128, 92, 20);
		panListar.add(txtfDataFiltroSaldo);
		
		JLabel lblNewLabel_8 = new JLabel("Consultar saldo até a data:");
		lblNewLabel_8.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(38, 131, 173, 14);
		panListar.add(lblNewLabel_8);
		
		JButton btnConsultarSaldo = new JButton("Consultar Saldo");
		btnConsultarSaldo.setBackground(new Color(84, 167, 167));
		btnConsultarSaldo.setBounds(329, 127, 189, 23);
		panListar.add(btnConsultarSaldo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 161, 548, 235);
		panListar.add(scrollPane);
		
		JTextArea txtAreaListagem = new JTextArea();
		txtAreaListagem.setEditable(false);
		txtAreaListagem.setLineWrap(true);
		scrollPane.setViewportView(txtAreaListagem);
				
		JPanel panCadastrar = new JPanel();
		panCadastrar.setBackground(new Color(192, 192, 192));
		tabbedPane.addTab("New tab", null, panCadastrar, null);
		panCadastrar.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Descrição:");
		lblNewLabel_2.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(25, 150, 120, 45);
		panCadastrar.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Data:");
		lblNewLabel_3.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(29, 206, 65, 32);
		panCadastrar.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Categoria:");
		lblNewLabel_4.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(29, 263, 96, 20);
		panCadastrar.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Valor:");
		lblNewLabel_5.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(288, 210, 86, 25);
		panCadastrar.add(lblNewLabel_5);
		
		txtDescricao = new JTextField();
		txtDescricao.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		txtDescricao.setBounds(159, 157, 333, 32);
		panCadastrar.add(txtDescricao);
		txtDescricao.setColumns(10);
		
		JComboBox cmbCategoria = new JComboBox();
		cmbCategoria.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		cmbCategoria.setModel(new DefaultComboBoxModel(new String[] {"ALIMENTACAO", "EDUCACAO", "ENTRETENIMENTO", 
				"FERIAS", "RESIDENCIA", "SAUDE", "TRANSPORTE", "OUTROS"}));
		cmbCategoria.setBounds(159, 262, 333, 22);
		panCadastrar.add(cmbCategoria);
		
		JFormattedTextField txtfData = new JFormattedTextField(mascaraData);
		txtfData.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		txtfData.setBounds(158, 209, 120, 27);
		panCadastrar.add(txtfData);
		
		JLabel lblNewLabel_6 = new JLabel("Cadastre uma despesa ou receita");
		lblNewLabel_6.setForeground(new Color(64, 128, 128));
		lblNewLabel_6.setBackground(new Color(64, 128, 128));
		lblNewLabel_6.setFont(new Font("Sitka Small", Font.BOLD, 22));
		lblNewLabel_6.setBounds(106, 22, 404, 34);
		panCadastrar.add(lblNewLabel_6);
		
		JRadioButton rdBtnDespesa = new JRadioButton("Despesa");				
		rdBtnDespesa.setSelected(true);
		rdBtnDespesa.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		rdBtnDespesa.setBounds(158, 105, 130, 32);
		panCadastrar.add(rdBtnDespesa);
		
		JRadioButton rdBtnReceita = new JRadioButton("Receita");
		rdBtnReceita.setFont(new Font("Sitka Small", Font.PLAIN, 18));
		rdBtnReceita.setBounds(353, 105, 139, 32);
		panCadastrar.add(rdBtnReceita);
		
		ButtonGroup groupCadastrar = new ButtonGroup();
		groupCadastrar.add(rdBtnDespesa);
		groupCadastrar.add(rdBtnReceita);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tipo:");
		lblNewLabel_2_1.setFont(new Font("Dialog", Font.PLAIN, 18));
		lblNewLabel_2_1.setBounds(25, 99, 120, 45);
		panCadastrar.add(lblNewLabel_2_1);
		
		JButton btnSalvarMovimentoFinanceiro = new JButton("Salvar");			
		btnSalvarMovimentoFinanceiro.setBackground(new Color(128, 255, 0));
		btnSalvarMovimentoFinanceiro.setBounds(119, 330, 120, 23);
		panCadastrar.add(btnSalvarMovimentoFinanceiro);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setForeground(new Color(255, 255, 255));
		btnCancelar.setBackground(new Color(255, 0, 0));
		btnCancelar.setBounds(319, 330, 130, 23);
		panCadastrar.add(btnCancelar);
		
		txtValor = new JTextField();
		txtValor.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtValor.setBounds(353, 211, 139, 27);
		panCadastrar.add(txtValor);
		txtValor.setColumns(10);
		
		JPanel panMenu = new JPanel();
		panMenu.setBackground(new Color(68, 149, 157));
		panMenu.setForeground(new Color(255, 128, 64));
		panMenu.setBounds(0, 0, 190, 410);
		contentPane.add(panMenu);
		panMenu.setLayout(null);
		
		JButton btnCadastrar = new JButton();
		btnCadastrar.setText("Cadastrar");
		btnCadastrar.setBounds(24, 175, 141, 29);
		panMenu.add(btnCadastrar);
		
		JButton btnImportar = new JButton("Importar Arquivo");
		btnImportar.setForeground(new Color(0, 0, 0));
		btnImportar.setBackground(new Color(255, 255, 255));
		btnImportar.setBounds(24, 121, 141, 29);
		panMenu.add(btnImportar);
		
		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(24, 228, 141, 29);
		panMenu.add(btnListar);
		
 

		btnImportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
			}
		});
		
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(2);
			}
		});
		
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(1);
			}
		});
		
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtDescricao.setText("");
				txtfData.setText("");
				txtValor.setText("");
			}
		});
		
		rdBtnDespesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbCategoria.setModel(new DefaultComboBoxModel(new String[] {"ALIMENTACAO", "EDUCACAO", "ENTRETENIMENTO", 
																			"FERIAS", "RESIDENCIA", "SAUDE", "TRANSPORTE", "OUTROS"}));
			}
		});
		
		rdBtnReceita.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbCategoria.setModel(new DefaultComboBoxModel(new String[] {"SALARIO", "FERIAS", "DECIMO_TERCEIRO", "OUTROS"}));
			}
		});
		
		btnSalvarMovimentoFinanceiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {								
		        try {
		        	String dateString = txtfData.getText(); 
			        LocalDate date = LocalDate.parse(dateString, formatter); // Converter a string para LocalDate usando o formatter
			        
		        	if(rdBtnDespesa.isSelected()) {
						MovimentoFinanceiro despesa = new Despesa(txtDescricao.getText(), Categoria.valueOf(cmbCategoria.getSelectedItem().toString()), date, 
								Double.parseDouble(txtValor.getText().trim()));
				        conta.incluir(despesa);
					}else {
						MovimentoFinanceiro receita = new Receita(txtDescricao.getText(), Categoria.valueOf(cmbCategoria.getSelectedItem().toString()), date, 
								Double.parseDouble(txtValor.getText()));
				        conta.incluir(receita);
					}
		        	
		        	if(arquivoImportado == null) {
		        		conta.criarArquivo();
		        	}else {
		        		conta.salvarArquivo(arquivoImportado);
		        	}
		        	
		        	txtDescricao.setText("");
					txtfData.setText("");
					txtValor.setText("");
					
					JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
		        }catch(NumberFormatException ex) {
		        	JOptionPane.showMessageDialog(null, "Campo Valor preenchido incorretamente. É aceito apenas números, e se conter casas decimais, utilizar ponto(.) para separar.", "Erro", JOptionPane.ERROR_MESSAGE);
		        }		        
		        catch(Exception ex){
		        	JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

		        }				
			}
		});
		
		btnListarMovimentos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAreaListagem.setText("");
				
				ArrayList<MovimentoFinanceiro> listaMovimentos = conta.listar();			
				ArrayList<MovimentoFinanceiro> listaAux = new ArrayList<>();	
				String texto = "";
				
				if(rdbtnAmbasListar.isSelected()) {
					listaAux.addAll(listaMovimentos);
				}
				
				//separando por tipo
				for (MovimentoFinanceiro movimentoFinanceiro : listaMovimentos) {
					if(movimentoFinanceiro instanceof Despesa && rdbtnDespesaListar.isSelected()) {
						listaAux.add(movimentoFinanceiro);
					}
					
					else if(movimentoFinanceiro instanceof Receita && rdbtnReceitaListar.isSelected()) {
						listaAux.add(movimentoFinanceiro);
					}
				}
				
				if(listaAux.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Não há registros cadastrados.", "Sem registros", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				
				for (MovimentoFinanceiro movi : listaAux) {
					texto += movi.toString() + "\n";
					
				}
				
				txtAreaListagem.setText(texto);
							
			}
		});
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				FileNameExtensionFilter fileFilter = new FileNameExtensionFilter("selecione o arquivo csv", "csv");
		        JFileChooser chooser = new JFileChooser();
		        chooser.setFileFilter(fileFilter);
		        
		        int retorno = chooser.showOpenDialog(chooser);
		        File arquivo = chooser.getSelectedFile();
		        
		        if (retorno == JFileChooser.APPROVE_OPTION && arquivo.getName().toLowerCase().endsWith(".csv")) {
		            conta.lerArquivo(arquivo);
		            conta.salvarArquivo(arquivo);
		            
		            arquivoImportado = arquivo;
		            
		            lblMensangemImportacaoSucesso.setVisible(true);
		            lblMensangemImportacaoErro.setVisible(false);
		            return;
		        }
				
		        lblMensangemImportacaoSucesso.setVisible(false);
		        lblMensangemImportacaoErro.setVisible(true);
			}
		});
		
		btnConsultarSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtAreaListagem.setText("");
				double saldo = 0d;
				
				String dateString = txtfDataFiltroSaldo.getText(); 
				
				if(dateString.equals("__/__/____")) {
					saldo = conta.consultarSaldo(LocalDate.now());
					//LocalDate date = LocalDate.parse(LocalDate.now().toString(), formatter);
					//txtfDataFiltroSaldo.setText(date.toString());
				}else {
					LocalDate date = LocalDate.parse(dateString, formatter);
			        saldo = conta.consultarSaldo(date);
					
				}
    
				txtAreaListagem.setText("Saldo até a data informada: " + saldo);
			}
		});
		
		
		JPanel panel = new JPanel();
		panel.setBounds(22, 42, 414, 213);
		//listar.getContentPane().add(panel);
		panel.setLayout(null);
		
	}
}
