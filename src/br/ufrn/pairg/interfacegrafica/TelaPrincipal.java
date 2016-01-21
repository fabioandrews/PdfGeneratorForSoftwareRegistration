package br.ufrn.pairg.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;

import br.ufrn.pairg.pdfgenerator.CriaELeArquivoCamposDeAplicacaoETiposDeProgramaDat;
import br.ufrn.pairg.pdfgenerator.CriaeLeArquivoConfiguracoesdat;
import br.ufrn.pairg.pdfgenerator.Main;
import br.ufrn.pairg.pdfgenerator.SingletonPDFGeradoComSucessoDeveSerMostrado;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.SwingConstants;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;








import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;

import br.ufrn.pairg.pdfgenerator.CriaeLeArquivoConfiguracoesdat;
import br.ufrn.pairg.pdfgenerator.Main;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.SwingConstants;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;










import java.io.File;

import javax.swing.*;
import javax.swing.filechooser.*;

public class TelaPrincipal extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField campo_preencher_diretorio;
	private JTextField campo_preencher_autor;
	private JTextField campo_preencher_versao;
	private JTextField campo_preencher_output;
	private final Action acaoBotaoEspecificarPastasArquivosProjeto = new AcaoEspecificarPastasEArquivosProjeto();
	private final Action acaoSelecionarPastaProjeto = new AcaoSelecionarProjeto();
	private static JFileChooser escolhedorPastaProjeto;
	private static JFileChooser escolhedorOutputProjeto;
	private LinkedList<String> extensoes;
	//variáveis referentes a selecionar 
	private JTextField textFieldAdicionarExtensoes;
	private JList<String> listaExtensoes;
	private JButton buttonAdicionarExtensoes;
	private JButton buttonRemoverExtensoes;
	private DefaultListModel<String> listModel;
	private Label label;
	private final Action acaoGerarPdf = new AcaoGerarPdf();
	private final Action acaoEspecificarOutput = new AcaoEspecificarOutput();
	private JTextField campo_nome_projeto;
	
	private int tamanhoMaximoDaBarraDeProgresso; //necessario  para mostrar ao usuario o progresso na hora de carregar as pastas e subpastas do projeto
	private JLabel textoBarraDeProgresso;
	
	
	private JTextField textFieldLinguagens;
	private JTextField textfieldModulo;
	
	private String linguagens;
	
	private JList<String> listaTiposDeAplicacao;
	private JList<String> listaTiposDePrograma;
	private DefaultListModel<String> listModelListaTiposDeAplicacao;
	private DefaultListModel<String> listModelListaTiposDePrograma;
	private JButton buttonAdicionarTipoDeAplicacao;
	private JButton buttonRemoverTipoDeAplicacao;
	private JButton buttonAdicionarTipoDePrograma;
	private JButton buttonRemoverTipoDePrograma;
	private LinkedList<String> tiposDeAplicacao;
	private LinkedList<String> tiposDePrograma;
	private JComboBox comboBoxAdicionarTipoDeAplicacao;
	private JComboBox comboBoxAdicionarTipoDePrograma;
	
	private JDialog dialogParaBarraDeProgresso;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().limparListaSelecionados();
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(true);
					SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaPrincipal() 
	{
		this.setResizable(false);
		setTitle("code2inpi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 597, 637);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{12, 92, 42, 42};
		gbl_contentPane.rowHeights = new int[]{19, 19, 19, 19, 19, 19, 19, 19, 0, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.1, 0.1, 0.6};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		
		
		
		
		
		JPanel painel_opcoes_projeto = new JPanel();
		TitledBorder tituloPainelProjeto;
		tituloPainelProjeto = BorderFactory.createTitledBorder("Programa de Computador");
		painel_opcoes_projeto.setBorder(tituloPainelProjeto);
		GridBagConstraints gbc_painel_opcoes_projeto = new GridBagConstraints();
		gbc_painel_opcoes_projeto.insets = new Insets(0, 0, 5, 5);
		gbc_painel_opcoes_projeto.fill = GridBagConstraints.BOTH;
		gbc_painel_opcoes_projeto.gridx = 0;
		gbc_painel_opcoes_projeto.gridy = 0;
		gbc_painel_opcoes_projeto.gridwidth = 4;
		gbc_painel_opcoes_projeto.gridheight = 5;
		
		contentPane.add(painel_opcoes_projeto, gbc_painel_opcoes_projeto);
		GridBagLayout gbl_painel_opcoes_projeto = new GridBagLayout();
		gbl_painel_opcoes_projeto.columnWidths = new int[]{0, 0, 0};
		gbl_painel_opcoes_projeto.rowHeights = new int[]{0, 0, 0, 0};
		gbl_painel_opcoes_projeto.columnWeights = new double[]{Double.MIN_VALUE, 1.0};
		gbl_painel_opcoes_projeto.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		painel_opcoes_projeto.setLayout(gbl_painel_opcoes_projeto);
		
		JLabel lblTtulo = DefaultComponentFactory.getInstance().createLabel("T\u00EDtulo:");
		GridBagConstraints gbc_lblTtulo = new GridBagConstraints();
		gbc_lblTtulo.anchor = GridBagConstraints.EAST;
		gbc_lblTtulo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTtulo.gridx = 0;
		gbc_lblTtulo.gridy = 0;
		painel_opcoes_projeto.add(lblTtulo, gbc_lblTtulo);
		
		campo_nome_projeto = new JTextField();
		GridBagConstraints gbc_campo_preencher_titulo_projeto = new GridBagConstraints();
		gbc_campo_preencher_titulo_projeto.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_titulo_projeto.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_titulo_projeto.gridx = 1;
		gbc_campo_preencher_titulo_projeto.gridy = 0;
		painel_opcoes_projeto.add(campo_nome_projeto, gbc_campo_preencher_titulo_projeto);
		campo_nome_projeto.setColumns(10);
		
		
		
		JLabel lblAutor = DefaultComponentFactory.getInstance().createLabel("Autor(es):");
		GridBagConstraints gbc_lblAutor = new GridBagConstraints();
		gbc_lblAutor.anchor = GridBagConstraints.EAST;
		gbc_lblAutor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAutor.gridx = 0;
		gbc_lblAutor.gridy = 1;
		painel_opcoes_projeto.add(lblAutor, gbc_lblAutor);
		
		campo_preencher_autor = new JTextField();
		GridBagConstraints gbc_campo_preencher_autor_projeto = new GridBagConstraints();
		gbc_campo_preencher_autor_projeto.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_autor_projeto.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_autor_projeto.gridx = 1;
		gbc_campo_preencher_autor_projeto.gridy = 1;
		painel_opcoes_projeto.add(campo_preencher_autor, gbc_campo_preencher_autor_projeto);
		campo_preencher_autor.setColumns(10);
		
		JLabel lblVerso = DefaultComponentFactory.getInstance().createLabel("Vers\u00E3o:");
		GridBagConstraints gbc_lblVerso = new GridBagConstraints();
		gbc_lblVerso.insets = new Insets(0, 0, 0, 5);
		gbc_lblVerso.anchor = GridBagConstraints.EAST;
		gbc_lblVerso.gridx = 0;
		gbc_lblVerso.gridy = 2;
		painel_opcoes_projeto.add(lblVerso, gbc_lblVerso);
		
		campo_preencher_versao = new JTextField();
		GridBagConstraints gbc_campo_preencher_versao_projeto = new GridBagConstraints();
		gbc_campo_preencher_versao_projeto.insets = new Insets(0, 0, 0, 5);
		gbc_campo_preencher_versao_projeto.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_versao_projeto.gridx = 1;
		gbc_campo_preencher_versao_projeto.gridy = 2;
		painel_opcoes_projeto.add(campo_preencher_versao, gbc_campo_preencher_versao_projeto);
		campo_preencher_versao.setColumns(10);
		
		
		TitledBorder tituloPainelArquivos;
		tituloPainelArquivos = BorderFactory.createTitledBorder("Arquivos");
		
		
		
	    extensoes= new LinkedList<String>();

	    
		
	    //PARTE REFERENTE A LINGUAGENS
	    JLabel lblLinguagens = DefaultComponentFactory.getInstance().createLabel("Linguagem:");
		GridBagConstraints gbc_lblLinguagens = new GridBagConstraints();
		gbc_lblLinguagens.insets = new Insets(5, 0, 0, 5);
		gbc_lblLinguagens.anchor = GridBagConstraints.EAST;
		gbc_lblLinguagens.gridx = 0;
		gbc_lblLinguagens.gridy = 3;
		painel_opcoes_projeto.add(lblLinguagens, gbc_lblLinguagens);
		
		textFieldLinguagens = new JTextField();
		GridBagConstraints gbc_textFieldLinguagens = new GridBagConstraints();
		gbc_textFieldLinguagens.insets = new Insets(5, 0, 0, 5);
		gbc_textFieldLinguagens.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldLinguagens.gridx = 1;
		gbc_textFieldLinguagens.gridy = 3;
		painel_opcoes_projeto.add(textFieldLinguagens, gbc_textFieldLinguagens);
		textFieldLinguagens.setColumns(10);
		
		 //PARTE REFERENTE A MÓDULO
	    JLabel lblModulo = DefaultComponentFactory.getInstance().createLabel("Módulo:");
		GridBagConstraints gbc_lblModulo = new GridBagConstraints();
		gbc_lblModulo.insets = new Insets(5, 0, 0, 5);
		gbc_lblModulo.anchor = GridBagConstraints.EAST;
		gbc_lblModulo.gridx = 0;
		gbc_lblModulo.gridy = 4;
		painel_opcoes_projeto.add(lblModulo, gbc_lblModulo);
		
		textfieldModulo = new JTextField();
		GridBagConstraints gbc_textFieldModulo = new GridBagConstraints();
		gbc_textFieldModulo.insets = new Insets(5, 0, 0, 5);
		gbc_textFieldModulo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldModulo.gridx = 1;
		gbc_textFieldModulo.gridy = 4;
		painel_opcoes_projeto.add(textfieldModulo, gbc_textFieldModulo);
		textfieldModulo.setColumns(10);
	    
	    //PARTE REFERENTE ao tipo de aplicacao e programa
		JPanel panel_tipo_de_aplicacao_e_programa =  new JPanel();
		TitledBorder titulopanel_dados_do_inpi;
		titulopanel_dados_do_inpi = BorderFactory.createTitledBorder("Dados do INPI");
		panel_tipo_de_aplicacao_e_programa.setBorder(titulopanel_dados_do_inpi);
		GridBagConstraints gbc_panel_tipo_de_aplicacao_e_programa = new GridBagConstraints();
		gbc_panel_tipo_de_aplicacao_e_programa.fill = GridBagConstraints.BOTH;
		gbc_panel_tipo_de_aplicacao_e_programa.gridx = 0;
		gbc_panel_tipo_de_aplicacao_e_programa.gridy = 5;
		gbc_panel_tipo_de_aplicacao_e_programa.insets = new Insets(0, 0, 5, 0);
		gbc_panel_tipo_de_aplicacao_e_programa.anchor = GridBagConstraints.NORTH;
		gbc_panel_tipo_de_aplicacao_e_programa.gridheight = 7;
		gbc_panel_tipo_de_aplicacao_e_programa.gridwidth = 4;
		contentPane.add(panel_tipo_de_aplicacao_e_programa, gbc_panel_tipo_de_aplicacao_e_programa);
		GridBagLayout gbl_panel_tipo_de_aplicacao_e_programa = new GridBagLayout();
		gbl_panel_tipo_de_aplicacao_e_programa.columnWidths = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_aplicacao_e_programa.rowHeights = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_aplicacao_e_programa.columnWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		gbl_panel_tipo_de_aplicacao_e_programa.rowWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		panel_tipo_de_aplicacao_e_programa.setLayout(gbl_panel_tipo_de_aplicacao_e_programa);
		
		JPanel panel_tipo_de_aplicacao =  new JPanel();
		
		
		GridBagConstraints gbc_panel_tipo_de_aplicacao = new GridBagConstraints();
		gbc_panel_tipo_de_aplicacao.fill = GridBagConstraints.BOTH;
		gbc_panel_tipo_de_aplicacao.gridx = 0;
		gbc_panel_tipo_de_aplicacao.gridy = 0;
		gbc_panel_tipo_de_aplicacao.insets = new Insets(0, 0, 5, 0);
		gbc_panel_tipo_de_aplicacao.anchor = GridBagConstraints.NORTH;
		gbc_panel_tipo_de_aplicacao.gridheight = 7;
		gbc_panel_tipo_de_aplicacao.gridwidth = 2;
		
		GridBagLayout gbl_panel_tipo_de_aplicacao = new GridBagLayout();
		gbl_panel_tipo_de_aplicacao.columnWidths = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_aplicacao.rowHeights = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_aplicacao.columnWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		gbl_panel_tipo_de_aplicacao.rowWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		panel_tipo_de_aplicacao.setLayout(gbl_panel_tipo_de_aplicacao);
		
		panel_tipo_de_aplicacao_e_programa.add(panel_tipo_de_aplicacao,gbc_panel_tipo_de_aplicacao);
		
		JPanel panel_tipo_de_programa =  new JPanel();
		
		GridBagConstraints gbc_panel_tipo_de_programa = new GridBagConstraints();
		gbc_panel_tipo_de_programa.fill = GridBagConstraints.BOTH;
		gbc_panel_tipo_de_programa.gridx = 2;
		gbc_panel_tipo_de_programa.gridy = 0;
		gbc_panel_tipo_de_programa.insets = new Insets(0, 0, 5, 0);
		gbc_panel_tipo_de_programa.anchor = GridBagConstraints.NORTH;
		gbc_panel_tipo_de_programa.gridheight = 7;
		gbc_panel_tipo_de_programa.gridwidth = 2;
		
		GridBagLayout gbl_panel_tipo_de_programa = new GridBagLayout();
		gbl_panel_tipo_de_programa.columnWidths = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_programa.rowHeights = new int[]{20, 20, 20, 20};
		gbl_panel_tipo_de_programa.columnWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		gbl_panel_tipo_de_programa.rowWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		panel_tipo_de_programa.setLayout(gbl_panel_tipo_de_programa);
		
		panel_tipo_de_aplicacao_e_programa.add(panel_tipo_de_programa,gbc_panel_tipo_de_programa);
		
		
		this.listModelListaTiposDeAplicacao = new DefaultListModel<String>();
		this.listModelListaTiposDePrograma = new DefaultListModel<String>();
		this.listaTiposDeAplicacao = new JList<String>(listModelListaTiposDeAplicacao);
		this.listaTiposDePrograma = new JList<String>(listModelListaTiposDePrograma);
		
		GridBagConstraints gbc_label_campos_de_aplicacao = new GridBagConstraints();
		gbc_label_campos_de_aplicacao.gridheight = 1;
		gbc_label_campos_de_aplicacao.gridwidth = 2;
		gbc_label_campos_de_aplicacao.gridx = 0;
		gbc_label_campos_de_aplicacao.gridy = 0;
		JLabel labelCamposDeAplicacao = new JLabel("Campos de Aplicação");
		panel_tipo_de_aplicacao.add(labelCamposDeAplicacao);
		
		GridBagConstraints gbc_listaTiposDeAplicacao = new GridBagConstraints();
		gbc_listaTiposDeAplicacao.gridheight = 5;
		gbc_listaTiposDeAplicacao.gridwidth = 2;
		gbc_listaTiposDeAplicacao.insets = new Insets(0, 0, 15, 35);
		gbc_listaTiposDeAplicacao.gridx = 0;
		gbc_listaTiposDeAplicacao.gridy = 1;
		listaTiposDeAplicacao.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaTiposDeAplicacao.setLayoutOrientation(JList.VERTICAL);
		listaTiposDeAplicacao.setVisibleRowCount(-1);
		JScrollPane scrollPaneListaTiposDeAplicacao = new JScrollPane(listaTiposDeAplicacao);
		scrollPaneListaTiposDeAplicacao.setPreferredSize(new Dimension(120, 100));
		scrollPaneListaTiposDeAplicacao.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneListaTiposDeAplicacao.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ListSelectionModel listSelectionModelListaTiposDeAplicacao = listaTiposDeAplicacao.getSelectionModel();
		
		panel_tipo_de_aplicacao.add(scrollPaneListaTiposDeAplicacao, gbc_listaTiposDeAplicacao);
	    
	    
		buttonRemoverTipoDeAplicacao = new JButton("-");
		GridBagConstraints gbc_buttonRemoverTipoDeAplicacao = new GridBagConstraints();
		gbc_buttonRemoverTipoDeAplicacao.insets = new Insets(15, 0, 0, -10);
		gbc_buttonRemoverTipoDeAplicacao.gridx = 1;
		gbc_buttonRemoverTipoDeAplicacao.gridy = 1;
		panel_tipo_de_aplicacao.add(buttonRemoverTipoDeAplicacao, gbc_buttonRemoverTipoDeAplicacao);
		buttonRemoverTipoDeAplicacao.addActionListener(this);
		
		listSelectionModelListaTiposDeAplicacao.addListSelectionListener(
                new ListenerListaTiposDeAplicacao(buttonRemoverTipoDeAplicacao,listaTiposDeAplicacao));
		
		//vamos pegar do arquivo os tipos de aplicacao. Se n existir, usaremos default e escreveremos o arquivo
		CriaELeArquivoCamposDeAplicacaoETiposDeProgramaDat leCamposDeAplicacaoETiposDePrograma = 
													new CriaELeArquivoCamposDeAplicacaoETiposDeProgramaDat();
		String[] tiposDeAplicacaoArray = leCamposDeAplicacaoETiposDePrograma.pegarCamposDeAplicacao();
	    comboBoxAdicionarTipoDeAplicacao = new JComboBox<String>(tiposDeAplicacaoArray);
	    comboBoxAdicionarTipoDeAplicacao.setSelectedIndex(-1);
	    comboBoxAdicionarTipoDeAplicacao.addActionListener(this);
		GridBagConstraints gbc_textFieldAdicionarTipoDeAplicacao = new GridBagConstraints();
		gbc_textFieldAdicionarTipoDeAplicacao.gridwidth = 2;
		gbc_textFieldAdicionarTipoDeAplicacao.gridx = 0;
		gbc_textFieldAdicionarTipoDeAplicacao.gridy = 6;
		panel_tipo_de_aplicacao.add(comboBoxAdicionarTipoDeAplicacao, gbc_textFieldAdicionarTipoDeAplicacao);
		
		//parte em que adiciono o tooltip que fica em cima de cada item do combobox
		ArrayList tooltipsCamposDeAplicacao;
		String[] tooltipsCamposDeAplicacaoEmArrayNormal = leCamposDeAplicacaoETiposDePrograma.pegarTooltipCamposDeAplicacao();
		tooltipsCamposDeAplicacao = new ArrayList<String>(Arrays.asList(tooltipsCamposDeAplicacaoEmArrayNormal));
		ComboboxToolTipRenderer renderer = new ComboboxToolTipRenderer();
		comboBoxAdicionarTipoDeAplicacao.setRenderer(renderer);
		renderer.setTooltips(tooltipsCamposDeAplicacao);
		//fim da parte em que adiciono o tooltip que fica em cima de cada item do combobox
		
		buttonAdicionarTipoDeAplicacao = new JButton("+");
	    GridBagConstraints gbc_buttonAdicionarTipoDeAplicacao = new GridBagConstraints();
	    gbc_buttonAdicionarTipoDeAplicacao.gridx = 2;
	    gbc_buttonAdicionarTipoDeAplicacao.gridy = 6;
	    panel_tipo_de_aplicacao.add(buttonAdicionarTipoDeAplicacao, gbc_buttonAdicionarTipoDeAplicacao);
	    buttonAdicionarTipoDeAplicacao.addActionListener(this);
	    tiposDeAplicacao= new LinkedList<String>();
		
		GridBagConstraints gbc_label_tipo_de_programa = new GridBagConstraints();
		gbc_label_tipo_de_programa.gridheight = 1;
		gbc_label_tipo_de_programa.gridwidth = 2;
		gbc_label_tipo_de_programa.gridx = 0;
		gbc_label_tipo_de_programa.gridy = 0;
		JLabel labelTiposDePrograma = new JLabel("Tipos de Programa");
		panel_tipo_de_programa.add(labelTiposDePrograma);
	    
	    GridBagConstraints gbc_listaTiposDePrograma = new GridBagConstraints();
	    gbc_listaTiposDePrograma.gridheight = 5;
	    gbc_listaTiposDePrograma.gridwidth = 2;
	    gbc_listaTiposDePrograma.insets = new Insets(0, 0, 15, 35);
	    gbc_listaTiposDePrograma.gridx = 0;
	    gbc_listaTiposDePrograma.gridy = 1;
		listaTiposDePrograma.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaTiposDePrograma.setLayoutOrientation(JList.VERTICAL);
		listaTiposDePrograma.setVisibleRowCount(-1);
		JScrollPane scrollPaneListaTiposDePrograma = new JScrollPane(listaTiposDePrograma);
		scrollPaneListaTiposDePrograma.setPreferredSize(new Dimension(120, 100));
		scrollPaneListaTiposDePrograma.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneListaTiposDePrograma.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ListSelectionModel listSelectionModelListaTiposDePrograma = listaTiposDePrograma.getSelectionModel();
		
		panel_tipo_de_programa.add(scrollPaneListaTiposDePrograma, gbc_listaTiposDePrograma);
	    
	    
		buttonRemoverTipoDePrograma = new JButton("-");
		GridBagConstraints gbc_buttonRemoverTipoDePrograma = new GridBagConstraints();
		gbc_buttonRemoverTipoDePrograma.insets = new Insets(15, 0, 0, -10);
		gbc_buttonRemoverTipoDePrograma.gridx = 1;
		gbc_buttonRemoverTipoDePrograma.gridy = 1;
		panel_tipo_de_programa.add(buttonRemoverTipoDePrograma, gbc_buttonRemoverTipoDePrograma);
		buttonRemoverTipoDePrograma.addActionListener(this);
		
		listSelectionModelListaTiposDePrograma.addListSelectionListener(
                new ListenerListaTiposDePrograma(buttonRemoverTipoDePrograma,listaTiposDePrograma));
		
		//vamos ver se o arquivo tipo de programa existe. Se nao, usaremos valores default
		String[] tiposDeProgramaArray = leCamposDeAplicacaoETiposDePrograma.pegarTiposDePrograma();
		
		comboBoxAdicionarTipoDePrograma = new JComboBox<String>(tiposDeProgramaArray);
		comboBoxAdicionarTipoDePrograma.setSelectedIndex(-1);
		comboBoxAdicionarTipoDePrograma.addActionListener(this);
		GridBagConstraints gbc_textFieldAdicionarTipoDePrograma = new GridBagConstraints();
		gbc_textFieldAdicionarTipoDePrograma.gridwidth = 2;
		gbc_textFieldAdicionarTipoDePrograma.gridx = 0;
		gbc_textFieldAdicionarTipoDePrograma.gridy = 6;
		panel_tipo_de_programa.add(comboBoxAdicionarTipoDePrograma, gbc_textFieldAdicionarTipoDePrograma);
		
		buttonAdicionarTipoDePrograma = new JButton("+");
	    GridBagConstraints gbc_buttonAdicionarTipoDePrograma = new GridBagConstraints();
	    gbc_buttonAdicionarTipoDePrograma.gridx = 2;
	    gbc_buttonAdicionarTipoDePrograma.gridy = 6;
	    panel_tipo_de_programa.add(buttonAdicionarTipoDePrograma, gbc_buttonAdicionarTipoDePrograma);
	    buttonAdicionarTipoDePrograma.addActionListener(this);
	    tiposDePrograma= new LinkedList<String>();
		
		
	    //parte em que adiciono o tooltip que fica em cima de cada item do combobox
	    ArrayList tooltipsTipoDePrograma;
	    String[] tooltipsTipoDeProgramaEmArrayNormal = leCamposDeAplicacaoETiposDePrograma.pegarTooltipTiposDePrograma();
	    tooltipsTipoDePrograma = new ArrayList<String>(Arrays.asList(tooltipsTipoDeProgramaEmArrayNormal));
	    ComboboxToolTipRenderer renderer2 = new ComboboxToolTipRenderer();
	    comboBoxAdicionarTipoDePrograma.setRenderer(renderer2);
	    renderer2.setTooltips(tooltipsTipoDePrograma);
	    //fim da parte em que adiciono o tooltip que fica em cima de cada item do combobox
		
		//FIM da parte referente ao tipo de aplicacao e programa
		
		JPanel painel_arquivos = new JPanel();
		painel_arquivos.setBorder(tituloPainelArquivos);
		GridBagConstraints gbc_painel_arquivos = new GridBagConstraints();
		gbc_painel_arquivos.gridheight = 6;//3 era antes
		gbc_painel_arquivos.gridwidth = 4;
		gbc_painel_arquivos.insets = new Insets(0, 0, 5, 5);
		gbc_painel_arquivos.fill = GridBagConstraints.BOTH;
		gbc_painel_arquivos.gridx = 0;
		gbc_painel_arquivos.gridy = 14;
		contentPane.add(painel_arquivos, gbc_painel_arquivos);
		GridBagLayout gbl_painel_arquivos = new GridBagLayout();
		gbl_painel_arquivos.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_painel_arquivos.rowHeights = new int[]{0, 0, 0, 0};
		gbl_painel_arquivos.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_painel_arquivos.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		painel_arquivos.setLayout(gbl_painel_arquivos);
		
		JLabel lblDiretrio = DefaultComponentFactory.getInstance().createLabel("Diret\u00F3rio:");
		GridBagConstraints gbc_lblDiretrio = new GridBagConstraints();
		gbc_lblDiretrio.insets = new Insets(40, 0, 5, 5);
		gbc_lblDiretrio.anchor = GridBagConstraints.EAST;
		gbc_lblDiretrio.gridx = 0;
		gbc_lblDiretrio.gridy = 0;
		painel_arquivos.add(lblDiretrio, gbc_lblDiretrio);
		
		campo_preencher_diretorio = new JTextField();
		GridBagConstraints gbc_campo_preencher_diretorio = new GridBagConstraints();
		gbc_campo_preencher_diretorio.insets = new Insets(40, 0, 5, 5);
		gbc_campo_preencher_diretorio.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_diretorio.gridx = 1;
		gbc_campo_preencher_diretorio.gridy = 0;
		painel_arquivos.add(campo_preencher_diretorio, gbc_campo_preencher_diretorio);
		campo_preencher_diretorio.setColumns(10);
		
		JButton botao_selecionar_pasta_projeto = new JButton("...");
		botao_selecionar_pasta_projeto.setAction(acaoSelecionarPastaProjeto);
		GridBagConstraints gbc_botao_escolher_diretorio_projeto = new GridBagConstraints();
		gbc_botao_escolher_diretorio_projeto.insets = new Insets(40, 0, 5, 5);
		gbc_botao_escolher_diretorio_projeto.gridx = 2;
		gbc_botao_escolher_diretorio_projeto.gridy = 0;
		painel_arquivos.add(botao_selecionar_pasta_projeto, gbc_botao_escolher_diretorio_projeto);
		
		
		
		JLabel lblOutput = DefaultComponentFactory.getInstance().createLabel("Saída:");
		GridBagConstraints gbc_lblOutput = new GridBagConstraints();
		gbc_lblOutput.anchor = GridBagConstraints.EAST;
		gbc_lblOutput.insets = new Insets(0, 0, 5, 5);
		gbc_lblOutput.gridx = 0;
		gbc_lblOutput.gridy = 1;
		painel_arquivos.add(lblOutput, gbc_lblOutput);
		
		campo_preencher_output = new JTextField();
		GridBagConstraints gbc_campo_preencher_output = new GridBagConstraints();
		gbc_campo_preencher_output.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_output.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_output.gridx = 1;
		gbc_campo_preencher_output.gridy = 1;
		painel_arquivos.add(campo_preencher_output, gbc_campo_preencher_output);
		campo_preencher_output.setColumns(10);
		
		JButton botao_especificar_arquivo_output = new JButton("...");
		botao_especificar_arquivo_output.setAction(acaoEspecificarOutput);
		GridBagConstraints gbc_botao_especificar_output = new GridBagConstraints();
		gbc_botao_especificar_output.insets = new Insets(0, 0, 5, 5);
		gbc_botao_especificar_output.gridx = 2;
		gbc_botao_especificar_output.gridy = 1;
		painel_arquivos.add(botao_especificar_arquivo_output, gbc_botao_especificar_output);
		
		JButton botaoEspecificarPastas = new JButton("Avan\u00E7ado...");
		botaoEspecificarPastas.setAction(acaoBotaoEspecificarPastasArquivosProjeto);
		GridBagConstraints gbc_botao_especificar_pastas = new GridBagConstraints();
		gbc_botao_especificar_pastas.insets = new Insets(0, 0, 0, 5);
		gbc_botao_especificar_pastas.gridx = 1;
		gbc_botao_especificar_pastas.gridy = 2;
		painel_arquivos.add(botaoEspecificarPastas, gbc_botao_especificar_pastas);
		
		//PARTE REFERENTE A ADICIONAR EXTENSÃO(ANDREWS)
		JPanel painel_adicionar_extensao = new JPanel();
		GridBagConstraints gbc_painel_adicionar_extensao = new GridBagConstraints();
		gbc_painel_adicionar_extensao.insets = new Insets(0, 0, 5, 0);
		gbc_painel_adicionar_extensao.anchor = GridBagConstraints.NORTH;
		gbc_painel_adicionar_extensao.gridheight = 6;
		gbc_painel_adicionar_extensao.gridx = 4;
		gbc_painel_adicionar_extensao.gridy = 0;
		gbc_painel_adicionar_extensao.gridwidth = 4;
		painel_arquivos.add(painel_adicionar_extensao, gbc_painel_adicionar_extensao);
		GridBagLayout gbl_painel_adicionar_extensao = new GridBagLayout();
		gbl_painel_adicionar_extensao.columnWidths = new int[]{20, 20, 20, 20};
		gbl_painel_adicionar_extensao.rowHeights = new int[]{20, 20, 20, 20};
		gbl_painel_adicionar_extensao.columnWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		gbl_painel_adicionar_extensao.rowWeights = new double[]{0.4, 0.4, 0.4, 0.4};
		painel_adicionar_extensao.setLayout(gbl_painel_adicionar_extensao);
		
		
		
		textFieldAdicionarExtensoes = new JTextField();
		GridBagConstraints gbc_textFieldAdicionarExtensoes = new GridBagConstraints();
		gbc_textFieldAdicionarExtensoes.gridwidth = 3;
		gbc_textFieldAdicionarExtensoes.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldAdicionarExtensoes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdicionarExtensoes.gridx = 0;
		gbc_textFieldAdicionarExtensoes.gridy = 3;
		painel_adicionar_extensao.add(textFieldAdicionarExtensoes, gbc_textFieldAdicionarExtensoes);
		textFieldAdicionarExtensoes.setColumns(10);
		
		buttonRemoverExtensoes = new JButton("-");
		GridBagConstraints gbc_buttonRemoverExtensoes = new GridBagConstraints();
		gbc_buttonRemoverExtensoes.insets = new Insets(0, 0, 5, 0);
		gbc_buttonRemoverExtensoes.gridx = 3;
		gbc_buttonRemoverExtensoes.gridy = 1;
		painel_adicionar_extensao.add(buttonRemoverExtensoes, gbc_buttonRemoverExtensoes);
		buttonRemoverExtensoes.addActionListener(this);
		
		
		this.listModel = new DefaultListModel<String>();
		this.listaExtensoes = new JList<String>(listModel);
		
		GridBagConstraints gbc_label_extensoes = new GridBagConstraints();
		gbc_label_extensoes.gridheight = 1;
		gbc_label_extensoes.gridwidth = 4;
		gbc_label_extensoes.gridx = 0;
		gbc_label_extensoes.gridy = 0;
		JLabel label_extensoes = new JLabel("Extensões");
		painel_adicionar_extensao.add(label_extensoes, gbc_label_extensoes);
		
		GridBagConstraints gbc_listaExtensoes = new GridBagConstraints();
		gbc_listaExtensoes.gridheight = 2;
		gbc_listaExtensoes.gridwidth = 4;
		gbc_listaExtensoes.insets = new Insets(0, 0, 5, 25);
		gbc_listaExtensoes.gridx = 0;
		gbc_listaExtensoes.gridy = 1;
		listaExtensoes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaExtensoes.setLayoutOrientation(JList.VERTICAL);
		listaExtensoes.setVisibleRowCount(-1);
		JScrollPane scrollPane = new JScrollPane(listaExtensoes);
		scrollPane.setPreferredSize(new Dimension(80, 120));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ListSelectionModel listSelectionModel = listaExtensoes.getSelectionModel();
	    listSelectionModel.addListSelectionListener(
	                            new ListenerListaExtensoes(buttonRemoverExtensoes,listaExtensoes));
		
	    painel_adicionar_extensao.add(scrollPane, gbc_listaExtensoes);
	    
	    buttonAdicionarExtensoes = new JButton("+");
	    GridBagConstraints gbc_buttonAdicionarExtensoes = new GridBagConstraints();
	    gbc_buttonAdicionarExtensoes.gridx = 3;
	    gbc_buttonAdicionarExtensoes.gridy = 3;
	    painel_adicionar_extensao.add(buttonAdicionarExtensoes, gbc_buttonAdicionarExtensoes);
	    buttonAdicionarExtensoes.addActionListener(this);
	    extensoes= new LinkedList<String>();
				
		
		
		
		JButton botaoGerarPDF = new JButton("Gerar PDF");
		botaoGerarPDF.setAction(acaoGerarPdf);
		GridBagConstraints gbc_botaoGerarPDF = new GridBagConstraints();
		gbc_botaoGerarPDF.gridheight = 2;
		gbc_botaoGerarPDF.gridwidth = 2;
		gbc_botaoGerarPDF.insets = new Insets(0, 0, 5, 55);
		gbc_botaoGerarPDF.gridx = 1;
		gbc_botaoGerarPDF.gridy = 21;
		contentPane.add(botaoGerarPDF, gbc_botaoGerarPDF);
		
		JButton botaoSobre = new JButton("Sobre...");
		botaoSobre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PopupSobreAFerramenta dialog = new PopupSobreAFerramenta();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
				dialog.setTitle("Sobre Code2inpi");
			}
		});
		GridBagConstraints gbc_botao_sobre = new GridBagConstraints();
		gbc_botao_sobre.gridheight = 1;
		gbc_botao_sobre.gridwidth = 1;
		gbc_botao_sobre.insets = new Insets(0, 0, 5, 5);
		gbc_botao_sobre.gridx = 3;
		gbc_botao_sobre.gridy = 21;
		contentPane.add(botaoSobre, gbc_botao_sobre);
		
		
		//vamos verificar se n jah existem extensoes no arquivo .txt que podemos usar
	    this.verificarSeJaExistemExtensoesNoTxtParaJaPovoarAGuiComEstasExtensoes();
		
		JProgressBar barraDeProgresso = new JProgressBar();
		/*GridBagConstraints gbc_JProgressBarUsuarioClicouNoBotaoAvancado = new GridBagConstraints();
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.insets = new Insets(0, 0, 5, 5);
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridheight = 1;
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridwidth = 2;
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridx = 1;
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridy = 22;*/

		
		//contentPane.add(barraDeProgresso, gbc_JProgressBarUsuarioClicouNoBotaoAvancado);
		SingletonBarraDeProgresso.getInstance().setBarraDeProgresso(barraDeProgresso);
		
		textoBarraDeProgresso = new JLabel("                                                                                 ",SwingConstants.CENTER);
		/*GridBagConstraints gbc_textoBarraDeProgresso = new GridBagConstraints();
		gbc_textoBarraDeProgresso.insets = new Insets(0, 0, 0, 5);
		gbc_textoBarraDeProgresso.gridheight = 1;
		gbc_textoBarraDeProgresso.gridwidth = 2;
		gbc_textoBarraDeProgresso.gridx = 1;
		gbc_textoBarraDeProgresso.gridy = 23;
		contentPane.add(textoBarraDeProgresso, gbc_textoBarraDeProgresso);*/
		textoBarraDeProgresso.setText("                                                                                        ");
		textoBarraDeProgresso.setVisible(true);
		SingletonBarraDeProgresso.getInstance().setTextoBarraDeProgresso(textoBarraDeProgresso);
		
		this.dialogParaBarraDeProgresso = new JDialog();     
		this.dialogParaBarraDeProgresso.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.dialogParaBarraDeProgresso.setTitle("Carregando...");
		JPanel painelProDialog = new JPanel();
		painelProDialog.setPreferredSize(new Dimension(300,100));
		
		GridBagLayout gbl_painelProDialog = new GridBagLayout();
		painelProDialog.setLayout(gbl_painelProDialog);
		
		
		GridBagConstraints gbc_barra_progresso = new GridBagConstraints();
		gbc_barra_progresso.gridx = 0;
		gbc_barra_progresso.gridy = 0;
		gbc_barra_progresso.gridheight = 2;
		gbc_barra_progresso.gridwidth = 3;
		
		painelProDialog.add(barraDeProgresso, gbc_barra_progresso);
		
		GridBagConstraints gbc_texto_progresso = new GridBagConstraints();
		gbc_texto_progresso.gridx = 0;
		gbc_texto_progresso.gridy = 2;
		gbc_texto_progresso.gridheight = 1;
		gbc_texto_progresso.gridwidth = 1;
		
		
		painelProDialog.add(textoBarraDeProgresso,gbc_texto_progresso);
		
		this.dialogParaBarraDeProgresso.add(painelProDialog);

		this.dialogParaBarraDeProgresso.pack();
		//this.dialogParaBarraDeProgresso.setLocationByPlatform(true);
		this.dialogParaBarraDeProgresso.setVisible(true);
		this.dialogParaBarraDeProgresso.setLocationRelativeTo(null);
		
		SingletonBarraDeProgresso.getInstance().setDialogParaBarraDeProgresso(dialogParaBarraDeProgresso);
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.buttonRemoverExtensoes)
		{
			int index = listaExtensoes.getSelectedIndex();
		    this.listModel.remove(index);
		    this.extensoes.remove(index);

		    int size = this.listModel.getSize();

		    if (size == 0) { //Nao tem nenhuma extensao. Desabilitar remover.
		        buttonRemoverExtensoes.setEnabled(false);

		    } else { //Select an index.
		        if (index == this.listModel.getSize()) {
		            //removed item in last position
		            index--;
		        }

		        listaExtensoes.setSelectedIndex(index);
		        listaExtensoes.ensureIndexIsVisible(index);
		    }
		}
		else if (e.getSource() == this.buttonAdicionarExtensoes)
		{
			//antes de adicionar, serah que a linkedlist existe?
			if(this.extensoes == null)
			{
				this.extensoes = new LinkedList<String>();
			}
			
			String novaExtensao = textFieldAdicionarExtensoes.getText();

		    //Usuario n digitou uma extensao valida...
		    if (novaExtensao.equals("") || jaExisteEstaExtensao(novaExtensao)) 
		    {
		    	JOptionPane.showMessageDialog(this, "Digite uma extensão válida e que não já esteja adicionada");
		        return;
		    }
		    
		    //usuario pode ter usado uma extensao com . tipo .java. Vamos tirar os pontos
		    String novaExtensaoSemPonto = novaExtensao.replace(".", "");

		    //coloca no fim da lista
		    listModel.insertElementAt(novaExtensaoSemPonto, this.listModel.getSize());
		    this.extensoes.add(novaExtensaoSemPonto);
		    

		    //Reset the text field.
		    textFieldAdicionarExtensoes.requestFocusInWindow();
		    textFieldAdicionarExtensoes.setText("");

		}
		else if(e.getSource() == this.buttonAdicionarTipoDeAplicacao)
		{
			String novoTipo = (String)comboBoxAdicionarTipoDeAplicacao.getSelectedItem();
			
			if(novoTipo.length() > 0)
			{
				boolean jaExisteEsteItemNaLista = false;
				for(int i = 0; i < this.tiposDeAplicacao.size(); i++)
				{
					String umItem = this.tiposDeAplicacao.get(i);
					if(umItem.compareTo(novoTipo) == 0)
					{
						jaExisteEsteItemNaLista = true;
						break;
					}
				}
				
				if(jaExisteEsteItemNaLista == false)
				{
					this.tiposDeAplicacao.add(novoTipo);
					//coloca no fim da lista
				    listModelListaTiposDeAplicacao.insertElementAt(novoTipo, this.listModelListaTiposDeAplicacao.getSize());
				}
			}
		}
		else if(e.getSource() == this.buttonAdicionarTipoDePrograma)
		{
			String novoTipo = (String)comboBoxAdicionarTipoDePrograma.getSelectedItem();
			
			if(novoTipo.length() > 0)
			{
				boolean jaExisteEsteItemNaLista = false;
				for(int i = 0; i < this.tiposDePrograma.size(); i++)
				{
					String umItem = this.tiposDePrograma.get(i);
					if(umItem.compareTo(novoTipo) == 0)
					{
						jaExisteEsteItemNaLista = true;
						break;
					}
				}
				
				if(jaExisteEsteItemNaLista == false)
				{
					this.tiposDePrograma.add(novoTipo);
					//coloca no fim da lista
				    listModelListaTiposDePrograma.insertElementAt(novoTipo, this.listModelListaTiposDePrograma.getSize());
				}
			}
		}
		else if(e.getSource() == this.buttonRemoverTipoDeAplicacao)
		{
			int index = listaTiposDeAplicacao.getSelectedIndex();
		    this.listModelListaTiposDeAplicacao.remove(index);
		    this.tiposDeAplicacao.remove(index);

		    int size = this.listModelListaTiposDeAplicacao.getSize();

		    if (size == 0) { //Nao tem nenhuma aplicacao. Desabilitar remover.
		        buttonRemoverTipoDeAplicacao.setEnabled(false);

		    } else { //Select an index.
		        if (index == this.listModelListaTiposDeAplicacao.getSize()) {
		            //removed item in last position
		            index--;
		        }

		        listaTiposDeAplicacao.setSelectedIndex(index);
		        listaTiposDeAplicacao.ensureIndexIsVisible(index);
		    }
		}
		else if(e.getSource() == this.buttonRemoverTipoDePrograma)
		{
			int index = listaTiposDePrograma.getSelectedIndex();
		    this.listModelListaTiposDePrograma.remove(index);
		    this.tiposDePrograma.remove(index);

		    int size = this.listModelListaTiposDePrograma.getSize();

		    if (size == 0) { //Nao tem nenhuma aplicacao. Desabilitar remover.
		        buttonRemoverTipoDePrograma.setEnabled(false);

		    } else { //Select an index.
		        if (index == this.listModelListaTiposDePrograma.getSize()) {
		            //removed item in last position
		            index--;
		        }

		        listaTiposDePrograma.setSelectedIndex(index);
		        listaTiposDePrograma.ensureIndexIsVisible(index);
		    }
		}
			
	    
	}
	
	private boolean jaExisteEstaExtensao(String extensao)
	{
		for(int i = 0; i < this.extensoes.size(); i++)
		{
			String umaExtensao = extensoes.get(i);
			if(umaExtensao.compareTo(extensao) == 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	//vamos verificar se existe um arquivo extensoes.txt. Se sim, verificaremos se existem extensoes nele e mostrar ao usuario 
		private void verificarSeJaExistemExtensoesNoTxtParaJaPovoarAGuiComEstasExtensoes()
		{
			CriaeLeArquivoConfiguracoesdat conheceOArquivoComAsExtensoes = new CriaeLeArquivoConfiguracoesdat();
			LinkedList<String> extensoesDoArquivo = conheceOArquivoComAsExtensoes.pegarExtensoesNoTxtExtensoes();
			
			if(extensoesDoArquivo.size() > 0)
			{
				//ja existia alguma extensao. Vamos usa-las
				
				if(this.extensoes == null)
				{
					this.extensoes = new LinkedList<String>();
				}
				
				for(int j = 0; j < extensoesDoArquivo.size(); j++)
				{
					String umaExtensao = extensoesDoArquivo.get(j);
					if (jaExisteEstaExtensao(umaExtensao) == false) 
				    {
						this.extensoes.add(umaExtensao);
						listModel.insertElementAt(umaExtensao, this.listModel.getSize());
				    }
				}
				
			}
		}
	

	private class AcaoEspecificarPastasEArquivosProjeto extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public AcaoEspecificarPastasEArquivosProjeto() {
			putValue(NAME, "Avançado...");
		}
		public void actionPerformed(ActionEvent e) {
			//mudar o cursor para loading
			TelaPrincipal.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			String [] arrayComURlDoProjeto = new String [2];
			arrayComURlDoProjeto[0] = campo_preencher_diretorio.getText();
			if(arrayComURlDoProjeto[0].length() > 0 )
			{
				File pastaDoProjeto = SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getPastaDoProjeto();
				arrayComURlDoProjeto[1] = pastaDoProjeto.getName();
				
				//antes de chamar o main, vamos checar quantos arquivos tem na pasta p dar nocao de tamanho ao usuario
				tamanhoMaximoDaBarraDeProgresso = 0;
				pegaQuantosArquivosTemNoDiretorio(pastaDoProjeto.getPath());
				String textoBarraDeProgresso = "Carregando arquivos da pasta selecionada...";
				SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(tamanhoMaximoDaBarraDeProgresso,textoBarraDeProgresso);
				SeletorPastasProjeto seletorPastasProjeto = new SeletorPastasProjeto();
				seletorPastasProjeto.setExtensoesEspecificadas(extensoes);
				seletorPastasProjeto.main(arrayComURlDoProjeto);
			}
			else
			{
				JOptionPane.showMessageDialog(TelaPrincipal.this, "Especifique uma pasta do projeto primeiro.");
			}
			//voltar o cursor ao normal
			TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
			SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
			
		}
	}
	private class AcaoSelecionarProjeto extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public AcaoSelecionarProjeto() {
			escolhedorPastaProjeto = new JFileChooser();
			putValue(NAME, "...");
		}
		public void actionPerformed(ActionEvent e) {
			//mudar o cursor para loading
			TelaPrincipal.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			//In response to a button click:
			SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().limparListaSelecionados();
			escolhedorPastaProjeto.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY);
			int returnVal = escolhedorPastaProjeto.showOpenDialog(TelaPrincipal.this);
			 if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File pastaDoProjeto = escolhedorPastaProjeto.getSelectedFile();
		            //This is where a real application would open the file.
		            System.out.println("Opening: " + pastaDoProjeto.getPath());
		            SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().setPastaDoProjeto(pastaDoProjeto);
		            campo_preencher_diretorio.setText(pastaDoProjeto.getPath());
		        } else {
		            System.out.println("Open command cancelled by user.");
		            
		        }
			//voltar o cursor ao normal
			TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	
	
	private class AcaoGerarPdf extends AbstractAction {
		public AcaoGerarPdf() {
			putValue(NAME, "Gerar PDF");
		}
		public void actionPerformed(ActionEvent e) {
			//mudar o cursor para loading
			TelaPrincipal.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			
			//antes de tudo, vamos fazer o valor do singletonpdfgeradocomsucesso ser true
			SingletonPDFGeradoComSucessoDeveSerMostrado.getInstance().setDeveSerMostrado(true);
			
			//primeiro checar se usuário preencheu todos os campos
			LinkedList<String> camposNaoPreenchidos = usuarioEsqueceuDePreencherQueCampos();
			if(camposNaoPreenchidos.size() > 0)
			{
				//usuario esqueceu de preencher algguns campos
				String errorMessage = "Erro- Falta preencher os seguintes campos: ";
				for(int i = 0; i < camposNaoPreenchidos.size(); i++)
				{
					String umCampoNaoPreenchido = camposNaoPreenchidos.get(i);
					errorMessage = errorMessage + umCampoNaoPreenchido;
					if(i + 1 < camposNaoPreenchidos.size())
					{
						errorMessage = errorMessage + " , ";
					}
				}
				JOptionPane.showMessageDialog(TelaPrincipal.this, errorMessage);
				TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
				
			}
			else
			{
				//usuário preencheu todos os campos!
				Main main = new Main();

				SingletonGuardaProjetoPastasEArquivosSelecionados guardaDadosProjetoGerarPdf = SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance();
				File pastaRaizDoProjeto = guardaDadosProjetoGerarPdf.getPastaDoProjeto();
				String nomeDiretorioRaizProjeto = pastaRaizDoProjeto.getName();
				String nomeDosAutoresSeparadosPorVirgula = campo_preencher_autor.getText();
				String versaoDoProjeto = campo_preencher_versao.getText();
				String urlOutputProjeto = campo_preencher_output.getText();
				String linguagens = textFieldLinguagens.getText();
				String moduloDoProjeto =  textfieldModulo.getText();
				Main.outputFILE2 = urlOutputProjeto;
				Main.outputFILE = urlOutputProjeto;
				String tituloProjeto = campo_nome_projeto.getText();
				main.gerarPDFParaRegistroDeSoftware(extensoes, tituloProjeto,nomeDiretorioRaizProjeto, versaoDoProjeto, nomeDosAutoresSeparadosPorVirgula, linguagens,tiposDeAplicacao,tiposDePrograma, moduloDoProjeto);
				//voltar o cursor ao normal
				TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
				
				if(SingletonPDFGeradoComSucessoDeveSerMostrado.getInstance().getDeveSerMostrado() == true)
				{
					JOptionPane.showMessageDialog(TelaPrincipal.this, "Arquivo PDF gerado com sucesso!");
					//faltou soh colocar num arquivo de configuracoes as extensoes que usamos, isso se o usuario quiser
					CriaeLeArquivoConfiguracoesdat criaExtensoesdat = new CriaeLeArquivoConfiguracoesdat();
					criaExtensoesdat.criarArquivoConfiguracoesdat(extensoes);
				}
				else
				{
					//tinha algum id q tornou o projeto incapaz de gerar numero de paginas e o usuario decidiun gerar o pdf
				}
			}

			
		}
		
		public LinkedList<String> usuarioEsqueceuDePreencherQueCampos()
		{
			LinkedList<String> camposNaoPreenchidos = new LinkedList<String>();
			if(campo_nome_projeto.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("título");
			}
			if(textfieldModulo.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("Módulo");
			}
			SingletonGuardaProjetoPastasEArquivosSelecionados guardaDadosProjetoGerarPdf = SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance();
			File pastaRaizDoProjeto = guardaDadosProjetoGerarPdf.getPastaDoProjeto();
			if(pastaRaizDoProjeto == null)
			{
				camposNaoPreenchidos.add("diretório");
			}
			if(campo_preencher_autor.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("autor");
			}
			if(campo_preencher_versao.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("versão");
			}
			if(campo_preencher_output.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("output");
			}
			if(extensoes.size() <=0)
			{
				camposNaoPreenchidos.add("extensões");
			}
			
			if(textFieldLinguagens.getText().length() <= 0)
			{
				camposNaoPreenchidos.add("linguagens");
			}
			
			if(tiposDeAplicacao == null || tiposDeAplicacao.size() == 0)
			{
				camposNaoPreenchidos.add("campos de aplicação");
			}
			
			if(tiposDePrograma == null || tiposDePrograma.size() == 0)
			{
				camposNaoPreenchidos.add("tipos de programa");
			}
			
			
			return camposNaoPreenchidos;
			
		}
	}
	private class AcaoEspecificarOutput extends AbstractAction {
		public AcaoEspecificarOutput() {
			escolhedorOutputProjeto= new JFileChooser();
			putValue(NAME, "...");
		}
		public void actionPerformed(ActionEvent e) {
			//mudar o cursor para loading
			TelaPrincipal.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			escolhedorOutputProjeto.setSelectedFile(new File("meuProjeto.pdf"));
			//In response to a button click:
			escolhedorOutputProjeto.addChoosableFileFilter(new FileFilter() {
				 
			    public String getDescription() {
			        return "Arquivos PDF (*.pdf)";
			    }
			 
			    public boolean accept(File f) {
			        if (f.isDirectory()) {
			            return true;
			        } else {
			            return f.getName().toLowerCase().endsWith(".pdf");
			        }
			    }
			});
			escolhedorOutputProjeto.setFileSelectionMode( JFileChooser.FILES_ONLY);
			int returnVal = escolhedorOutputProjeto.showSaveDialog(TelaPrincipal.this);
			 if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File pdfDoProjeto = escolhedorOutputProjeto.getSelectedFile();
		            //This is where a real application would open the file.
		            System.out.println("output: " + pdfDoProjeto.getPath());
		            SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().setOutputSelecionado(pdfDoProjeto);
		            campo_preencher_output.setText(pdfDoProjeto.getPath());
		        } else {
		            System.out.println("Select output command cancelled by user.");
		            
		        }
			//voltar o cursor ao normal
			TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
		}
	}
	
	
	private int pegaQuantosArquivosTemNoDiretorio(String dirPath) {
	    File f = new File(dirPath);
	    File[] files = f.listFiles();

	    if (files != null)
	    for (int i = 0; i < files.length; i++) {
	    	tamanhoMaximoDaBarraDeProgresso++;
	        File file = files[i];

	        if (file.isDirectory()) {   
	        	pegaQuantosArquivosTemNoDiretorio(file.getAbsolutePath()); 
	        }
	    }
	    
	    return tamanhoMaximoDaBarraDeProgresso;
	}
	
	public void alterarTextoBarraProgresso(String texto)
	{
		this.textoBarraDeProgresso.setText(texto);
	}
	
	public void setVisibletextoBarraDeProgresso()
	{
		this.textoBarraDeProgresso.setVisible(true);
	}
}
