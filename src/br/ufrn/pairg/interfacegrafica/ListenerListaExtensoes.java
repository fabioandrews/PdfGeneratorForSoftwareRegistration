package br.ufrn.pairg.interfacegrafica;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListenerListaExtensoes implements ListSelectionListener
{
	private JButton buttonRemoverExtensoes;
	private JList listaExtensoes; 
	
	public ListenerListaExtensoes(JButton buttonRemoverExtensoes,JList listaExtensoes)
	{
		this.buttonRemoverExtensoes = buttonRemoverExtensoes;
		this.listaExtensoes = listaExtensoes;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
	    if (e.getValueIsAdjusting() == false) {

	        if (listaExtensoes.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	            buttonRemoverExtensoes.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	            buttonRemoverExtensoes.setEnabled(true);
	        }
	    }
	}

}
