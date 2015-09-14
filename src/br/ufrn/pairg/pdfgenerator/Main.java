package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Main 
{
	 public static String FILE = "c:/temp/FirstPdf.pdf";
	 public static String FILE2 = "c:/temp/FirstPdfComNumeroDePaginas.pdf";
	private String urlArquivoComIdDentroDele; //esse objeto serah mudado na funcao arquivosDasExtensoesTemAlgumQueNaoVaiDarParaSaberNumeroDePaginas se existir algum arquivo assim
	
	//se algum arquivo tiver o mesmo id que serahgerado paraidentificar numero de paginas,
	//se alguma forma escrito em algum lugar, vai dar bug. Vamos checar isso e avisar ao usuario se ele quer mesmo fazer isso
	private boolean existeAgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginas(String urlPasta,LinkedList<String> extensoes)
	{
		this.urlArquivoComIdDentroDele = "";
		PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes pegaCaminhos = new PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes();
		LinkedList<String> caminhosDeArquivosQueIraoParaOPDF = 
				pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(urlPasta, extensoes);
		
		
		for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
		{
			String url = caminhosDeArquivosQueIraoParaOPDF.get(i);
			String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(url);
			if(arquivoLido.contains("#%&#" + "id_"))
			{
				this.urlArquivoComIdDentroDele = url;
				return true;
			}
		}
		
		return false;
	}
	
	public void gerarPDFParaRegistroDeSoftware(String urlPasta,LinkedList<String> extensoes, String nomeProjeto)
	{
		//primeiro, verificaremos se eh possivel gerar o pdf com o numero de paginas, pois
		/*depois que um pdf for gerado pela funcao gerarPDFDeStringVariosArquivos, devemos contar quantas paginas rendeu cada arquivo no pdf e recriar o pdf, mas dessa vez incluindo o numero de paginas junto ao nome do arquivo*/
		/*e a sao gerados ids (#%&#id...)emcima do nome do arquivo no pdf criado pela funcao gerarPDFDeStringVariosArquivos da classe GeraPDFDeStringVariosArquivos.
		  Caso algum dos arquivos das extensoes informadas possuir um #%&#, podemos nao conseguir gerar o numero de paginas*/
		boolean existeAgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginas = 
							existeAgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginas(urlPasta,extensoes);
		if(existeAgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginas == true)
		{
			//vamos dizer ao usuario q nao sera possivel colocar a quantidade de paginas
			int resposta = JOptionPane.showConfirmDialog(null, "Não será possível gerar a quantidade de páginas que cada arquivo tem no PDF por causa do arquivo \n" + this.urlArquivoComIdDentroDele + "\nMesmo assim deseja criar o PDF?", "Problemas com o projeto informado PDF",  JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION)
			{
			   //vamos criar o pdf mas sem o numero de paginas
				
				PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes pegaCaminhos = new PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes();
				LinkedList<String> caminhosDeArquivosQueIraoParaOPDF = 
						pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(urlPasta, extensoes);
				
				LinkedList<String> nomesArquivosLidos = new LinkedList<String>();
				LinkedList<String> textosArquivosLidos = new LinkedList<String>();
				File arquivoPdfGerar = new File(FILE);
				
				for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
				{
					String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
					String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
					String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeProjeto);
					nomesArquivosLidos.add(nomeArquivoLido);
					textosArquivosLidos.add(arquivoLido);
				}
				
				  
				GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
				geradorPdf.gerarPDFDeStringVariosArquivosSemNumeroDePaginas(textosArquivosLidos, nomesArquivosLidos,arquivoPdfGerar);
			}
			else
			{
				//nao acontece nada
			}
		}
		else
		{
			//arquivo gerado vai ter o numero de paginas
			PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes pegaCaminhos = new PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes();
			LinkedList<String> caminhosDeArquivosQueIraoParaOPDF = 
					pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(urlPasta, extensoes);
			
			LinkedList<String> nomesArquivosLidos = new LinkedList<String>();
			LinkedList<String> textosArquivosLidos = new LinkedList<String>();
			File arquivoPdfGerar = new File(FILE);
			File arquivopdfGerarComNumeroDePaginas = new File(Main.FILE2);
			
			for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
			{
				String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
				String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
				String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeProjeto);
				nomesArquivosLidos.add(nomeArquivoLido);
				textosArquivosLidos.add(arquivoLido);
			}
			  
			GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
			geradorPdf.gerarPDFDeStringVariosArquivosComNumeroDePaginas(textosArquivosLidos, nomesArquivosLidos,arquivoPdfGerar,arquivopdfGerarComNumeroDePaginas);
			arquivoPdfGerar.delete();
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Main main = new Main();
		LinkedList<String> extensoes = new LinkedList<String>();
		extensoes.add("java");
		String nomeProjeto = "PdfGeneratorForSoftwareRegistration";
		main.gerarPDFParaRegistroDeSoftware("C:/Users/fábioandrews/Documents/git/PdfGeneratorForSoftwareRegistration/PdfGeneratorForSoftwareRegistration",extensoes,nomeProjeto);

	}

}
