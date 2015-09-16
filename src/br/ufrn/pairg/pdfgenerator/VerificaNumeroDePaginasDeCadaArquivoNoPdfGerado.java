package br.ufrn.pairg.pdfgenerator;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;


/*depois que um pdf for gerado pela funcao gerarPDFDeStringVariosArquivos, devemos contar quantas paginas rendeu cada arquivo no pdf e recriar o pdf, mas dessa vez incluindo o numero de paginas junto ao nome do arquivo*/
/*a variavel idsArquivos referencia-se aos ids (#%&#id...) 
	gerados emcima do nome do arquivo no pdf criado pela funcao gerarPDFDeStringVariosArquivos da classe GeraPDFDeStringVariosArquivos*/
public class VerificaNumeroDePaginasDeCadaArquivoNoPdfGerado
{
	//urlArquivos eh tipo PdfGeneratorForSoftwareRegistration/pdf.java,c://java.java...
	public HashMap<String,Integer> pegarNumeroDePaginasNoPdfDeCadaArquivo(HashMap<String,String> urlsArquivosESeusIds, LinkedList<String> urlsArquivos, String urlOndeEstahOPdf)
	{
		HashMap<String,Integer> arquivosEQuantasPaginasElesTem = new HashMap<String, Integer>();
		
		//primeiro vamos pegar apenas os ids que precisamos checar no texto do pdf
		LinkedList<String> idsUrlsArquivos = new LinkedList<String>();
		for(int h = 0; h < urlsArquivos.size(); h++)
		{
			String umaUrl = urlsArquivos.get(h);
			String idDeUmaUrl = urlsArquivosESeusIds.get(umaUrl);
			idsUrlsArquivos.add(idDeUmaUrl);
		}
		
		
		
		try
		{
			PdfReader reader = new PdfReader(urlOndeEstahOPdf);
	        int quantasPaginasTemOPdf = reader.getNumberOfPages();
	        String urlArquivoAtual = ""; //a cada nome de arquivo que eu for encontrando no pdf, eu vou trocando essa variavel
	        int numeroPaginasurlArquivoAtual = 0; //vou aumentando a cada pagina que eu for achando do arquivo e paro quando achar o nome de outro arquivo ou quando acabar documento
	        
	        for(int i = 1; i <= quantasPaginasTemOPdf; i++)
	        {
	        	try 
	        	{
					String textoDaPagina = PdfTextExtractor.getTextFromPage(reader, i);
					
					String urlArquivoAchadaNaPagina = "";
					//primeiro vamos ver se conseguimos achar algum id de arquivo na pagina(nao url, soh id msm. Senao dava problema dele n achar pq o path era mt grande)
					for(int j = 0; j < idsUrlsArquivos.size(); j++)
					{
						String umIdUrlArquivo = idsUrlsArquivos.get(j);
						if(textoDaPagina.contains(umIdUrlArquivo))
						{
							//achamos um id. Vamos verificar de que url ele eh
							Iterator<String> iteraPorUrls = urlsArquivosESeusIds.keySet().iterator();
							String urlDoIdEncontrado = "";
							while(iteraPorUrls.hasNext())
							{
								String umaUrl = iteraPorUrls.next();
								String idUmaUrl = urlsArquivosESeusIds.get(umaUrl);
								if(umIdUrlArquivo.compareTo(idUmaUrl) == 0)
								{
									urlDoIdEncontrado = umaUrl;
									break;
								}
							}
							
							if(urlDoIdEncontrado.length() > 0)
							{
								//com certeza achamos o id de uma url e consequentemente uma url tb
								//System.out.println("achado id arquivo na pagina " + i);
								urlArquivoAchadaNaPagina = urlDoIdEncontrado;
								break;
							}
						}
					}
					
					if(urlArquivoAchadaNaPagina.length() > 0)
					{
						//achei o nome de outro arquivo. Se a quantidade de paginas de outro arquivo estava sendo formada, vamos colocar no hashmap
						if(urlArquivoAtual.length() > 0)
						{
							//estavamos pegando a quantidade de paginas de um arquivo anteriormente. Ele deve ir ao hashmap
							arquivosEQuantasPaginasElesTem.put(urlArquivoAtual, numeroPaginasurlArquivoAtual);
							urlArquivoAtual = urlArquivoAchadaNaPagina;
							numeroPaginasurlArquivoAtual = 1;
							//System.out.println(urlArquivoAtual + "++" + numeroPaginasurlArquivoAtual);
						}
						else
						{
							//nao tinha arquivos anteriormente, mas agora vai ter
							urlArquivoAtual = urlArquivoAchadaNaPagina;
							numeroPaginasurlArquivoAtual = 1;
							//System.out.println(urlArquivoAtual + "++" + numeroPaginasurlArquivoAtual);
						}
					}
					else
					{
						//nao achamos url de arquivo nenhum na pagina. Se ja existia um arquivo sendo verificado, aumentamos a quantidade de paginas dele
						if(urlArquivoAtual.length() > 0)
						{
							numeroPaginasurlArquivoAtual = numeroPaginasurlArquivoAtual + 1;
							//System.out.println(urlArquivoAtual + "++" + numeroPaginasurlArquivoAtual);
						}
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
	        }
	        
	        //faltou o ultimo arquivo(se tiver algum). Ele deve ser colocado no hasmap tb
	        if(urlArquivoAtual.length() > 0)
	        {
	        	//existia um ultimo arquivo
	        	arquivosEQuantasPaginasElesTem.put(urlArquivoAtual, numeroPaginasurlArquivoAtual);
	        }
	        
	        reader.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		
		return arquivosEQuantasPaginasElesTem;
		
	}
	
	
	public static void main(String args[])
	{
		VerificaNumeroDePaginasDeCadaArquivoNoPdfGerado verificador = new VerificaNumeroDePaginasDeCadaArquivoNoPdfGerado();
		LinkedList<String> urlsArquivos = new LinkedList<String>();
		HashMap<String,String> nomesDosArquivosLidosESeusIds = new HashMap<String, String>();
		String url0 = "PdfGeneratorForSoftwareRegistration/PdfGeneratorForSoftwareRegistration/src/br/ufrn/pairg/pdfgenerator/PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes.java";
		String url1 = "PdfGeneratorForSoftwareRegistration/PdfGeneratorForSoftwareRegistration/src/br/ufrn/pairg/pdfgenerator/LeitorArquivoTexto.java";
		urlsArquivos.add(url0);
		urlsArquivos.add(url1);
		GeraPDFDeStringVariosArquivos geraPDF = new GeraPDFDeStringVariosArquivos();
		nomesDosArquivosLidosESeusIds.put(url0,"&" + geraPDF.getNomeDaFerramenta() + "#id_" +  "0" + "%");
		nomesDosArquivosLidosESeusIds.put(url1,"&" + geraPDF.getNomeDaFerramenta() + "#id_" +  "1" + "%");
		String urlOndeEstahOPdf = "C:/Temp/FirstPdf.pdf";
		
		
		HashMap<String, Integer> arquivosEQuantasPaginasTem = 
				verificador.pegarNumeroDePaginasNoPdfDeCadaArquivo(nomesDosArquivosLidosESeusIds,urlsArquivos, urlOndeEstahOPdf);
		Iterator<String> iteraChaves = arquivosEQuantasPaginasTem.keySet().iterator();
		
		while(iteraChaves.hasNext() == true)
		{
			String umaChave = iteraChaves.next();
			int quantasPaginasArquivoTem = arquivosEQuantasPaginasTem.get(umaChave);
			
			System.out.println(umaChave + " tem " + quantasPaginasArquivoTem + " paginas");
		}
		
		if(arquivosEQuantasPaginasTem.keySet().isEmpty())
		{
			System.out.println("nao foi encontrado nenhum arquivo");
		}
		
		
	}

}
