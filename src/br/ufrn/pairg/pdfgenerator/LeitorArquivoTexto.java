package br.ufrn.pairg.pdfgenerator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorArquivoTexto {
	
	/**
	 * lê um arquivo de texto da URL especificada.
	 * @param URL exemplo "C:\\testing.txt"
	 * @return string com o texto do arquivo lido ou null se der exceção 
	 */
	public  static String lerArquivoQualquerDeTexto(String URL) {
		String textoLido = "";
		try (BufferedReader br = new BufferedReader(new FileReader(URL)))
		{

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) 
			{
				textoLido = textoLido + sCurrentLine + System.lineSeparator();
			}
			return textoLido;

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} 


	}
	
	/**
	 * chamar esse metodo para saber o nome do arquivo para botar no titulo do  pdf
	 * @param urlArquivo url do arquivo para ler
	 * @param nomePastaPrincipal nome da pasta principal do projeto, para extrair o nome sem toda a url
	 * @return
	 */
	public static String pegarNomeArquivo(String urlArquivo, String nomePastaPrincipal)
	{
		String nomeDoArquivo = "";
		String leitorUrl = "";
		int indicePercorreUrl = 0;
		boolean podePararLerUrl = false;
		
		while(indicePercorreUrl < urlArquivo.length() && podePararLerUrl == false)
		{
			if(urlArquivo.charAt(indicePercorreUrl) == '\\' || urlArquivo.charAt(indicePercorreUrl) == '/' )
			{
				if(leitorUrl.compareToIgnoreCase(nomePastaPrincipal) == 0)
				{
					//achamos o nome do arquivo! é o que resta dessa busca!
					nomeDoArquivo = leitorUrl + urlArquivo.substring(indicePercorreUrl);
					podePararLerUrl = true;
				}
				else
				{
					leitorUrl = "";
					indicePercorreUrl = indicePercorreUrl + 1;
				}
			}
			else
			{
				leitorUrl = leitorUrl + urlArquivo.charAt(indicePercorreUrl);
				indicePercorreUrl = indicePercorreUrl + 1;
			}
		}
		
		return nomeDoArquivo;
		
	}
	

}
