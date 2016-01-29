package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;

//Essa classe vai ler e modificar e criar o arquivo .dat com as extensoes que devem ser consideradas no projeto e ja atualizar a lista de extensoes na gui
public class CriaeLeArquivoVersao 
{
	
	
	//serah que o arquivo ja existe ou nao?
	private boolean existeArquivoVersaoECriaPastaConfigSeNaoExistir()
	{
		//primeiro vamos checar se a pasta config existe. Se nao, vamos cria-la antes de criar o arquivo config
		boolean existePastaConfig = existePastaConfig();
		if(existePastaConfig == false)
		{
			File directory = new File(String.valueOf("config"));
			directory.mkdir();
			return false;
		}
		else
		{
			File f = new File("config/versao");
			if(f.exists() && !f.isDirectory()) 
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
	
	//sera que a pasta config existe ou nao?
	private boolean existePastaConfig()
	{
		File directory = new File(String.valueOf("config"));
		return directory.exists();
	}
	
	public void criarArquivoVersao()
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("config/versao", "UTF-8");
			writer.println("//versao//");
			writer.println("0.1.4");
			writer.println("//versao//");
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//caso n ache nenhuma versao, retorna a versao atual e ainda cria o arquivo novamente
		public String pegarVersaoNoArquivoVersao()
		{
			if(this.existeArquivoVersaoECriaPastaConfigSeNaoExistir() == false)
			{
				//o arquivo n existe ainda. Vamos criar!
				criarArquivoVersao();
			}
				
			String textoNoArquivoVersao = 
						LeitorArquivoTexto.lerArquivoQualquerDeTexto("config/versao");
			String textoNoArquivoConfiguracoesSemAcentos = Normalizer.normalize(textoNoArquivoVersao, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			String[] arquivoSeparadoPorVersao = textoNoArquivoConfiguracoesSemAcentos.split("//versao//");
			String versaoComEspacosEmBranco = arquivoSeparadoPorVersao[1];
			String versaoSemEspacosEmBranco = versaoComEspacosEmBranco.replaceAll("\\s+",""); //remove todos os espacos em branco
				
			return versaoSemEspacosEmBranco;
		}
	
	
	public static void main(String args[])
	{
		CriaeLeArquivoVersao gerenteTxt = new CriaeLeArquivoVersao();
		String versao = gerenteTxt.pegarVersaoNoArquivoVersao();
		System.out.println("Versão:" + versao);
		
	}
	
	
	
	
	
}
