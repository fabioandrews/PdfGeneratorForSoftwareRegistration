package br.ufrn.pairg.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JList;

import java.awt.GridBagConstraints;

import javax.swing.JButton;

import java.awt.Insets;

import javax.swing.JTextField;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.awt.Label;

public class TesteGuiListaExtensoes extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField textFieldAdicionarExtensoes;
	private JList<String> listaExtensoes;
	private JButton buttonAdicionarExtensoes;
	private JButton buttonRemoverExtensoes;
	private LinkedList<String> extensoes;
	
	private DefaultListModel<String> listModel;
	private Label label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TesteGuiListaExtensoes frame = new TesteGuiListaExtensoes();
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
	public TesteGuiListaExtensoes() 
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 383);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 49, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 257, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		label = new Label("Extens\u00F5es");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.insets = new Insets(0, 0, 0, 0);
		gbc_label.gridx = 6;
		gbc_label.gridy = 1;
		contentPane.add(label, gbc_label);
		
		
		textFieldAdicionarExtensoes = new JTextField();
		GridBagConstraints gbc_textFieldAdicionarExtensoes = new GridBagConstraints();
		gbc_textFieldAdicionarExtensoes.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldAdicionarExtensoes.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdicionarExtensoes.gridx = 6;
		gbc_textFieldAdicionarExtensoes.gridy = 5;
		contentPane.add(textFieldAdicionarExtensoes, gbc_textFieldAdicionarExtensoes);
		textFieldAdicionarExtensoes.setColumns(10);
		
		buttonAdicionarExtensoes = new JButton("+");
		GridBagConstraints gbc_buttonAdicionarExtensoes = new GridBagConstraints();
		gbc_buttonAdicionarExtensoes.insets = new Insets(0, 0, 0, 5);
		gbc_buttonAdicionarExtensoes.gridx = 7;
		gbc_buttonAdicionarExtensoes.gridy = 5;
		contentPane.add(buttonAdicionarExtensoes, gbc_buttonAdicionarExtensoes);
		buttonAdicionarExtensoes.addActionListener(this);
		
		buttonRemoverExtensoes = new JButton("-");
		GridBagConstraints gbc_buttonRemoverExtensoes = new GridBagConstraints();
		gbc_buttonRemoverExtensoes.insets = new Insets(0, 0, 5, 5);
		gbc_buttonRemoverExtensoes.gridx = 9;
		gbc_buttonRemoverExtensoes.gridy = 2;
		contentPane.add(buttonRemoverExtensoes, gbc_buttonRemoverExtensoes);
		buttonRemoverExtensoes.addActionListener(this);
		
		this.listModel = new DefaultListModel<String>();
		this.listaExtensoes = new JList<String>(listModel);
		
		GridBagConstraints gbc_listaExtensoes = new GridBagConstraints();
		gbc_listaExtensoes.gridheight = 3;
		gbc_listaExtensoes.gridwidth = 4;
		gbc_listaExtensoes.insets = new Insets(0, 0, 0, 5);
		gbc_listaExtensoes.gridx = 6;
		gbc_listaExtensoes.gridy = 2;
		listaExtensoes.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listaExtensoes.setLayoutOrientation(JList.VERTICAL_WRAP);
		listaExtensoes.setVisibleRowCount(-1);
		JScrollPane scrollPane = new JScrollPane(listaExtensoes);
		scrollPane.setPreferredSize(new Dimension(250, 80));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ListSelectionModel listSelectionModel = listaExtensoes.getSelectionModel();
	    listSelectionModel.addListSelectionListener(
	                            new ListenerListaExtensoes(buttonRemoverExtensoes,listaExtensoes));
		
	    contentPane.add(scrollPane, gbc_listaExtensoes);
	    extensoes= new LinkedList<String>();
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.buttonRemoverExtensoes)
		{
			int index = listaExtensoes.getSelectedIndex();
		    this.listModel.remove(index);

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

}
