package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import br.ufrn.pairg.interfacegrafica.SingletonGuardaProjetoPastasEArquivosSelecionados;

public class Main 
{
	 public static String outputFILE = "c:/temp/FirstPdf.pdf";
	 public static String outputFILE2 = "c:/temp/FirstPdf.pdf";
	private String urlArquivoComIdDentroDele; //esse objeto serah mudado na funcao arquivosDasExtensoesTemAlgumQueNaoVaiDarParaSaberNumeroDePaginas se existir algum arquivo assim
	
	
	//se algum arquivo tiver o mesmo id que serahgerado paraidentificar numero de paginas,
	//se alguma forma escrito em algum lugar, vai dar bug. Vamos checar isso e avisar ao usuario se ele quer mesmo fazer isso
	//o usuario nao selecionou um diretorio do projeto. vamos pegar tudo do singleton
	private boolean existeAlgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginasUsuarioEscolheuArquivosEPastasNaoApenasDiretorioDoProjeto(LinkedList<String> extensoes)
	{
			this.urlArquivoComIdDentroDele = "";
			
			LinkedList<ArquivoDoProjeto> arquivosSelecionados = 
					SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getArquivosSelecionados();
			LinkedList<PastaDoProjeto> pastasSelecionadas =
					SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getPastasSelecionadas();
			
			GeraPDFDeStringVariosArquivos geraPdf = new GeraPDFDeStringVariosArquivos();
			String nomeDaFerramenta = geraPdf.getNomeDaFerramenta();
			
			//primeiro vou verificar os arquivos das pastas selecionadas
			for(int i = 0; i < pastasSelecionadas.size(); i++)
			{
				String umaUrlPasta = pastasSelecionadas.get(i).getUrlDaPasta();
				PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes pegaCaminhos = new PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes();
				LinkedList<String> caminhosDeArquivosQueIraoParaOPDF = 
						pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(umaUrlPasta, extensoes);
				for(int j = 0; j < caminhosDeArquivosQueIraoParaOPDF.size(); j++)
				{
					String umaUrlArquivo = caminhosDeArquivosQueIraoParaOPDF.get(j);
					String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umaUrlArquivo);
					
					if(arquivoLido.contains("&" + nomeDaFerramenta + "#id_") == true)
					{
						this.urlArquivoComIdDentroDele = umaUrlArquivo;
						return true;
					}
				}
			}
			
			//agora vamos verificar os arquivos selecionados(os que nao estao dentro de pastas)
			
			for(int k = 0; k < arquivosSelecionados.size(); k++)
			{
				String url = arquivosSelecionados.get(k).getUrlDoArquivo();
				String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(url);
				if(arquivoLido.contains("&" + nomeDaFerramenta + "#id_") == true)
				{
					this.urlArquivoComIdDentroDele = url;
					return true;
				}
			}
			
