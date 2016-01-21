package br.ufrn.pairg.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import br.ufrn.pairg.pdfgenerator.Main;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class PopupSobreAFerramenta extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PopupSobreAFerramenta dialog = new PopupSobreAFerramenta();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setTitle("Sobre Code2inpi");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PopupSobreAFerramenta() 
	{
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setTitle("Sobre Code2inpi");
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 550, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			CriaeLeArquivoConfiguracoesdat conheceVersao = new CriaeLeArquivoConfiguracoesdat();
			String versao = conheceVersao.pegarVersaoNoTxtConfiguracoes();
			JLabel labelVersao = new JLabel("Vers\u00E3o: " + versao);
			GridBagConstraints gbc_labelVersao = new GridBagConstraints();
			gbc_labelVersao.insets = new Insets(0, 0, 5, 0);
			gbc_labelVersao.gridx = 2;
			gbc_labelVersao.gridy = 1;
			gbc_labelVersao.gridheight = 1;
			gbc_labelVersao.gridwidth = 1;
			contentPanel.add(labelVersao, gbc_labelVersao);
		}
		{
			JLabel labelUrl = new JLabel("www.code2inpi.pairg.dimap.ufrn.br/");
			GridBagConstraints gbc_labelUrl = new GridBagConstraints();
			gbc_labelUrl.insets = new Insets(0, 0, 5, 0);
			gbc_labelUrl.gridx = 1;
			gbc_labelUrl.gridy = 3;
			gbc_labelUrl.gridheight = 1;
			gbc_labelUrl.gridwidth = 2;
			contentPanel.add(labelUrl, gbc_labelUrl);
		}
		{
			URL urlImagemAbout = Main.class.getResource(
                    "/resources/about.png");
			Icon icon = new ImageIcon(urlImagemAbout);
			JLabel labelImagemSponsors = new JLabel(icon);
			GridBagConstraints gbc_labelImagemSponsors = new GridBagConstraints();
			gbc_labelImagemSponsors.insets = new Insets(0, 0, 0, 5);
			gbc_labelImagemSponsors.gridx = 1;
			gbc_labelImagemSponsors.gridy = 4;
			gbc_labelImagemSponsors.gridheight = 2;
			gbc_labelImagemSponsors.gridwidth = 2;
			contentPanel.add(labelImagemSponsors, gbc_labelImagemSponsors);
		}
		{
			GridBagConstraints gbc_labelButtonPane = new GridBagConstraints();
			gbc_labelButtonPane.insets = new Insets(0, 0, 0, 5);
			gbc_labelButtonPane.gridx = 1;
			gbc_labelButtonPane.gridy = 6;
			gbc_labelButtonPane.gridheight = 1;
			gbc_labelButtonPane.gridwidth = 2;
			JButton okButton = new JButton("OK");
			okButton.setActionCommand("OK");
			okButton.addActionListener(this);
			getRootPane().setDefaultButton(okButton);
			contentPanel.add(okButton, gbc_labelButtonPane);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
	    if ("OK".equals(e.getActionCommand())) 
	    {
	    	this.dispose();
	    }
	} 

}
