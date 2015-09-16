package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.LinkedList;

//Essa classe vai ler e modificar e criar o arquivo .txt com as extensoes que devem ser consideradas no projeto e ja atualizar a lista de extensoes na gui
public class CriaeLeTxtComExtensoes 
{
	//caso n ache nenhuma extensao, retorna lista vazia e ainda cria o arquivo txt novamente
	public LinkedList<String> pegarExtensoesNoTxtExtensoes()
	{
		if(this.existeArquivoExtensoesTxt() == false)
		{
			//o arquivo n existe ainda. Vamos criar!
			criarArquivoExtensoesTxt();
			return new LinkedList<String>();
		}
		else
		{
			
			String textoNoArquivoExtensoes = 
					LeitorArquivoTexto.lerArquivoQualquerDeTexto("extensoes.txt");
			String textoNoArquivoExtensoesSemAcentos = Normalizer.normalize(textoNoArquivoExtensoes, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			String textoNoArquivoExtensoesComExtensoeasEEspacos = textoNoArquivoExtensoesSemAcentos.replace("i//Coloque as extensAes separadas por vArgula aqui neste arquivo//", "");
			String textoNoArquivoExtensoesComExtensoeasEEspacos2 = textoNoArquivoExtensoesComExtensoeasEEspacos.replace("//Coloque as extensAes separadas por vArgula aqui neste arquivo//", "");
			
			
			String extensoesSeparadasPorVirgula = textoNoArquivoExtensoesComExtensoeasEEspacos2.replaceAll("\\s+",""); //remove todos os espacos em branco
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
	private boolean existeArquivoExtensoesTxt()
	{
		File f = new File("extensoes.txt");
		if(f.exists() && !f.isDirectory()) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void criarArquivoExtensoesTxt()
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("extensoes.txt", "UTF-8");
			writer.println("//Coloque as extensões separadas por vírgula aqui neste arquivo//");
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
	public void criarArquivoExtensoesTxt(LinkedList<String> extensoes)
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
			writer = new PrintWriter("extensoes.txt", "UTF-8");
			writer.println("//Coloque as extensões separadas por vírgula aqui neste arquivo//");
			writer.println(extensoesSeparadasPorVirgula);
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String args[])
	{
		CriaeLeTxtComExtensoes gerenteTxt = new CriaeLeTxtComExtensoes();
		LinkedList<String> extensoes = gerenteTxt.pegarExtensoesNoTxtExtensoes();
		
		
		if(extensoes.size() == 0)
		{
			System.out.println("nao ha extensoes no arquivo txt");
		}
		
		for(int i = 0; i < extensoes.size(); i++)
		{
			System.out.println(extensoes.get(i));
		}
	}
	
	
	
}
