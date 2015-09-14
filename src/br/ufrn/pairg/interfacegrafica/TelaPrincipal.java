package br.ufrn.pairg.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.Action;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	private JTextField campo_preencher_diretorio;
	private JTextField campo_preencher_autor;
	private JTextField campo_preencher_versao;
	private JTextField campo_preencher_output;
	private final Action acaoBotaoEspecificarPastasArquivosProjeto = new AcaoEspecificarPastasEArquivosProjeto();
	private final Action acaoSelecionarPastaProjeto = new AcaoSelecionarProjeto();
	private static JFileChooser escolhedorPastaProjeto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		gbl_contentPane.columnWidths = new int[]{92, 0};
		gbl_contentPane.rowHeights = new int[]{19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
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
		gbc_descricao_software.gridwidth = 7;
		gbc_descricao_software.gridheight = 1;
		contentPane.add(descricao_software, gbc_descricao_software);
		
		JLabel descricao_software2 = DefaultComponentFactory.getInstance().createLabel("o c\u00F3digo fonte para um \u00FAnico PDF!");
		descricao_software2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_descricao_software2 = new GridBagConstraints();
		gbc_descricao_software2.gridwidth = 7;
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
		botao_explicacao_selecione_diretorio.setToolTipText("escolha a parta raiz do seu projeto");
		GridBagConstraints gbc_botao_explicacao_selecione_diretorio = new GridBagConstraints();
		gbc_botao_explicacao_selecione_diretorio.insets = new Insets(0, 0, 5, 5);
		gbc_botao_explicacao_selecione_diretorio.gridx = 3;
		gbc_botao_explicacao_selecione_diretorio.gridy = 3;
		gbc_botao_explicacao_selecione_diretorio.gridheight = 1;
		contentPane.add(botao_explicacao_selecione_diretorio, gbc_botao_explicacao_selecione_diretorio);
		
		
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
		GridBagConstraints gbc_botaoGerarPDF = new GridBagConstraints();
		gbc_botaoGerarPDF.gridheight = 3;
		gbc_botaoGerarPDF.gridwidth = 2;
		gbc_botaoGerarPDF.insets = new Insets(0, 0, 5, 5);
		gbc_botaoGerarPDF.gridx = 1;
		gbc_botaoGerarPDF.gridy = 8;
		contentPane.add(botaoGerarPDF, gbc_botaoGerarPDF);
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
			//In response to a button click:
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
		}
	}
}
