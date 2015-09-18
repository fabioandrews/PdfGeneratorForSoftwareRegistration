package br.ufrn.pairg.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;

import br.ufrn.pairg.pdfgenerator.CriaeLeTxtComExtensoes;
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
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.Action;


import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.*;

public class TelaPrincipal extends JFrame implements ActionListener {

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
	public TelaPrincipal() {
		setTitle("codefont2file");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{12, 92, 42, 42, 92};
		gbl_contentPane.rowHeights = new int[]{19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19, 19};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 0.1, 0.1, 0.6};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel TituloTela = DefaultComponentFactory.getInstance().createTitle("CodeFont 2 File");
		TituloTela.setFont(new Font("Tahoma", Font.BOLD, 15));
		TituloTela.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_TituloTela = new GridBagConstraints();
		gbc_TituloTela.gridwidth = 7;
		gbc_TituloTela.gridheight = 1;
		gbc_TituloTela.insets = new Insets(0, 0, 5, 5);
		gbc_TituloTela.anchor = GridBagConstraints.NORTH;
		gbc_TituloTela.fill = GridBagConstraints.HORIZONTAL;
		gbc_TituloTela.gridx = 0;
		gbc_TituloTela.gridy = 0;
		contentPane.add(TituloTela, gbc_TituloTela);
		
		JLabel descricao_software = DefaultComponentFactory.getInstance().createLabel("Bem vindo ao Codefont2File! Escolha seu projeto que converteremos");
		GridBagConstraints gbc_descricao_software = new GridBagConstraints();
		gbc_descricao_software.insets = new Insets(0, 0, 5, 0);
		gbc_descricao_software.gridx = 0;
		gbc_descricao_software.gridy = 1;
		gbc_descricao_software.gridwidth = 8;
		gbc_descricao_software.gridheight = 1;
		contentPane.add(descricao_software, gbc_descricao_software);
		
		JLabel descricao_software2 = DefaultComponentFactory.getInstance().createLabel("o c\u00F3digo fonte para um \u00FAnico PDF!");
		descricao_software2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_descricao_software2 = new GridBagConstraints();
		gbc_descricao_software2.gridwidth = 8;
		gbc_descricao_software2.gridheight = 1;
		gbc_descricao_software2.insets = new Insets(0, 0, 5, 0);
		gbc_descricao_software2.gridx = 0;
		gbc_descricao_software2.gridy = 2;
		contentPane.add(descricao_software2, gbc_descricao_software2);
		gbc_descricao_software.gridwidth = 1;
		
		//PARTE REFERENTE A ADICIONAR EXTENSÃO(ANDREWS)
		JPanel painel_adicionar_extensao = new JPanel();
		TitledBorder tituloPainelExtensoes;
		tituloPainelExtensoes = BorderFactory.createTitledBorder("Extensões");
		
		JPanel painel_opcoes_projeto = new JPanel();
		TitledBorder tituloPainelProjeto;
		tituloPainelProjeto = BorderFactory.createTitledBorder("Projeto");
		painel_opcoes_projeto.setBorder(tituloPainelProjeto);
		GridBagConstraints gbc_painel_opcoes_projeto = new GridBagConstraints();
		gbc_painel_opcoes_projeto.insets = new Insets(0, 0, 5, 5);
		gbc_painel_opcoes_projeto.fill = GridBagConstraints.BOTH;
		gbc_painel_opcoes_projeto.gridx = 0;
		gbc_painel_opcoes_projeto.gridy = 3;
		gbc_painel_opcoes_projeto.gridwidth = 4;
		gbc_painel_opcoes_projeto.gridheight = 3;
		
