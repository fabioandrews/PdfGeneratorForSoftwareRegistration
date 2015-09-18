package br.ufrn.pairg.interfacegrafica;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

//singleton que cuida de todas as acoes referentes a barra de progresso da gui(aparece por exemplo ao clicar em avancado...)
public class SingletonBarraDeProgresso 
{
	private JProgressBar barraDeProgresso;
	private static SingletonBarraDeProgresso instance;
	private JLabel textoBarraDeProgresso;
	
	private SingletonBarraDeProgresso()
	{
		
	}

	public static SingletonBarraDeProgresso getInstance()
	{
		if(instance == null)
		{
			instance = new SingletonBarraDeProgresso();
		}
		
		return instance;
	}
	
	public void setBarraDeProgresso(JProgressBar barraDeProgresso)
	{
		this.barraDeProgresso = barraDeProgresso;
	}
	
	public void setTextoBarraDeProgresso(JLabel textoBarra)
	{
		this.textoBarraDeProgresso = textoBarra;
	}
	
	
	public void inicializarBarraDeProgresso(int valorMaximo, String textoBarra)
	{
		if(barraDeProgresso != null)
		{
			barraDeProgresso.setVisible(true);
			barraDeProgresso.setStringPainted(true);
			barraDeProgresso.setValue(0);
			this.barraDeProgresso.setMaximum(valorMaximo);
			this.barraDeProgresso.setMinimum(0);
			
			//this.textoBarraDeProgresso.setVisible(true);
			//this.telaPrincipal.setVisibletextoBarraDeProgresso();
			//this.textoBarraDeProgresso.setText(textoBarra);
			
			
			ThreadAlteraTextoBarraDeProgresso thread = new ThreadAlteraTextoBarraDeProgresso(textoBarraDeProgresso,textoBarra,true);
			thread.start();
			
			
		}
	}
	
	public void updateBarraDeProgresso(int novoValor)
	{
		if(barraDeProgresso != null)
		{
			this.barraDeProgresso.setValue(novoValor);
			this.barraDeProgresso.update(barraDeProgresso.getGraphics());
		}
	}
	
	public void tornarBarraDeProgressoInvisivel()
	{
		if(barraDeProgresso != null)
		{
			this.barraDeProgresso.setVisible(false);
			barraDeProgresso.setStringPainted(true);
			
			ThreadAlteraTextoBarraDeProgresso thread = new ThreadAlteraTextoBarraDeProgresso(textoBarraDeProgresso,"                                                                                                  ",true);
			thread.start();
			
			
			//this.textoBarraDeProgresso.setVisible(false);
		}
	}

	
}
