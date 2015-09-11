package br.ufrn.pairg.interfacegrafica;

import java.util.LinkedList;

import br.ufrn.pairg.pdfgenerator.ArquivoDoProjeto;
import br.ufrn.pairg.pdfgenerator.PastaDoProjeto;

public class SingletonGuardaPastasEArquivosSelecionados {
	
	private static SingletonGuardaPastasEArquivosSelecionados instancia;
	private LinkedList<PastaDoProjeto> pastasSelecionadas;
	private LinkedList<ArquivoDoProjeto> arquivosSelecionados;

	private SingletonGuardaPastasEArquivosSelecionados()
	{
		this.pastasSelecionadas = new LinkedList<PastaDoProjeto>();
		this.arquivosSelecionados = new LinkedList<ArquivoDoProjeto>();
	}
	
	public static SingletonGuardaPastasEArquivosSelecionados getInstance()
	{
		if(instancia == null)
		{
			instancia = new SingletonGuardaPastasEArquivosSelecionados();
		}
		
		return instancia;
	}
	
	public void limparListaSelecionados()
	{
		this.arquivosSelecionados.clear();
		this.pastasSelecionadas.clear();
	}
	
	public void adicionarArquivoOuPastaSelecionado(Object arquivoOuPastaSelecionado)
	{
		if(arquivoOuPastaSelecionado instanceof PastaDoProjeto)
		{
			PastaDoProjeto pastaSelecionada = (PastaDoProjeto) arquivoOuPastaSelecionado;
			this.pastasSelecionadas.add(pastaSelecionada);
		}
		else if(arquivoOuPastaSelecionado instanceof ArquivoDoProjeto)
		{
			ArquivoDoProjeto arquivoSelecionado = (ArquivoDoProjeto) arquivoOuPastaSelecionado;
			this.arquivosSelecionados.add(arquivoSelecionado);
		}
		
	}

}