		contentPane.add(painel_opcoes_projeto, gbc_painel_opcoes_projeto);
		GridBagLayout gbl_painel_opcoes_projeto = new GridBagLayout();
		gbl_painel_opcoes_projeto.columnWidths = new int[]{0, 0, 0};
		gbl_painel_opcoes_projeto.rowHeights = new int[]{0, 0, 0};
		gbl_painel_opcoes_projeto.columnWeights = new double[]{Double.MIN_VALUE, 1.0};
		gbl_painel_opcoes_projeto.rowWeights = new double[]{0.0, 0.0, 0.0};
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
		
		JButton botaoHintNomeProjeto = new JButton("?");
		botaoHintNomeProjeto.setToolTipText("Nome do seu projeto");
		GridBagConstraints gbc_explicacao_nome_projeto = new GridBagConstraints();
		gbc_explicacao_nome_projeto.insets = new Insets(0, 0, 5, 0);
		gbc_explicacao_nome_projeto.gridx = 2;
		gbc_explicacao_nome_projeto.gridy = 0;
		painel_opcoes_projeto.add(botaoHintNomeProjeto, gbc_explicacao_nome_projeto);
		
		JLabel lblAutor = DefaultComponentFactory.getInstance().createLabel("Autor:");
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
		painel_adicionar_extensao.setBorder(tituloPainelExtensoes);
		GridBagConstraints gbc_painel_adicionar_extensao = new GridBagConstraints();
		gbc_painel_adicionar_extensao.insets = new Insets(0, 0, 5, 0);
		gbc_painel_adicionar_extensao.anchor = GridBagConstraints.NORTH;
		gbc_painel_adicionar_extensao.gridheight = 8;
		gbc_painel_adicionar_extensao.gridx = 4;
		gbc_painel_adicionar_extensao.gridy = 3;
		gbc_painel_adicionar_extensao.gridwidth = 4;
		contentPane.add(painel_adicionar_extensao, gbc_painel_adicionar_extensao);
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
		gbc_buttonRemoverExtensoes.gridy = 0;
		painel_adicionar_extensao.add(buttonRemoverExtensoes, gbc_buttonRemoverExtensoes);
		buttonRemoverExtensoes.addActionListener(this);
		
		this.listModel = new DefaultListModel<String>();
		this.listaExtensoes = new JList<String>(listModel);
		
		GridBagConstraints gbc_listaExtensoes = new GridBagConstraints();
		gbc_listaExtensoes.gridheight = 3;
		gbc_listaExtensoes.gridwidth = 4;
		gbc_listaExtensoes.insets = new Insets(0, 0, 5, 25);
		gbc_listaExtensoes.gridx = 0;
		gbc_listaExtensoes.gridy = 0;
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
		
