package br.ufrn.pairg.interfacegrafica;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListenerListaTiposDePrograma implements ListSelectionListener
{
	private JButton buttonRemoveTipoDePograma;
	private JList listaTiposDePrograma; 
	
	public ListenerListaTiposDePrograma(JButton buttonRemoveTipoDePograma,JList listaTiposDePrograma)
	{
		this.buttonRemoveTipoDePograma = buttonRemoveTipoDePograma;
		this.listaTiposDePrograma = listaTiposDePrograma;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
	    if (e.getValueIsAdjusting() == false) {

	        if (listaTiposDePrograma.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	        	buttonRemoveTipoDePograma.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	        	buttonRemoveTipoDePograma.setEnabled(true);
	        }
	    }
	}

}
