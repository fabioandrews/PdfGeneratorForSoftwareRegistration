package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.Normalizer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

//Essa classe vai ler e modificar e criar o arquivo .dat com as extensoes que devem ser consideradas no projeto e ja atualizar a lista de extensoes na gui
public class CriaeLeArquivoLinguagensdat 
{
	//caso n ache nada, retorna hashmap padrao e cria arquivo dat
	public HashMap<String,LinkedList<String>> pegarLinguagensESuasExtensoesRecomendadadasNoArquivoExtensoes()
	{
		if(this.existeArquivoLinguagensdatECriaPastaConfigSeNaoExistir() == false)
		{
			//o arquivo n existe ainda. Vamos criar!
			criarArquivoLinguagensdat();
		}
		
			HashMap<String,LinkedList<String>> linguagensESuasExtensoesRecomendadadas =
																new HashMap<String,LinkedList<String>>();
			String textoNoArquivoLinguagens = 
					LeitorArquivoTexto.lerArquivoQualquerDeTexto("config/linguagens.dat");
			String textoNoArquivoLinguagensSemAcentos = Normalizer.normalize(textoNoArquivoLinguagens, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
			
			//ex: Java;java.
			//    C+;c,ccc.
			String tudoDeImportanteNoArquivo = textoNoArquivoLinguagensSemAcentos.split("//linguagens//")[1];
			String[] linguagensESuasExtensoesSeparadasPorPontoEVirgulaEVirgulas = tudoDeImportanteNoArquivo.split("\\.");
			for(int i = 0; i < linguagensESuasExtensoesSeparadasPorPontoEVirgulaEVirgulas.length - 1; i++)
			{
				String umaLinguagemESuasExtensoesSeparadasPorPontoEVirgulaEVirgulas = linguagensESuasExtensoesSeparadasPorPontoEVirgulaEVirgulas[i];
				String umaLinguagemESuasExtensoesSeparadasPorPontoEVirgulaEVirgulasSemEspacos = umaLinguagemESuasExtensoesSeparadasPorPontoEVirgulaEVirgulas.replaceAll("\\s+","");
				String umaLinguagem = umaLinguagemESuasExtensoesSeparadasPorPontoEVirgulaEVirgulasSemEspacos.split("\\;")[0];
				LinkedList<String> extensoesUmaLinguagem = new LinkedList<String>();
				String extensoesSeparadasPorVirgula = umaLinguagemESuasExtensoesSeparadasPorPontoEVirgulaEVirgulasSemEspacos.split("\\;")[1];
				
				if(extensoesSeparadasPorVirgula.contains(",") == true)
				{
					//existe mais de uma extensao
					String[] arrayComCadaExtensao = extensoesSeparadasPorVirgula.split("\\,");
					for(int j = 0; j < arrayComCadaExtensao.length; j++)
					{
						extensoesUmaLinguagem.add(arrayComCadaExtensao[j]);
					}
				}
				else
				{
					//so ha uma extensao
					extensoesUmaLinguagem.add(extensoesSeparadasPorVirgula);
				}
				
				//por fim, adicionar a linguagem e extensoes ao hashmap
				linguagensESuasExtensoesRecomendadadas.put(umaLinguagem, extensoesUmaLinguagem);			
			}
			
			return linguagensESuasExtensoesRecomendadadas;
	}
	
	
	//serah que o arquivo ja existe ou nao?
	private boolean existeArquivoLinguagensdatECriaPastaConfigSeNaoExistir()
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
			File f = new File("config/linguagens.dat");
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
	
	public void criarArquivoLinguagensdat()
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("config/linguagens.dat", "UTF-8");
			writer.println("//Coloque aqui as linguagens e suas extensoes recomendadadas//");
			writer.println("//linguagens//");
			writer.println("Actionscript;as.");
			writer.println("Ada;adb,ads.");
			writer.println("Android;java,xml.");
			writer.println("ASP;asp.");
			writer.println("C;c,h.");
			writer.println("C#;cs.");
			writer.println("C++;cc,cpp,cxx,C,c++,h,hh,hpp,hxx,h++.");
			writer.println("CSS;css.");
			writer.println("Delphi;dfm,dll,dpk,dpr.");
			writer.println("HTML/CSS;css,htm,html.");
			writer.println("Java;java,xml.");
			writer.println("JavaScript;js.");
			writer.println("Lua;lua.");
			writer.println("Objective-C;h,m,mm,C.");
			writer.println("Perl;pl,pm,t,pod.");
			writer.println("PHP;php,phtml,php3,php4,php5,phps.");
			writer.println("Python;py,pyc,pyd,pyo,pyw,pyz.");
			writer.println("Ruby;rb,rbw.");
			writer.println("SQL;sql.");
			writer.println("//linguagens//");
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
		CriaeLeArquivoLinguagensdat gerenteTxt = new CriaeLeArquivoLinguagensdat();
		HashMap<String,LinkedList<String>> linguagensEExtensoes = gerenteTxt.pegarLinguagensESuasExtensoesRecomendadadasNoArquivoExtensoes();
		
		
		if(linguagensEExtensoes.size() == 0)
		{
			System.out.println("nao ha linguagens no arquivo dat");
		}
		
		Iterator<String> iterador = linguagensEExtensoes.keySet().iterator();
		while(iterador.hasNext() == true)
		{
			String linguagem = iterador.next();
			System.out.println("//Linguagem:" + linguagem);
			LinkedList<String> extensoes = linguagensEExtensoes.get(linguagem);
			
			for(int i = 0; i < extensoes.size(); i++)
			{
				System.out.println(extensoes.get(i));
			}
		}
	}
	
}
