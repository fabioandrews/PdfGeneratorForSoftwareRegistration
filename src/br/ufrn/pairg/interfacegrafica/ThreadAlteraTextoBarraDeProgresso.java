package br.ufrn.pairg.interfacegrafica;

import javax.swing.JLabel;

public class ThreadAlteraTextoBarraDeProgresso extends Thread
{

	private JLabel textoBarraDeProgresso;
	private String textoParaBarraProgresso;
	private boolean textoFicarahVisivel;
	
	public ThreadAlteraTextoBarraDeProgresso(JLabel textoBarraDeProgresso,String textoParaBarraProgresso, boolean textoFicarahVisivel)
	{
		this.textoBarraDeProgresso = textoBarraDeProgresso;
		this.textoParaBarraProgresso = textoParaBarraProgresso;
		this.textoFicarahVisivel = textoFicarahVisivel;
	}
	@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		this.textoBarraDeProgresso.setText(textoParaBarraProgresso);
		this.textoBarraDeProgresso.paintImmediately(this.textoBarraDeProgresso.getVisibleRect());
		this.textoBarraDeProgresso.setVisible(textoFicarahVisivel);
		//this.textoBarraDeProgresso.update(textoBarraDeProgresso.getGraphics());
		//this.textoBarraDeProgresso.paint(textoBarraDeProgresso.getGraphics());
		//this.telaPrincipal.paint(telaPrincipal.getGraphics());
		//this.telaPrincipal.revalidate();
		
	}

}
