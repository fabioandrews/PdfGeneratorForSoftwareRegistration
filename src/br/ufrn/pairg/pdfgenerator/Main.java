package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;

import javax.print.attribute.HashAttributeSet;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import org.apache.commons.io.FilenameUtils;

import br.ufrn.pairg.interfacegrafica.SingletonBarraDeProgresso;
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
			
			//vamos criar a barrinha de progresso para a etapa 1.1: verificar se algum arquivo tem extensao ruim nas pastas
			String textoBarraDeProgresso = "Verificando arquivos...";
			SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(pastasSelecionadas.size() - 1,textoBarraDeProgresso);
			
			
			//primeiro vou verificar os arquivos das pastas selecionadas
			for(int i = 0; i < pastasSelecionadas.size(); i++)
			{
				SingletonBarraDeProgresso.getInstance().updateBarraDeProgresso(i);
				
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
						SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
						return true;
					}
				}
			}
			
			SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
			
			//agora vamos verificar os arquivos selecionados(os que nao estao dentro de pastas)
			//vamos criar a barrinha de progresso para a etapa 1.2: verificar se algum arquivo tem extensao ruim fora das pastas
			String textoBarraDeProgresso2 = "Verificando arquivos...";
			SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(arquivosSelecionados.size() - 1,textoBarraDeProgresso2);
			
			for(int k = 0; k < arquivosSelecionados.size(); k++)
			{
				String url = arquivosSelecionados.get(k).getUrlDoArquivo();
				String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(url);
				if(arquivoLido.contains("&" + nomeDaFerramenta + "#id_") == true)
				{
					this.urlArquivoComIdDentroDele = url;
					SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
					return true;
				}
			}
			
			SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
			//se n tinha arquivos com o id conflitante, vamos retornar false
			return false;
		}
	
	
	//A funcao abaixo cria o PDF com todo o codigo de todos os arquivos de um projeto java.
	//A funcao nao pode ser usada antes do SingletonGuardaProjetoPastasEArquivosSelecionados ter as pastas selecionadas, arquivos ou apenas o diretorio.
	//O usuario pode decidir tanto escolher apenas a pasta do projeto como os arquivos especificos e ate mesmo pastas especificas
	//Caso o arquivo apresente algum id ruim(#%&#id...), o software irah perguntar se o usuario quer gerar o pdf mesmo sem numero de paginas 
	public void gerarPDFParaRegistroDeSoftware(LinkedList<String> extensoes, String tituloDoProjeto, String nomeDiretorioRaizDoProjeto, String versaoDoProjeto, String nomeDosAutoresSeparadosPorVirgula,String linguagens, LinkedList<String> tiposDeAlicacao, LinkedList<String> tiposDePrograma, String moduloComponenteDoProjeto)
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
					
					//tambem devemos pegar as extensoes de cada arquivo e sair colocando num hashmap
					HashMap<String,Integer> extensoesEQuantosArquivosExistemDestaExtensao = new HashMap<String, Integer>();
					
					for(int f = 0; f < pastasSelecionadas.size(); f++)
					{
						String umaUrlUmaPasta = pastasSelecionadas.get(f).getUrlDaPasta();
						LinkedList<String> caminhosDeArquivosDentroDePastasSelecionadas = 
								pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(umaUrlUmaPasta, extensoes);
						for(int g = 0; g < caminhosDeArquivosDentroDePastasSelecionadas.size(); g++)
						{
							String umCaminhoArquivoDentroPastasSelecionadas = caminhosDeArquivosDentroDePastasSelecionadas.get(g);
							caminhosDeArquivosQueIraoParaOPDF.add(umCaminhoArquivoDentroPastasSelecionadas);
							
							//falta colocar a extensao deste arquivo
							String extensaoDesteArquivo = FilenameUtils.getExtension(umCaminhoArquivoDentroPastasSelecionadas);
							if(extensoesEQuantosArquivosExistemDestaExtensao.containsKey(extensaoDesteArquivo) == true)
							{
								int quantosArquivosDestaExtensaoEncontrados = 
										extensoesEQuantosArquivosExistemDestaExtensao.get(extensaoDesteArquivo);
								quantosArquivosDestaExtensaoEncontrados = quantosArquivosDestaExtensaoEncontrados + 1;
								extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo, quantosArquivosDestaExtensaoEncontrados);
							}
							else
							{
								extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo,1);
							}
						}
					}
					
					
					//agora vamos pegar soh o caminhos dos arquivos que foram selecionados
					LinkedList<ArquivoDoProjeto> arquivosSelecionados = 
							SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getArquivosSelecionados();
					
					for(int h = 0; h < arquivosSelecionados.size(); h++)
					{
						ArquivoDoProjeto umArquivo = arquivosSelecionados.get(h);
						String extensaoUmArquivo = umArquivo.getExtensaoDoArquivo();
						boolean arquivoEhDeExtensaoQueIremosUsar = false;
						//vamos ver se eh de alguma extensao que iremos usar
						for(int s = 0; s < extensoes.size(); s++)
						{
							String umaExtensao = extensoes.get(s);
							String umaExtensaoComPonto = "." + umaExtensao;
							
							if(umaExtensaoComPonto.compareTo(extensaoUmArquivo) == 0)
							{
								arquivoEhDeExtensaoQueIremosUsar = true;
								break;
							}
							
						}
						
						if(arquivoEhDeExtensaoQueIremosUsar == true)
						{
							String umaUrlUmArquivoSelecionado = umArquivo.getUrlDoArquivo();
							caminhosDeArquivosQueIraoParaOPDF.add(umaUrlUmArquivoSelecionado);
							
							//falta colocar a extensao deste arquivo
							String extensaoDesteArquivo = FilenameUtils.getExtension(umaUrlUmArquivoSelecionado);
							if(extensoesEQuantosArquivosExistemDestaExtensao.containsKey(extensaoDesteArquivo) == true)
							{
								int quantosArquivosDestaExtensaoEncontrados = 
										extensoesEQuantosArquivosExistemDestaExtensao.get(extensaoDesteArquivo);
								quantosArquivosDestaExtensaoEncontrados = quantosArquivosDestaExtensaoEncontrados + 1;
								extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo, quantosArquivosDestaExtensaoEncontrados);
							}
							else
							{
								extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo,1);
							}
						}
						else
						{
							//nao fazemos nada. O arquivo n era de uma extensao que iriamos usar!
						}
					}
					
					//agora soh falta criar o pdf com base nos caminhosDeArquivosQueIraoParaOPDF
					LinkedList<String> nomesArquivosLidos = new LinkedList<String>();
					LinkedList<String> textosArquivosLidos = new LinkedList<String>();
					File arquivoPdfGerar = new File(outputFILE);
					
					
					//vamos criar a barrinha de progresso para a etapa 2: extrair todo o texto dos arquivos
					String textoBarraDeProgresso = "Lendo arquivos e pastas...";
					SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(caminhosDeArquivosQueIraoParaOPDF.size() - 1,textoBarraDeProgresso);
					
					for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
					{
						SingletonBarraDeProgresso.getInstance().updateBarraDeProgresso(i);
						String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
						String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
						String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeDiretorioRaizDoProjeto);
						nomesArquivosLidos.add(nomeArquivoLido);
						textosArquivosLidos.add(arquivoLido);
					}
					SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
					  
					GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
					geradorPdf.gerarPDFDeStringVariosArquivosSemNumeroDePaginas(textosArquivosLidos, tituloDoProjeto,nomesArquivosLidos,arquivoPdfGerar,nomeDiretorioRaizDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula,extensoesEQuantosArquivosExistemDestaExtensao,linguagens, tiposDeAlicacao, tiposDePrograma, moduloComponenteDoProjeto);
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
					
					//vamos criar a barrinha de progresso para a etapa 2: extrair todo o texto dos arquivos
					String textoBarraDeProgresso = "Lendo arquivos e pastas...";
					SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(caminhosDeArquivosQueIraoParaOPDF.size() - 1,textoBarraDeProgresso);
					
					//tambem devemos pegar as extensoes de cada arquivo e sair colocando num hashmap
					HashMap<String,Integer> extensoesEQuantosArquivosExistemDestaExtensao = new HashMap<String, Integer>();
					
					for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
					{
						SingletonBarraDeProgresso.getInstance().updateBarraDeProgresso(i);
						String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
						String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
						String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeDiretorioRaizDoProjeto);
						nomesArquivosLidos.add(nomeArquivoLido);
						textosArquivosLidos.add(arquivoLido);
						
						//falta colocar a extensao deste arquivo
						String extensaoDesteArquivo = FilenameUtils.getExtension(umArquivo);
						if(extensoesEQuantosArquivosExistemDestaExtensao.containsKey(extensaoDesteArquivo) == true)
						{
							int quantosArquivosDestaExtensaoEncontrados = 
									extensoesEQuantosArquivosExistemDestaExtensao.get(extensaoDesteArquivo);
							quantosArquivosDestaExtensaoEncontrados = quantosArquivosDestaExtensaoEncontrados + 1;
							extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo, quantosArquivosDestaExtensaoEncontrados);
						}
						else
						{
							extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo,1);
						}
					}
					
					SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
					  
					GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
					geradorPdf.gerarPDFDeStringVariosArquivosSemNumeroDePaginas(textosArquivosLidos, tituloDoProjeto,nomesArquivosLidos,arquivoPdfGerar,nomeDiretorioRaizDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula, extensoesEQuantosArquivosExistemDestaExtensao,linguagens, tiposDeAlicacao, tiposDePrograma, moduloComponenteDoProjeto);
				}
				
			}
			else
			{
				//nao acontece nada
				SingletonPDFGeradoComSucessoDeveSerMostrado.getInstance().setDeveSerMostrado(false);
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
				
				HashMap<String,Integer> extensoesEQuantosArquivosExistemDestaExtensao = new HashMap<String,Integer>();
				
				for(int f = 0; f < pastasSelecionadas.size(); f++)
				{
					String umaUrlUmaPasta = pastasSelecionadas.get(f).getUrlDaPasta();
					LinkedList<String> caminhosDeArquivosDentroDePastasSelecionadas = 
							pegaCaminhos.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(umaUrlUmaPasta, extensoes);
					for(int g = 0; g < caminhosDeArquivosDentroDePastasSelecionadas.size(); g++)
					{
						String umCaminhoArquivoDentroPastasSelecionadas = caminhosDeArquivosDentroDePastasSelecionadas.get(g);
						caminhosDeArquivosQueIraoParaOPDF.add(umCaminhoArquivoDentroPastasSelecionadas);
						
						//falta colocar a extensao deste arquivo
						String extensaoDesteArquivo = FilenameUtils.getExtension(umCaminhoArquivoDentroPastasSelecionadas);
						if(extensoesEQuantosArquivosExistemDestaExtensao.containsKey(extensaoDesteArquivo) == true)
						{
							int quantosArquivosDestaExtensaoEncontrados = 
									extensoesEQuantosArquivosExistemDestaExtensao.get(extensaoDesteArquivo);
							quantosArquivosDestaExtensaoEncontrados = quantosArquivosDestaExtensaoEncontrados + 1;
							extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo, quantosArquivosDestaExtensaoEncontrados);
						}
						else
						{
							extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo,1);
						}
					}
				}
				
				
				//agora vamos pegar soh o caminhos dos arquivos que foram selecionados
				LinkedList<ArquivoDoProjeto> arquivosSelecionados = 
						SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().getArquivosSelecionados();
				for(int h = 0; h < arquivosSelecionados.size(); h++)
				{
					ArquivoDoProjeto umArquivo = arquivosSelecionados.get(h);
					String extensaoUmArquivo = umArquivo.getExtensaoDoArquivo();
					boolean arquivoEhDeExtensaoQueIremosUsar = false;
					//vamos ver se eh de alguma extensao que iremos usar
					for(int s = 0; s < extensoes.size(); s++)
					{
						String umaExtensao = extensoes.get(s);
						String umaExtensaoComPonto =  "." + umaExtensao;
						
						
						if(umaExtensaoComPonto.compareTo(extensaoUmArquivo) == 0)
						{
							arquivoEhDeExtensaoQueIremosUsar = true;
							break;
						}
						
					}
					
					if(arquivoEhDeExtensaoQueIremosUsar == true)
					{
						String umaUrlUmArquivoSelecionado = umArquivo.getUrlDoArquivo();
						caminhosDeArquivosQueIraoParaOPDF.add(umaUrlUmArquivoSelecionado);
						
						//falta colocar a extensao deste arquivo
						String extensaoDesteArquivo = FilenameUtils.getExtension(umaUrlUmArquivoSelecionado);
						if(extensoesEQuantosArquivosExistemDestaExtensao.containsKey(extensaoDesteArquivo) == true)
						{
							int quantosArquivosDestaExtensaoEncontrados = 
									extensoesEQuantosArquivosExistemDestaExtensao.get(extensaoDesteArquivo);
							quantosArquivosDestaExtensaoEncontrados = quantosArquivosDestaExtensaoEncontrados + 1;
							extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo, quantosArquivosDestaExtensaoEncontrados);
						}
						else
						{
							extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo,1);
						}
						
					}
					else
					{
						//nao fazemos nada. O arquivo n era de uma extensao que iriamos usar!
					}
				}
				
				//agora soh falta criar o pdf com base nos caminhosDeArquivosQueIraoParaOPDF
				LinkedList<String> nomesArquivosLidos = new LinkedList<String>();
				LinkedList<String> textosArquivosLidos = new LinkedList<String>();
				File arquivoPdfGerar = new File(outputFILE);
				File arquivopdfGerarComNumeroDePaginas = new File(Main.outputFILE2);
				
				//vamos criar a barrinha de progresso para a etapa 2: extrair todo o texto dos arquivos
				String textoBarraDeProgresso = "Lendo arquivos e pastas...";
				SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(caminhosDeArquivosQueIraoParaOPDF.size() - 1,textoBarraDeProgresso);
				for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
				{
					SingletonBarraDeProgresso.getInstance().updateBarraDeProgresso(i);
					String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
					String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
					String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeDiretorioRaizDoProjeto);
					nomesArquivosLidos.add(nomeArquivoLido);
					textosArquivosLidos.add(arquivoLido);
				}
				
				SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
				
				  
				GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
				geradorPdf.gerarPDFDeStringVariosArquivosComNumeroDePaginas(textosArquivosLidos,tituloDoProjeto, nomesArquivosLidos,arquivoPdfGerar,arquivopdfGerarComNumeroDePaginas,nomeDiretorioRaizDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula,extensoesEQuantosArquivosExistemDestaExtensao,linguagens, tiposDeAlicacao, tiposDePrograma, moduloComponenteDoProjeto);
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
				
				//vamos criar a barrinha de progresso para a etapa 2: extrair todo o texto dos arquivos
				String textoBarraDeProgresso = "Lendo arquivos e pastas...";
				SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(caminhosDeArquivosQueIraoParaOPDF.size() - 1,textoBarraDeProgresso);
				
				HashMap<String,Integer> extensoesEQuantosArquivosExistemDestaExtensao = new HashMap<String, Integer>();
				
				for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
				{
					SingletonBarraDeProgresso.getInstance().updateBarraDeProgresso(i);
					String umArquivo = caminhosDeArquivosQueIraoParaOPDF.get(i);
					String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(umArquivo);
					String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(umArquivo, nomeDiretorioRaizDoProjeto);
					nomesArquivosLidos.add(nomeArquivoLido);
					textosArquivosLidos.add(arquivoLido);
					
					//falta colocar a extensao deste arquivo
					String extensaoDesteArquivo = FilenameUtils.getExtension(umArquivo);
					if(extensoesEQuantosArquivosExistemDestaExtensao.containsKey(extensaoDesteArquivo) == true)
					{
						int quantosArquivosDestaExtensaoEncontrados = 
								extensoesEQuantosArquivosExistemDestaExtensao.get(extensaoDesteArquivo);
						quantosArquivosDestaExtensaoEncontrados = quantosArquivosDestaExtensaoEncontrados + 1;
						extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo, quantosArquivosDestaExtensaoEncontrados);
					}
					else
					{
						extensoesEQuantosArquivosExistemDestaExtensao.put(extensaoDesteArquivo,1);
					}
					
				}
				
				SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
				
				GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
				geradorPdf.gerarPDFDeStringVariosArquivosComNumeroDePaginas(textosArquivosLidos,tituloDoProjeto, nomesArquivosLidos,arquivoPdfGerar,arquivopdfGerarComNumeroDePaginas,nomeDiretorioRaizDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula,extensoesEQuantosArquivosExistemDestaExtensao,linguagens, tiposDeAlicacao, tiposDePrograma, moduloComponenteDoProjeto);
			}
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
				
				//vamos criar a barrinha de progresso para a etapa 1: verificar se algum arquivo tem extensao ruim
				String textoBarraDeProgresso = "Verificando arquivos...";
				SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(caminhosDeArquivosQueIraoParaOPDF.size() - 1,textoBarraDeProgresso);
				
				for(int i = 0; i < caminhosDeArquivosQueIraoParaOPDF.size(); i++)
				{
					SingletonBarraDeProgresso.getInstance().updateBarraDeProgresso(i);
					String url = caminhosDeArquivosQueIraoParaOPDF.get(i);
					String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(url);
					if(arquivoLido.contains("&" + nomeDaFerramenta + "#id_") == true)
					{
						this.urlArquivoComIdDentroDele = url;
						SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
						return true;
					}
				}
				
				SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
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
		String linguagens = "java,xml";
		String modulo = "cliente";
		LinkedList<String> tiposDeAplicacao = new LinkedList<String>();
		  LinkedList<String> tiposDePrograma = new LinkedList<String>();
		  tiposDeAplicacao.add("AD01- Administr");
		  tiposDeAplicacao.add("AD02- FunçãoAdm");
		  tiposDePrograma.add("SO01-Sist Operac");
		  String urlDiretorio = "C:\\Users\\fábioandrews\\Desktop\\adt-bundle-windows-x86-20130717\\adt-bundle-windows-x86_64-20131030\\adt-bundle-windows-x86_64-20131030\\eclipse\\projetos\\KarutaKanji";
		SingletonGuardaProjetoPastasEArquivosSelecionados.getInstance().setPastaDoProjeto(new File(urlDiretorio));
		main.gerarPDFParaRegistroDeSoftware(extensoes,nomeProjeto,urlDiretorio,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula,linguagens,tiposDeAplicacao,tiposDePrograma, modulo);

	}

}
