package br.ufrn.pairg.interfacegrafica;

import java.io.File;
import java.util.LinkedList;

import br.ufrn.pairg.pdfgenerator.ArquivoDoProjeto;
import br.ufrn.pairg.pdfgenerator.PastaDoProjeto;

public class SingletonGuardaProjetoPastasEArquivosSelecionados {
	
	private static SingletonGuardaProjetoPastasEArquivosSelecionados instancia;
	private File pastaDoProjeto;
	private LinkedList<PastaDoProjeto> pastasSelecionadas;
	private LinkedList<ArquivoDoProjeto> arquivosSelecionados;

	private SingletonGuardaProjetoPastasEArquivosSelecionados()
	{
		this.pastasSelecionadas = new LinkedList<PastaDoProjeto>();
		this.arquivosSelecionados = new LinkedList<ArquivoDoProjeto>();
	}
	
	public static SingletonGuardaProjetoPastasEArquivosSelecionados getInstance()
	{
		if(instancia == null)
		{
			instancia = new SingletonGuardaProjetoPastasEArquivosSelecionados();
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

	public File getPastaDoProjeto() {
		return pastaDoProjeto;
	}

	public void setPastaDoProjeto(File pastaDoProjeto) {
		this.pastaDoProjeto = pastaDoProjeto;
	}
	/**
	 * 
	 * @return string com url do projeto ou "" caso não tenha sido definida a pasta do projeto
	 */
	public String getSohUrlPastaDoProjeto()
	{
		if(this.pastaDoProjeto != null)
		{
			return pastaDoProjeto.getPath();
		}
		else
		{
			return "";
		}
	}

	

}