		JPanel painel_arquivos = new JPanel();
		TitledBorder tituloPainelArquivos;
		tituloPainelArquivos = BorderFactory.createTitledBorder("Arquivos");
		painel_arquivos.setBorder(tituloPainelArquivos);
		GridBagConstraints gbc_painel_arquivos = new GridBagConstraints();
		gbc_painel_arquivos.gridheight = 3;
		gbc_painel_arquivos.gridwidth = 4;
		gbc_painel_arquivos.insets = new Insets(0, 0, 5, 5);
		gbc_painel_arquivos.fill = GridBagConstraints.BOTH;
		gbc_painel_arquivos.gridx = 0;
		gbc_painel_arquivos.gridy = 6;
		contentPane.add(painel_arquivos, gbc_painel_arquivos);
		GridBagLayout gbl_painel_arquivos = new GridBagLayout();
		gbl_painel_arquivos.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_painel_arquivos.rowHeights = new int[]{0, 0, 0, 0};
		gbl_painel_arquivos.columnWeights = new double[]{0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_painel_arquivos.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		painel_arquivos.setLayout(gbl_painel_arquivos);
		
		JLabel lblDiretrio = DefaultComponentFactory.getInstance().createLabel("Diret\u00F3rio:");
		GridBagConstraints gbc_lblDiretrio = new GridBagConstraints();
		gbc_lblDiretrio.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiretrio.anchor = GridBagConstraints.EAST;
		gbc_lblDiretrio.gridx = 0;
		gbc_lblDiretrio.gridy = 0;
		painel_arquivos.add(lblDiretrio, gbc_lblDiretrio);
		
		campo_preencher_diretorio = new JTextField();
		GridBagConstraints gbc_campo_preencher_diretorio = new GridBagConstraints();
		gbc_campo_preencher_diretorio.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_diretorio.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_diretorio.gridx = 1;
		gbc_campo_preencher_diretorio.gridy = 0;
		painel_arquivos.add(campo_preencher_diretorio, gbc_campo_preencher_diretorio);
		campo_preencher_diretorio.setColumns(10);
		
		JButton botao_selecionar_pasta_projeto = new JButton("...");
		botao_selecionar_pasta_projeto.setAction(acaoSelecionarPastaProjeto);
		GridBagConstraints gbc_botao_escolher_diretorio_projeto = new GridBagConstraints();
		gbc_botao_escolher_diretorio_projeto.insets = new Insets(0, 0, 5, 5);
		gbc_botao_escolher_diretorio_projeto.gridx = 2;
		gbc_botao_escolher_diretorio_projeto.gridy = 0;
		painel_arquivos.add(botao_selecionar_pasta_projeto, gbc_botao_escolher_diretorio_projeto);
		
		JButton botao_explicacao_selecione_diretorio = new JButton("?");
		botao_explicacao_selecione_diretorio.setToolTipText("escolha a pasta raiz do seu projeto");
		GridBagConstraints gbc_botao_instrucao_escolher_diretorio = new GridBagConstraints();
		gbc_botao_instrucao_escolher_diretorio.insets = new Insets(0, 0, 5, 0);
		gbc_botao_instrucao_escolher_diretorio.gridx = 3;
		gbc_botao_instrucao_escolher_diretorio.gridy = 0;
		painel_arquivos.add(botao_explicacao_selecione_diretorio, gbc_botao_instrucao_escolher_diretorio);
		
		JLabel lblOutput = DefaultComponentFactory.getInstance().createLabel("Output:");
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
		
		JButton botao_instrucoes_avancado = new JButton("?");
		botao_instrucoes_avancado.setToolTipText("especificar que pastas/arquivos de seu projeto você quer no PDF");
		GridBagConstraints gbc_botao_instrucoes_avancado = new GridBagConstraints();
		gbc_botao_instrucoes_avancado.insets = new Insets(0, 0, 0, 5);
		gbc_botao_instrucoes_avancado.gridx = 2;
		gbc_botao_instrucoes_avancado.gridy = 2;
		painel_arquivos.add(botao_instrucoes_avancado, gbc_botao_instrucoes_avancado);
		
		JButton botaoGerarPDF = new JButton("Gerar PDF");
		botaoGerarPDF.setAction(acaoGerarPdf);
		GridBagConstraints gbc_botaoGerarPDF = new GridBagConstraints();
		gbc_botaoGerarPDF.gridheight = 2;
		gbc_botaoGerarPDF.gridwidth = 2;
		gbc_botaoGerarPDF.insets = new Insets(0, 0, 0, 5);
		gbc_botaoGerarPDF.gridx = 1;
		gbc_botaoGerarPDF.gridy = 9;
		contentPane.add(botaoGerarPDF, gbc_botaoGerarPDF);
		
		
		
	    extensoes= new LinkedList<String>();

	    //vamos verificar se n jah existem extensoes no arquivo .txt que podemos usar
	    this.verificarSeJaExistemExtensoesNoTxtParaJaPovoarAGuiComEstasExtensoes();
	    
	    JProgressBar barraDeProgresso = new JProgressBar();
		GridBagConstraints gbc_JProgressBarUsuarioClicouNoBotaoAvancado = new GridBagConstraints();
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridheight = 1;
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridwidth = 2;
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridx = 1;
		gbc_JProgressBarUsuarioClicouNoBotaoAvancado.gridy = 11;
		contentPane.add(barraDeProgresso, gbc_JProgressBarUsuarioClicouNoBotaoAvancado);
		SingletonBarraDeProgresso.getInstance().setBarraDeProgresso(barraDeProgresso);
		
		textoBarraDeProgresso = new JLabel("                                                                                 ",SwingConstants.CENTER);
		GridBagConstraints gbc_textoBarraDeProgresso = new GridBagConstraints();
		gbc_textoBarraDeProgresso.gridheight = 1;
		gbc_textoBarraDeProgresso.gridwidth = 2;
		gbc_textoBarraDeProgresso.gridx = 1;
		gbc_textoBarraDeProgresso.gridy = 12;
		contentPane.add(textoBarraDeProgresso, gbc_textoBarraDeProgresso);
		textoBarraDeProgresso.setText("                                                                                        ");
		textoBarraDeProgresso.setVisible(true);
		SingletonBarraDeProgresso.getInstance().setTextoBarraDeProgresso(textoBarraDeProgresso);
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

		    //coloca no fim da lista
		    listModel.insertElementAt(novaExtensao, this.listModel.getSize());
		    this.extensoes.add(novaExtensao);
		    

		    //Reset the text field.
		    textFieldAdicionarExtensoes.requestFocusInWindow();
		    textFieldAdicionarExtensoes.setText("");

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
			CriaeLeTxtComExtensoes conheceOArquivoComAsExtensoes = new CriaeLeTxtComExtensoes();
			LinkedList<String> extensoesDoArquivo = conheceOArquivoComAsExtensoes.pegarExtensoesNoTxtExtensoes();
			
			if(extensoesDoArquivo.size() > 0)
			{
				//ja existia alguma extensao. Vamos perguntar ao usuario se ele quer usa-las
				String extensoesSeparadasPorVirgula = "";
				for(int i = 0; i < extensoesDoArquivo.size(); i++)
				{
					extensoesSeparadasPorVirgula = extensoesSeparadasPorVirgula + extensoesDoArquivo.get(i);
					
					if(i != extensoesDoArquivo.size() - 1)
					{
						extensoesSeparadasPorVirgula = extensoesSeparadasPorVirgula + ",";
					}
				}
				int resposta = JOptionPane.showConfirmDialog(null, "No arquivo extensoes.txt, foram encontradas as seguintes extensoes: \n" + extensoesSeparadasPorVirgula + "\nDeseja usá-las?", "Foram encontradas extensões em extensoes.txt",  JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION)
				{
					//vamos usar estas extensoes
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
				else
				{
					//n faz nada porque o usuario n quer usar as extensoes do arquivo
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
				Main.outputFILE2 = urlOutputProjeto;
				Main.outputFILE = urlOutputProjeto;
				String tituloProjeto = campo_nome_projeto.getText();
				main.gerarPDFParaRegistroDeSoftware(extensoes, tituloProjeto,nomeDiretorioRaizProjeto, versaoDoProjeto, nomeDosAutoresSeparadosPorVirgula);
				//voltar o cursor ao normal
				TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
				
				JOptionPane.showMessageDialog(TelaPrincipal.this, "Arquivo PDF gerado com sucesso!");
				//faltou soh colocar no arquivo extensoes.txt as extensoes que usamos, isso se o usuario quiser
				int resposta = JOptionPane.showConfirmDialog(null, "Deseja gravar as extensões usadas no arquivo extensoes.txt para usá-las futuramente em outro projeto?", "Gravar extensoes em extensoes.txt",  JOptionPane.YES_NO_OPTION);
				if (resposta == JOptionPane.YES_OPTION)
				{
					CriaeLeTxtComExtensoes criaExtensoestxt = new CriaeLeTxtComExtensoes();
					criaExtensoestxt.criarArquivoExtensoesTxt(extensoes);
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
