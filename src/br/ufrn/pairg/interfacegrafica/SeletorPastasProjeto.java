package br.ufrn.pairg.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JTree;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import javax.swing.JLabel;

import br.ufrn.pairg.interfacegrafica.checkboxtree.CheckTreeManager;
import br.ufrn.pairg.pdfgenerator.ArquivoDoProjeto;
import br.ufrn.pairg.pdfgenerator.PastaDoProjeto;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Insets;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JScrollPane;
import javax.swing.AbstractAction;
import javax.swing.Action;

public class SeletorPastasProjeto extends JDialog {

	/**
	 * Launch the application.
	 */
	
	private static String urlPastaDoProjeto = "";
	private static String nomePastaDoProjeto = "";
	private final Action acaoBotaoOk = new SwingAction();
	private CheckTreeManager checkTreeManager;
	private final Action acaoBotaoCancelar = new AcaoBotaoCancelar();
	
	/**
	 * 
	 * @param args prmeiro agumento � a string com a url da pasta do projeto
	 */
	public static void main(String[] args) {
		try {
			if(args != null)
			{
				urlPastaDoProjeto = args[0];
				nomePastaDoProjeto = args[1];
				System.out.println("url pasta projeto:" + urlPastaDoProjeto);
			}
			else if(args == null)
			{
				System.out.println("erro n�o pegou pasta projeto, projeto nulo:");
			}
			//ele especifica no primeiro argumento do main a url da pasta do projeto
			SeletorPastasProjeto dialog = new SeletorPastasProjeto();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setTitle("Selecione os arquivos/pastas que quiser botar no PDF");
			dialog.setVisible(true);
			//no come�o, limpa os arquivos selecionados do singleton que os guarda
			SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().limparListaSelecionados();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	 private void createNodes(DefaultMutableTreeNode nohDaArvore) {
	        
	        Object objetoGuardadoNoNohDaArvore = nohDaArvore.getUserObject();
	        if(objetoGuardadoNoNohDaArvore instanceof PastaDoProjeto)
	        {
	        	PastaDoProjeto umaPastaDoProjeto = (PastaDoProjeto)  objetoGuardadoNoNohDaArvore;
	        	String urlPastaDoProjeto = umaPastaDoProjeto.getUrlDaPasta();
	        	if(urlPastaDoProjeto != null && urlPastaDoProjeto.length() > 0)
	        	{
	        		File pastaroot = new File(urlPastaDoProjeto);
	        		String[] nomesPasta = pastaroot.list();

	        		for(String nomeUmaPasta : nomesPasta)
	        		{
	        		    if (new File(urlPastaDoProjeto + "\\" + nomeUmaPasta).isDirectory())
	        		    {
	        		    	String urlUmaPasta = urlPastaDoProjeto + "\\" + nomeUmaPasta;
	        		    	PastaDoProjeto novaPastaPraArvore = new PastaDoProjeto(nomeUmaPasta, urlUmaPasta);
	        		    	DefaultMutableTreeNode novoNohDaArvore = new DefaultMutableTreeNode(novaPastaPraArvore);
	        		        nohDaArvore.add(novoNohDaArvore);
	        		        //e continua a busca por pasta de forma recursiva...
	        		        createNodes(novoNohDaArvore);
	        		    	
	        		    }else
	        		    {
	        		    	//chegamos em um arquivo
	        		    	String urlUmaArquivo = urlPastaDoProjeto + "\\" + nomeUmaPasta;
	        		    	ArquivoDoProjeto novoArquivoPraArvore = new ArquivoDoProjeto(nomeUmaPasta, urlUmaArquivo);
	        		    	DefaultMutableTreeNode novoNohDaArvore = new DefaultMutableTreeNode(novoArquivoPraArvore);
	        		        nohDaArvore.add(novoNohDaArvore);
	        		    	
	        		    }
	        		}
	        	}
	        }

	        

	       

	       
	    }

	/**
	 * Create the dialog.
	 */
	public SeletorPastasProjeto() {
		setBounds(100, 100, 450, 300);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{228, 33, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		{
			

		}
		{
			JScrollPane painelScrollPraArvore = new JScrollPane();
			GridBagConstraints gbc_painelScrollPraArvore = new GridBagConstraints();
			gbc_painelScrollPraArvore.insets = new Insets(0, 0, 5, 0);
			gbc_painelScrollPraArvore.fill = GridBagConstraints.BOTH;
			gbc_painelScrollPraArvore.gridx = 0;
			gbc_painelScrollPraArvore.gridy = 0;
			getContentPane().add(painelScrollPraArvore, gbc_painelScrollPraArvore);
			{
				JTree arvoreSelecionePastasProjeto = new JTree();
				painelScrollPraArvore.setViewportView(arvoreSelecionePastasProjeto);
				DefaultMutableTreeNode rootDoProjeto =
				        new DefaultMutableTreeNode(new PastaDoProjeto(nomePastaDoProjeto, urlPastaDoProjeto));
				createNodes(rootDoProjeto);
				DefaultTreeModel model = (DefaultTreeModel) arvoreSelecionePastasProjeto.getModel();
			    final TreeSelectionModel selectionModel = arvoreSelecionePastasProjeto.getSelectionModel();

			    selectionModel.setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
			    
			    //agora, fazer a �rvore virar arvore com checkbox:
			    // makes your tree as CheckTree
			    //primeiro, ser� que os checkboxes j� foram marcados?
			 
			    
			    checkTreeManager = new CheckTreeManager(arvoreSelecionePastasProjeto);
			    
			    
			    
			    
			    /* 	MUDAR ICONE DAS PASTAS
			     * ImageIcon iconePasta = CriadorImageIcon.createImageIcon("imagens/icone_pastinha.png");
			    if (iconePasta != null) {
			        DefaultTreeCellRenderer renderer = 
			            new DefaultTreeCellRenderer();
			        renderer.setLeafIcon(iconePasta);
			        renderer.setOpenIcon(iconePasta);
			        renderer.setIcon(iconePasta);
			        renderer.setClosedIcon(iconePasta);
			        arvoreSelecionePastasProjeto.setCellRenderer(renderer);
			    }*/
			    model.setRoot(rootDoProjeto);
				
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			GridBagConstraints gbc_buttonPane = new GridBagConstraints();
			gbc_buttonPane.anchor = GridBagConstraints.NORTH;
			gbc_buttonPane.fill = GridBagConstraints.HORIZONTAL;
			gbc_buttonPane.gridx = 0;
			gbc_buttonPane.gridy = 1;
			getContentPane().add(buttonPane, gbc_buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// to get the paths that were checked
						TreePath checkedPaths[] = checkTreeManager.getSelectionModel().getSelectionPaths();
						//guardar pra futuras referencias
						if(checkedPaths.length > 0)
						{
							//usuario selecionou pastas/arquivos do projeto e apertou ok
							SingletonGuardaProjetoPastasEArquivosSelecionados guardarPastasEArquivosSelecionados = SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance();
							for(int i = 0; i < checkedPaths.length; i++)
							{
								TreePath umCheckedPath = checkedPaths[i];
								Object objetoDoTreePath = umCheckedPath.getLastPathComponent();
								if(objetoDoTreePath instanceof DefaultMutableTreeNode)
								{
									DefaultMutableTreeNode umNohArvore = (DefaultMutableTreeNode) objetoDoTreePath;
									Object objetoDoNoh = umNohArvore.getUserObject();
									System.out.println("%objetoSelecionado:%%%%%" + objetoDoNoh.toString());
									//aqui esse objeto pode ser um ArquivoDoProjeto ou PastaDoProjeto. Vamos guard�-lo no singleton, n�o importando o que for
									guardarPastasEArquivosSelecionados.adicionarArquivoOuPastaSelecionado(objetoDoNoh);
									SeletorPastasProjeto.this.dispose();
								}
							}
						}
						else
						{
							JOptionPane.showMessageDialog(SeletorPastasProjeto.this, "Especifique ao menos um arquivo/pasta do projeto ou cancele para voltar.");
						}
						
					}
				});
				okButton.setAction(acaoBotaoOk);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setAction(acaoBotaoCancelar);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "OK");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class AcaoBotaoCancelar extends AbstractAction {
		public AcaoBotaoCancelar() {
			putValue(NAME, "Cancelar");
		}
		public void actionPerformed(ActionEvent e) {
			SeletorPastasProjeto.this.dispose();
		}
	}
}