			//se n tinha arquivos com o id conflitante, vamos retornar false
			return false;
		}
	
	
	//A funcao abaixo cria o PDF com todo o codigo de todos os arquivos de um projeto java.
	//A funcao nao pode ser usada antes do SingletonGuardaProjetoPastasEArquivosSelecionados ter as pastas selecionadas, arquivos ou apenas o diretorio.
	//O usuario pode decidir tanto escolher apenas a pasta do projeto como os arquivos especificos e ate mesmo pastas especificas
	//Caso o arquivo apresente algum id ruim(#%&#id...), o software irah perguntar se o usuario quer gerar o pdf mesmo sem numero de paginas 
	public void gerarPDFParaRegistroDeSoftware(LinkedList<String> extensoes, String nomeDiretorioRaizDoProjeto, String versaoDoProjeto, String nomeDosAutoresSeparadosPorVirgula)
	{
		//primeiro, verificaremos se eh possivel gerar o pdf com o numero de paginas, pois
		/*depois que um pdf for gerado pela funcao gerarPDFDeStringVariosArquivos, devemos contar quantas paginas rendeu cada arquivo no pdf e recriar o pdf, mas dessa vez incluindo o numero de paginas junto ao nome do arquivo*/
		/*e a sao gerados ids (#%&#id...)emcima do nome do arquivo no pdf criado pela funcao gerarPDFDeStringVariosArquivos da classe GeraPDFDeStringVariosArquivos.
		  Caso algum dos arquivos das extensoes informadas possuir um #%&#, podemos nao conseguir gerar o numero de paginas*/
		
		//antes disso, faremos uma grande verificacao: o usuario escolheu apenas uma pasta do projeto ou foi escolhendo arquivos e pastas especificos?
		boolean usuarioSelecionouPastasEArquivosEspecificosProPdf = SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().usuarioSelecionouPastasEArquivosEspecificosProPdf();
		boolean existeAgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginas;
		
		if(usuarioSelecionouPastasEArquivosEspecificosProPdf == true)
		{
			//usuario escolheu os arquivos e pastas, nao soh o diretorio do projeto
			existeAgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginas =
					existeAlgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginasUsuarioEscolheuArquivosEPastasNaoApenasDiretorioDoProjeto(extensoes);
		}
		else
		{
			//usuario escolheu apenas diretorio do projeto
			String urlPasta = 
					SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getPastaDoProjeto().getAbsolutePath();
			existeAgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginas = 
					existeAlgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginasUsuarioEscolheuDiretorioDoProjeto(urlPasta,extensoes);
		}
		
		
		if(existeAgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginas == true)
		{
			//vamos dizer ao usuario q nao sera possivel colocar a quantidade de paginas
			int resposta = JOptionPane.showConfirmDialog(null, "Não será possível gerar a quantidade de páginas que cada arquivo tem no PDF por causa do arquivo \n" + this.urlArquivoComIdDentroDele + "\nMesmo assim deseja criar o PDF?", "Problemas com o projeto informado PDF",  JOptionPane.YES_NO_OPTION);
			if (resposta == JOptionPane.YES_OPTION)
			{
			   //vamos criar o pdf mas sem o numero de paginas
				
				if(usuarioSelecionouPastasEArquivosEspecificosProPdf == true)
				{
					//usuario selecionou arquivos e pastas do projeto
					//primeiro vamos sair pegando os caminhos para os arquivos das pastas selecionadas
					
					LinkedList<String> caminhosDeArquivosQueIraoParaOPDF = new LinkedList<String>(); //arquivos normais e arquivos dentro de pastas ficam aqui
					
					LinkedList<PastaDoProjeto> pastasSelecionadas = 
							SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getPastasSelecionadas();
					PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes pegaCaminhos =
							new PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes();
					for(int f = 0; f < pastasSelecionadas.size(); f++)
					{
						String umaUrlUmaPasta = pastasSelecionadas.get(f).getUrlDaPasta();
						LinkedList<String> caminhosDeArquivosDentroDePastasSelecionadas = 
								pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(umaUrlUmaPasta, extensoes);
						for(int g = 0; g < caminhosDeArquivosDentroDePastasSelecionadas.size(); g++)
						{
							String umCaminhoArquivoDentroPastasSelecionadas = caminhosDeArquivosDentroDePastasSelecionadas.get(g);
							caminhosDeArquivosQueIraoParaOPDF.add(umCaminhoArquivoDentroPastasSelecionadas);
						}
					}
					
					
					//agora vamos pegar soh o caminhos dos arquivos que foram selecionados
					LinkedList<ArquivoDoProjeto> arquivosSelecionados = 
							SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getArquivosSelecionados();
					for(int h = 0; h < arquivosSelecionados.size(); h++)
					{
						String umaUrlUmArquivoSelecionado = arquivosSelecionados.get(h).getUrlDoArquivo();
						caminhosDeArquivosQueIraoParaOPDF.add(umaUrlUmArquivoSelecionado);
					}
					
					//agora soh falta criar o pdf com base nos caminhosDeArquivosQueIraoParaOPDF
					LinkedList<String> nomesArquivosLidos = new LinkedList<String>();
					LinkedList<String> textosArquivosLidos = new LinkedList<String>();
					File arquivoPdfGerar = new File(outputFILE);
					
					for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
					{
						String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
						String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
						String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeDiretorioRaizDoProjeto);
						nomesArquivosLidos.add(nomeArquivoLido);
						textosArquivosLidos.add(arquivoLido);
					}
					
					  
					GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
					geradorPdf.gerarPDFDeStringVariosArquivosSemNumeroDePaginas(textosArquivosLidos, nomesArquivosLidos,arquivoPdfGerar,nomeDiretorioRaizDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula);
				}
				else
				{
					//usuario selecionou apenas a pasta do projeto
					
					String urlPasta = 
							SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getPastaDoProjeto().getAbsolutePath();
					
					PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes pegaCaminhos = new PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes();
					LinkedList<String> caminhosDeArquivosQueIraoParaOPDF = 
							pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(urlPasta, extensoes);
					
					LinkedList<String> nomesArquivosLidos = new LinkedList<String>();
					LinkedList<String> textosArquivosLidos = new LinkedList<String>();
					File arquivoPdfGerar = new File(outputFILE);
					
					for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
					{
						String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
						String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
						String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeDiretorioRaizDoProjeto);
						nomesArquivosLidos.add(nomeArquivoLido);
						textosArquivosLidos.add(arquivoLido);
					}
					
					  
					GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
					geradorPdf.gerarPDFDeStringVariosArquivosSemNumeroDePaginas(textosArquivosLidos, nomesArquivosLidos,arquivoPdfGerar,nomeDiretorioRaizDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula);
				}
				
			}
			else
			{
				//nao acontece nada
			}
		}
		else
		{
			//arquivo gerado vai ter o numero de paginas
			if(usuarioSelecionouPastasEArquivosEspecificosProPdf == true)
			{
				//usuario selecionou arquivos e pastas do projeto
				//primeiro vamos sair pegando os caminhos para os arquivos das pastas selecionadas
				
				LinkedList<String> caminhosDeArquivosQueIraoParaOPDF = new LinkedList<String>(); //arquivos normais e arquivos dentro de pastas ficam aqui
				
				LinkedList<PastaDoProjeto> pastasSelecionadas = 
						SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getPastasSelecionadas();
				PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes pegaCaminhos =
						new PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes();
				for(int f = 0; f < pastasSelecionadas.size(); f++)
				{
					String umaUrlUmaPasta = pastasSelecionadas.get(f).getUrlDaPasta();
					LinkedList<String> caminhosDeArquivosDentroDePastasSelecionadas = 
							pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(umaUrlUmaPasta, extensoes);
					for(int g = 0; g < caminhosDeArquivosDentroDePastasSelecionadas.size(); g++)
					{
						String umCaminhoArquivoDentroPastasSelecionadas = caminhosDeArquivosDentroDePastasSelecionadas.get(g);
						caminhosDeArquivosQueIraoParaOPDF.add(umCaminhoArquivoDentroPastasSelecionadas);
					}
				}
				
				
				//agora vamos pegar soh o caminhos dos arquivos que foram selecionados
				LinkedList<ArquivoDoProjeto> arquivosSelecionados = 
						SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getArquivosSelecionados();
				for(int h = 0; h < arquivosSelecionados.size(); h++)
				{
					String umaUrlUmArquivoSelecionado = arquivosSelecionados.get(h).getUrlDoArquivo();
					caminhosDeArquivosQueIraoParaOPDF.add(umaUrlUmArquivoSelecionado);
				}
				
				//agora soh falta criar o pdf com base nos caminhosDeArquivosQueIraoParaOPDF
				LinkedList<String> nomesArquivosLidos = new LinkedList<String>();
				LinkedList<String> textosArquivosLidos = new LinkedList<String>();
				File arquivoPdfGerar = new File(outputFILE);
				File arquivopdfGerarComNumeroDePaginas = new File(Main.outputFILE2);
				
				for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
				{
					String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
					String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
					String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeDiretorioRaizDoProjeto);
					nomesArquivosLidos.add(nomeArquivoLido);
					textosArquivosLidos.add(arquivoLido);
				}
				
				  
				GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
				geradorPdf.gerarPDFDeStringVariosArquivosComNumeroDePaginas(textosArquivosLidos, nomesArquivosLidos,arquivoPdfGerar,arquivopdfGerarComNumeroDePaginas,nomeDiretorioRaizDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula);
			}
			else
			{
				//usuario selecionou apenas a pasta do projeto
				
				String urlPasta = 
						SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getPastaDoProjeto().getAbsolutePath();
				
				PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes pegaCaminhos = new PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes();
				LinkedList<String> caminhosDeArquivosQueIraoParaOPDF = 
						pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(urlPasta, extensoes);
				
				LinkedList<String> nomesArquivosLidos = new LinkedList<String>();
				LinkedList<String> textosArquivosLidos = new LinkedList<String>();
				File arquivoPdfGerar = new File(outputFILE);
				File arquivopdfGerarComNumeroDePaginas = new File(Main.outputFILE2);
				
				for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
				{
					String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
					String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
					String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeDiretorioRaizDoProjeto);
					nomesArquivosLidos.add(nomeArquivoLido);
					textosArquivosLidos.add(arquivoLido);
				}
				
				  
				GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
				geradorPdf.gerarPDFDeStringVariosArquivosComNumeroDePaginas(textosArquivosLidos, nomesArquivosLidos,arquivoPdfGerar,arquivopdfGerarComNumeroDePaginas,nomeDiretorioRaizDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula);
			}
		}
	}
	
	
	
	public void gerarPDFParaRegistroDeSoftwareApenasComSelecaoDeDiretorio(String urlPasta,LinkedList<String> extensoes, String nomeDiretorioProjeto, String versaoDoProjeto, String nomeDosAutoresSeparadosPorVirgula)
	{
		//primeiro, verificaremos se eh possivel gerar o pdf com o numero de paginas, pois
		/*depois que um pdf for gerado pela funcao gerarPDFDeStringVariosArquivos, devemos contar quantas paginas rendeu cada arquivo no pdf e recriar o pdf, mas dessa vez incluindo o numero de paginas junto ao nome do arquivo*/
		/*e a sao gerados ids (#%&#id...)emcima do nome do arquivo no pdf criado pela funcao gerarPDFDeStringVariosArquivos da classe GeraPDFDeStringVariosArquivos.
		  Caso algum dos arquivos das extensoes informadas possuir um #%&#, podemos nao conseguir gerar o numero de paginas*/
		boolean existeAgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginas = 
							existeAlgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginasUsuarioEscolheuDiretorioDoProjeto(urlPasta,extensoes);
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
				File arquivoPdfGerar = new File(outputFILE);
				
				for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
				{
					String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
					String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
					String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeDiretorioProjeto);
					nomesArquivosLidos.add(nomeArquivoLido);
					textosArquivosLidos.add(arquivoLido);
				}
				
				  
				GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
				geradorPdf.gerarPDFDeStringVariosArquivosSemNumeroDePaginas(textosArquivosLidos, nomesArquivosLidos,arquivoPdfGerar,nomeDiretorioProjeto,versaoDoProjeto, nomeDosAutoresSeparadosPorVirgula);
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
			File arquivoPdfGerar = new File(outputFILE);
			File arquivopdfGerarComNumeroDePaginas = new File(Main.outputFILE2);
			
			for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
			{
				String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
				String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
				String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeDiretorioProjeto);
				nomesArquivosLidos.add(nomeArquivoLido);
				textosArquivosLidos.add(arquivoLido);
			}
			  
			GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
			geradorPdf.gerarPDFDeStringVariosArquivosComNumeroDePaginas(textosArquivosLidos, nomesArquivosLidos,arquivoPdfGerar,arquivopdfGerarComNumeroDePaginas,nomeDiretorioProjeto, versaoDoProjeto, nomeDosAutoresSeparadosPorVirgula);
			arquivoPdfGerar.delete();
		}
	}
	
	
	//se algum arquivo tiver o mesmo id que serahgerado paraidentificar numero de paginas,
	//se alguma forma escrito em algum lugar, vai dar bug. Vamos checar isso e avisar ao usuario se ele quer mesmo fazer isso
			private boolean existeAlgumArquivoDasExtensoesQueNaoVaiDarParaSaberNumeroDePaginasUsuarioEscolheuDiretorioDoProjeto(String urlPasta,LinkedList<String> extensoes)
			{
				this.urlArquivoComIdDentroDele = "";
				PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes pegaCaminhos = new PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes();
				LinkedList<String> caminhosDeArquivosQueIraoParaOPDF = 
						pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(urlPasta, extensoes);
				
				GeraPDFDeStringVariosArquivos geraPdf = new GeraPDFDeStringVariosArquivos();
				String nomeDaFerramenta = geraPdf.getNomeDaFerramenta();
				for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
				{
					String url = caminhosDeArquivosQueIraoParaOPDF.get(i);
					String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(url);
					if(arquivoLido.contains("&" + nomeDaFerramenta + "#id_") == true)
					{
						this.urlArquivoComIdDentroDele = url;
						return true;
					}
				}
				
				return false;
			}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Main main = new Main();
		LinkedList<String> extensoes = new LinkedList<String>();
		extensoes.add("java");
		extensoes.add("xml");
		String nomeProjeto = "KarutaKanji";
		String nomeDosAutoresSeparadosPorVirgula = "Fábio Phillip, Fábio Andrews";
		String versaoDoProjeto = "1.0";
		
		main.gerarPDFParaRegistroDeSoftwareApenasComSelecaoDeDiretorio("C:\\Users\\fábioandrews\\Desktop\\adt-bundle-windows-x86-20130717\\adt-bundle-windows-x86_64-20131030\\adt-bundle-windows-x86_64-20131030\\eclipse\\projetos\\KarutaKanji",extensoes,nomeProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula);
		
		

	}

}
