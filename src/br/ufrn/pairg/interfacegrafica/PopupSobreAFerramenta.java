package br.ufrn.pairg.interfacegrafica;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import com.sun.org.apache.xerces.internal.util.URI;

import br.ufrn.pairg.pdfgenerator.CriaeLeArquivoConfiguracoesdat;
import br.ufrn.pairg.pdfgenerator.CriaeLeArquivoVersao;
import br.ufrn.pairg.pdfgenerator.Main;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
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
			dialog.setTitle("Sobre code2inpi");
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
		this.setTitle("Sobre code2inpi");
		this.setLocationRelativeTo(null);
		setBounds(100, 100, 550, 220);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0};
		gbl_contentPanel.rowHeights = new int[]{1, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0};
		gbl_contentPanel.rowWeights = new double[]{Double.MIN_VALUE, 0.0, 0.0, 0.0, 0.0, 0.0, 0,0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			
			
			
			JLabel labelCode2inpi = new JLabel("code2inpi");
			Font font = labelCode2inpi.getFont();
			// same font but bold
			Font boldFont = new Font(font.getFontName(), Font.BOLD, font.getSize() + 2);
			labelCode2inpi.setFont(boldFont);
			GridBagConstraints gbc_labelCode2inpi = new GridBagConstraints();
			gbc_labelCode2inpi.insets = new Insets(0, 0, 0, 0);
			gbc_labelCode2inpi.gridx = 1;
			gbc_labelCode2inpi.gridy = 1;
			gbc_labelCode2inpi.gridheight = 1;
			gbc_labelCode2inpi.gridwidth = 2;
			contentPanel.add(labelCode2inpi, gbc_labelCode2inpi);
		}
		{
			CriaeLeArquivoVersao conheceVersao = new CriaeLeArquivoVersao();
			String versao = conheceVersao.pegarVersaoNoArquivoVersao();
			JLabel labelVersao = new JLabel("Vers\u00E3o: " + versao);
			GridBagConstraints gbc_labelVersao = new GridBagConstraints();
			gbc_labelVersao.insets = new Insets(0, 0, 5, 0);
			gbc_labelVersao.gridx = 1;
			gbc_labelVersao.gridy = 2;
			gbc_labelVersao.gridheight = 1;
			gbc_labelVersao.gridwidth = 2;
			contentPanel.add(labelVersao, gbc_labelVersao);
			
			JLabel labelURL = new JLabel("www.code2inpi.pairg.ufrn.br");
			GridBagConstraints gbc_labelURL = new GridBagConstraints();
			gbc_labelURL.insets = new Insets(0, 0, 0, 0);
			gbc_labelURL.gridx = 1;
			gbc_labelURL.gridy = 4;
			gbc_labelURL.gridheight = 1;
			gbc_labelURL.gridwidth = 2;
			contentPanel.add(labelURL, gbc_labelURL);
			
			
		}
		{
			URL urlImagemLogoUfrn = Main.class.getResource(
                    "/resources/logoufrn.png");
			Icon icon = new ImageIcon(urlImagemLogoUfrn);
			final JLabel labelImagemLogoUFRN = new JLabel(icon);
			GridBagConstraints gbc_labelImagemLogoUFRN = new GridBagConstraints();
			gbc_labelImagemLogoUFRN.insets = new Insets(0, 0, 0, 5);
			gbc_labelImagemLogoUFRN.gridx = 1;
			gbc_labelImagemLogoUFRN.gridy = 5;
			gbc_labelImagemLogoUFRN.gridheight = 2;
			gbc_labelImagemLogoUFRN.gridwidth = 1;
			contentPanel.add(labelImagemLogoUFRN, gbc_labelImagemLogoUFRN);
			
			
			labelImagemLogoUFRN.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) 
				{
					try {
						openWebpage(new URL("http://www.ufrn.br/"));
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
			
			
			
			URL urlImagemLogoPairg = Main.class.getResource(
                    "/resources/logopairg.png");
			Icon iconePairg = new ImageIcon(urlImagemLogoPairg);
			JLabel labelImagemLogoPairg = new JLabel(iconePairg);
			GridBagConstraints gbc_labelImagemSponsors = new GridBagConstraints();
			gbc_labelImagemSponsors.insets = new Insets(0, 0, 0, 5);
			gbc_labelImagemSponsors.gridx = 2;
			gbc_labelImagemSponsors.gridy = 5;
			gbc_labelImagemSponsors.gridheight = 2;
			gbc_labelImagemSponsors.gridwidth = 1;
			contentPanel.add(labelImagemLogoPairg, gbc_labelImagemSponsors);
			
			labelImagemLogoPairg.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						openWebpage(new URL("http://www.pairg.dimap.ufrn.br/"));
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			});
		}
		{
			GridBagConstraints gbc_labelButtonPane = new GridBagConstraints();
			gbc_labelButtonPane.insets = new Insets(0, 0, 10, 5);
			gbc_labelButtonPane.gridx = 1;
			gbc_labelButtonPane.gridy = 7;
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
	
	public static void openWebpage(java.net.URI uri) {
	    Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
	    if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
	        try {
	            desktop.browse(uri);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

	public static void openWebpage(URL url) {
	    try {
	        openWebpage(url.toURI());
	    } catch (URISyntaxException e) {
	        e.printStackTrace();
	    }
	}

}
