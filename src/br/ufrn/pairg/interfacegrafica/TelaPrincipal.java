package br.ufrn.pairg.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.JLabel;

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
	private final Action acaoGerarPdf = new AcacoGerarPdf();
	private final Action acaoEspecificarOutput = new AcaoEspecificarOutput();
	

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
		setBounds(100, 100, 650, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{12, 92, 42, 42, 92};
		gbl_contentPane.rowHeights = new int[]{19, 19, 19, 19, 19, 19, 19};
		gbl_contentPane.columnWeights = new double[]{0.2, 0.8, 0.1, 0.1, 0.6};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE, 0.0};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel TituloTela = DefaultComponentFactory.getInstance().createTitle("CodeFont 2 File");
		TituloTela.setFont(new Font("Tahoma", Font.BOLD, 15));
		TituloTela.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_TituloTela = new GridBagConstraints();
		gbc_TituloTela.gridwidth = 7;
		gbc_TituloTela.gridheight = 1;
		gbc_TituloTela.insets = new Insets(0, 0, 5, 0);
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
		
		JLabel label_diretorio = DefaultComponentFactory.getInstance().createLabel("Diret\u00F3rio:");
		GridBagConstraints gbc_label_diretorio = new GridBagConstraints();
		gbc_label_diretorio.anchor = GridBagConstraints.EAST;
		gbc_label_diretorio.insets = new Insets(0, 0, 5, 5);
		gbc_label_diretorio.gridx = 0;
		gbc_label_diretorio.gridy = 3;
		gbc_label_diretorio.gridwidth = 1;
		gbc_label_diretorio.gridheight = 1;
		contentPane.add(label_diretorio, gbc_label_diretorio);
		
		campo_preencher_diretorio = new JTextField();
		GridBagConstraints gbc_campo_preencher_diretorio = new GridBagConstraints();
		gbc_campo_preencher_diretorio.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_diretorio.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_diretorio.gridx = 1;
		gbc_campo_preencher_diretorio.gridy = 3;
		gbc_campo_preencher_diretorio.gridheight = 1;
		gbc_descricao_software.gridwidth = 1;
		contentPane.add(campo_preencher_diretorio, gbc_campo_preencher_diretorio);
		campo_preencher_diretorio.setColumns(20);
		
		JButton botao_selecionar_pasta_projeto = new JButton("...");
		botao_selecionar_pasta_projeto.setAction(acaoSelecionarPastaProjeto);
		GridBagConstraints gbc_botao_selecionar_pasta_projeto = new GridBagConstraints();
		gbc_botao_selecionar_pasta_projeto.insets = new Insets(0, 0, 5, 5);
		gbc_botao_selecionar_pasta_projeto.gridx = 2;
		gbc_botao_selecionar_pasta_projeto.gridy = 3;
		gbc_botao_selecionar_pasta_projeto.gridheight = 1;
		contentPane.add(botao_selecionar_pasta_projeto, gbc_botao_selecionar_pasta_projeto);
		
		JButton botao_explicacao_selecione_diretorio = new JButton("?");
		botao_explicacao_selecione_diretorio.setHorizontalAlignment(SwingConstants.LEFT);
		botao_explicacao_selecione_diretorio.setToolTipText("escolha a pasta raiz do seu projeto");
		GridBagConstraints gbc_botao_explicacao_selecione_diretorio = new GridBagConstraints();
		gbc_botao_explicacao_selecione_diretorio.insets = new Insets(0, 0, 5, 5);
		gbc_botao_explicacao_selecione_diretorio.gridx = 3;
		gbc_botao_explicacao_selecione_diretorio.gridy = 3;
		gbc_botao_explicacao_selecione_diretorio.gridheight = 1;
		contentPane.add(botao_explicacao_selecione_diretorio, gbc_botao_explicacao_selecione_diretorio);
		
		//PARTE REFERENTE A ADICIONAR EXTENSÃO(ANDREWS)
		JPanel painel_adicionar_extensao = new JPanel();
		TitledBorder tituloPainelExtensoes;
		tituloPainelExtensoes = BorderFactory.createTitledBorder("Extensões");
		painel_adicionar_extensao.setBorder(tituloPainelExtensoes);
		GridBagConstraints gbc_painel_adicionar_extensao = new GridBagConstraints();
		gbc_painel_adicionar_extensao.anchor = GridBagConstraints.NORTH;
		gbc_painel_adicionar_extensao.gridheight = 7;
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
		
	    
		
		JLabel label_autor = DefaultComponentFactory.getInstance().createLabel("Autor:");
		GridBagConstraints gbc_label_autor = new GridBagConstraints();
		gbc_label_autor.anchor = GridBagConstraints.EAST;
		gbc_label_autor.insets = new Insets(0, 0, 5, 5);
		gbc_label_autor.gridx = 0;
		gbc_label_autor.gridy = 4;
		gbc_label_autor.gridheight = 1;
		contentPane.add(label_autor, gbc_label_autor);
		
		campo_preencher_autor = new JTextField();
		GridBagConstraints gbc_campo_preencher_autor = new GridBagConstraints();
		gbc_campo_preencher_autor.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_autor.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_autor.gridx = 1;
		gbc_campo_preencher_autor.gridy = 4;
		gbc_campo_preencher_autor.gridheight = 1;
		contentPane.add(campo_preencher_autor, gbc_campo_preencher_autor);
		campo_preencher_autor.setColumns(20);
		
		JLabel label_versao = DefaultComponentFactory.getInstance().createLabel("Vers\u00E3o:");
		GridBagConstraints gbc_label_versao = new GridBagConstraints();
		gbc_label_versao.anchor = GridBagConstraints.EAST;
		gbc_label_versao.insets = new Insets(0, 0, 5, 5);
		gbc_label_versao.gridx = 0;
		gbc_label_versao.gridy = 5;
		gbc_label_versao.gridheight = 1;
		contentPane.add(label_versao, gbc_label_versao);
		
		campo_preencher_versao = new JTextField();
		GridBagConstraints gbc_campo_preencher_versao = new GridBagConstraints();
		gbc_campo_preencher_versao.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_versao.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_versao.gridx = 1;
		gbc_campo_preencher_versao.gridy = 5;
		contentPane.add(campo_preencher_versao, gbc_campo_preencher_versao);
		gbc_campo_preencher_versao.gridheight = 1;
		campo_preencher_versao.setColumns(10);
		
		JLabel label_output = DefaultComponentFactory.getInstance().createLabel("Output:");
		GridBagConstraints gbc_label_output = new GridBagConstraints();
		gbc_label_output.anchor = GridBagConstraints.EAST;
		gbc_label_output.insets = new Insets(0, 0, 5, 5);
		gbc_label_output.gridx = 0;
		gbc_label_output.gridy = 6;
		gbc_label_output.gridheight = 1;
		contentPane.add(label_output, gbc_label_output);
		
		campo_preencher_output = new JTextField();
		GridBagConstraints gbc_campo_preencher_output = new GridBagConstraints();
		gbc_campo_preencher_output.insets = new Insets(0, 0, 5, 5);
		gbc_campo_preencher_output.fill = GridBagConstraints.HORIZONTAL;
		gbc_campo_preencher_output.gridx = 1;
		gbc_campo_preencher_output.gridy = 6;
		gbc_campo_preencher_output.gridheight = 1;
		contentPane.add(campo_preencher_output, gbc_campo_preencher_output);
		campo_preencher_output.setColumns(10);
		
		JButton botao_especificar_arquivo_output = new JButton("...");
		botao_especificar_arquivo_output.setAction(acaoEspecificarOutput);
		GridBagConstraints gbc_botao_especificar_arquivo_output = new GridBagConstraints();
		gbc_botao_especificar_arquivo_output.insets = new Insets(0, 0, 5, 5);
		gbc_botao_especificar_arquivo_output.gridx = 2;
		gbc_botao_especificar_arquivo_output.gridy = 6;
		gbc_botao_especificar_arquivo_output.gridheight = 1;
		contentPane.add(botao_especificar_arquivo_output, gbc_botao_especificar_arquivo_output);
		
		JButton botaoEspecificarPastas = new JButton("Avançado...");
		botaoEspecificarPastas.setAction(acaoBotaoEspecificarPastasArquivosProjeto);
		GridBagConstraints gbc_botaoEspecificarPastas = new GridBagConstraints();
		gbc_botaoEspecificarPastas.gridwidth = 2;
		gbc_botaoEspecificarPastas.insets = new Insets(5, 0, 5, 5);
		gbc_botaoEspecificarPastas.gridx = 1;
		gbc_botaoEspecificarPastas.gridy = 7;
		contentPane.add(botaoEspecificarPastas, gbc_botaoEspecificarPastas);
		
		JButton botao_explicacao_especificar_pastas = new JButton("?");
		botao_explicacao_especificar_pastas.setToolTipText("especificar que pastas/arquivos de seu projeto você quer no PDF");
		GridBagConstraints gbc_botao_explicacao_especificar_pastas = new GridBagConstraints();
		gbc_botao_explicacao_especificar_pastas.insets = new Insets(5, 0, 5, 5);
		gbc_botao_explicacao_especificar_pastas.gridx = 3;
		gbc_botao_explicacao_especificar_pastas.gridy = 7;
		contentPane.add(botao_explicacao_especificar_pastas, gbc_botao_explicacao_especificar_pastas);
		
		JButton botaoGerarPDF = new JButton("Gerar PDF");
		botaoGerarPDF.setAction(acaoGerarPdf);
		GridBagConstraints gbc_botaoGerarPDF = new GridBagConstraints();
		gbc_botaoGerarPDF.gridheight = 3;
		gbc_botaoGerarPDF.gridwidth = 2;
		gbc_botaoGerarPDF.insets = new Insets(0, 0, 5, 5);
		gbc_botaoGerarPDF.gridx = 1;
		gbc_botaoGerarPDF.gridy = 8;
		contentPane.add(botaoGerarPDF, gbc_botaoGerarPDF);
		
		
	    extensoes= new LinkedList<String>();
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
				SeletorPastasProjeto.main(arrayComURlDoProjeto);
			}
			else
			{
				JOptionPane.showMessageDialog(TelaPrincipal.this, "Especifique uma pasta do projeto primeiro.");
			}
			//voltar o cursor ao normal
			TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
			
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
	
	
	
	private class AcacoGerarPdf extends AbstractAction {
		public AcacoGerarPdf() {
			putValue(NAME, "Gerar PDF");
		}
		public void actionPerformed(ActionEvent e) {
			//mudar o cursor para loading
			TelaPrincipal.this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
			Main main = new Main();

			SingletonGuardaProjetoPastasEArquivosSelecionados guardaDadosProjetoGerarPdf = SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance();
			File pastaRaizDoProjeto = guardaDadosProjetoGerarPdf.getPastaDoProjeto();
			String nomeDiretorioRaizProjeto = pastaRaizDoProjeto.getName();
			String nomeDosAutoresSeparadosPorVirgula = campo_preencher_autor.getText();
			String versaoDoProjeto = campo_preencher_versao.getText();
			String urlOutputProjeto = campo_preencher_output.getText();
			Main.outputFILE2 = urlOutputProjeto;
			Main.outputFILE = urlOutputProjeto;
			main.gerarPDFParaRegistroDeSoftware(extensoes, nomeDiretorioRaizProjeto, versaoDoProjeto, nomeDosAutoresSeparadosPorVirgula);
			//voltar o cursor ao normal
			TelaPrincipal.this.setCursor(Cursor.getDefaultCursor());
			
			JOptionPane.showMessageDialog(TelaPrincipal.this, "Arquivo PDF gerado com sucesso!");
			
			
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
}
