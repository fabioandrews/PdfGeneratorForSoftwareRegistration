package br.ufrn.pairg.interfacegrafica;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListenerListaTiposDeAplicacao implements ListSelectionListener
{
	private JButton buttonRemoveTipoDeAplicacao;
	private JList listaTiposDeAplicacao; 
	
	public ListenerListaTiposDeAplicacao(JButton buttonRemoveTipoDeAplicacao,JList listaTiposDeAplicacao)
	{
		this.buttonRemoveTipoDeAplicacao = buttonRemoveTipoDeAplicacao;
		this.listaTiposDeAplicacao = listaTiposDeAplicacao;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) 
	{
	    if (e.getValueIsAdjusting() == false) {

	        if (listaTiposDeAplicacao.getSelectedIndex() == -1) {
	        //No selection, disable fire button.
	        	buttonRemoveTipoDeAplicacao.setEnabled(false);

	        } else {
	        //Selection, enable the fire button.
	        	buttonRemoveTipoDeAplicacao.setEnabled(true);
	        }
	    }
	}

}
