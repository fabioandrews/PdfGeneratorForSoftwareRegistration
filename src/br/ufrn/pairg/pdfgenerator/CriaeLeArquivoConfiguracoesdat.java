package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.LinkedList;

//Essa classe vai ler e modificar e criar o arquivo .dat com as extensoes que devem ser consideradas no projeto e ja atualizar a lista de extensoes na gui
public class CriaeLeArquivoConfiguracoesdat 
{
	//caso n ache nenhuma extensao, retorna lista vazia e ainda cria o arquivo txt novamente
	public LinkedList<String> pegarExtensoesNoTxtExtensoes()
	{
		if(this.existeArquivoConfiguracoesdat() == false)
		{
			//o arquivo n existe ainda. Vamos criar!
			criarArquivoConfiguracoesdat();
			return new LinkedList<String>();
		}
		else
		{
			
			String textoNoArquivoExtensoes = 
					LeitorArquivoTexto.lerArquivoQualquerDeTexto("configuracoes.dat");
			String textoNoArquivoExtensoesSemAcentos = Normalizer.normalize(textoNoArquivoExtensoes, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			String[] arquivoSeparadoPorExtensoes = textoNoArquivoExtensoesSemAcentos.split("//extensoes//");
			String extensoesSeparadasPorVirgulaComEspacosEmBranco = arquivoSeparadoPorExtensoes[1];
			String extensoesSeparadasPorVirgula = extensoesSeparadasPorVirgulaComEspacosEmBranco.replaceAll("\\s+",""); //remove todos os espacos em branco
			String[] arrayExtensoesDoArquivo = extensoesSeparadasPorVirgula.split(",");
			
			LinkedList<String> extensoesDoArquivo = new LinkedList<String>();
			for(int i = 0; i < arrayExtensoesDoArquivo.length; i++)
			{
				if(arrayExtensoesDoArquivo[i].length() > 0)
				{
					String umaExtensao = arrayExtensoesDoArquivo[i];
					if(umaExtensao.contains(".") == true)
					{
						//se o usuario colocu a extensao com ponto eu ja vou tirando
						String umaExtensaoSemPontos = umaExtensao.replaceAll("\\.", ""); 
						extensoesDoArquivo.add(umaExtensaoSemPontos);
					}
					else
					{
						extensoesDoArquivo.add(umaExtensao);
					}
				}
			}
			
			return extensoesDoArquivo;
		}
	}
	
	
	//serah que o arquivo ja existe ou nao?
	private boolean existeArquivoConfiguracoesdat()
	{
		File f = new File("configuracoes.dat");
		if(f.exists() && !f.isDirectory()) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void criarArquivoConfiguracoesdat()
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("configuracoes.dat", "UTF-8");
			writer.println("//extensoes//");
			writer.println("");
			writer.println("//extensoes//");
			writer.println("//versao//");
			writer.println("0.1.3");
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
	
	//cria o arquivo e jah bota as extensoes
	public void criarArquivoConfiguracoesdat(LinkedList<String> extensoes)
	{
		//primeiro vamos transformar a linkedlist de extensoes em string com virgulas
		String extensoesSeparadasPorVirgula = "";
		for(int i = 0; i < extensoes.size(); i++)
		{
			extensoesSeparadasPorVirgula = extensoesSeparadasPorVirgula + extensoes.get(i);
			if(i != extensoes.size() - 1)
			{
				extensoesSeparadasPorVirgula = extensoesSeparadasPorVirgula + ",";
			}
			
		}
		
		PrintWriter writer;
		try {
			writer = new PrintWriter("configuracoes.dat", "UTF-8");
			writer.println("//extensoes//");
			writer.println(extensoesSeparadasPorVirgula);
			writer.println("//extensoes//");
			writer.println("//versao//");
			writer.println("0.1.3");
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
		public String pegarVersaoNoTxtConfiguracoes()
		{
			if(this.existeArquivoConfiguracoesdat() == false)
			{
				//o arquivo n existe ainda. Vamos criar!
				criarArquivoConfiguracoesdat();
				return "0.1.3";
			}
			else
			{
				
				String textoNoArquivoConfiguracoes = 
						LeitorArquivoTexto.lerArquivoQualquerDeTexto("configuracoes.dat");
				String textoNoArquivoConfiguracoesSemAcentos = Normalizer.normalize(textoNoArquivoConfiguracoes, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
				String[] arquivoSeparadoPorVersao = textoNoArquivoConfiguracoesSemAcentos.split("//versao//");
				String versaoComEspacosEmBranco = arquivoSeparadoPorVersao[1];
				String versaoSemEspacosEmBranco = versaoComEspacosEmBranco.replaceAll("\\s+",""); //remove todos os espacos em branco
				
				return versaoSemEspacosEmBranco;
			}
		}
	
	
	public static void main(String args[])
	{
		CriaeLeArquivoConfiguracoesdat gerenteTxt = new CriaeLeArquivoConfiguracoesdat();
		LinkedList<String> extensoes = gerenteTxt.pegarExtensoesNoTxtExtensoes();
		
		
		if(extensoes.size() == 0)
		{
			System.out.println("nao ha extensoes no arquivo dat");
		}
		
		for(int i = 0; i < extensoes.size(); i++)
		{
			System.out.println(extensoes.get(i));
		}
	}
	
	
	
	
	
}
