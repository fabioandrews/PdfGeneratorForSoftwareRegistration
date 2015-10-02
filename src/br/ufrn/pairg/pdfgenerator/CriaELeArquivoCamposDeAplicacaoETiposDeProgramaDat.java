package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

public class CriaELeArquivoCamposDeAplicacaoETiposDeProgramaDat 
{
	private String[] opcoesTiposDeAplicacaoDefault = { "AD01-Administr", "AD02-Fun��o Adm", "AD03-Modern Adm", "AD04-Adm Publ", "AD05-Adm Empres","AD06-Adm Prod","AD07-Adm Pes","AD08-Adm Materl","AD09-Adm Patrim","AD10-Marketing","AD11-Adm Escrit",
			"AG01-Agricultur","AG02-Ci�nc Agrl","AG03-Adm Agricl","AG04-Econom Agr�c","AG05-Sist agr�c","AG06-Eng agr�cl","AG07-Edafologia","AG08-Fitopatol","AG09-Prod Veget","AG10-Prod Animl","AG11-Ci�nc Flor","AG12-Aquacultur","AG13-Extr Veget","AG14-Extr Animl",
			"AN01-Sociedade","AN02-Desenv soc","AN03-Grupos soc","AN04-Cultura","AN05-Religi�o","AN06-Antropolog","AN07-Sociologia",
			"AH01-Assen Hum","AH02-Cidade","AH03-Org Territ","AH04-Pol As Hum","AH05-Popula��o","AH06-Discip Aux",
			"BL01-Biologia","BL02-Gen�tica","BL03-Citologia","BL04-Microbiolg","BL05-Anatomia","BL06-Fisiologia","BL07-Bioqu�mica","BL08-Biof�sica",
			"BT01-Bot�nica","BT02-Fitogeograf","BT03-Bot�n Econ","BT04-Bot�n Sist",
			"CO01-Filosofia","CO02-Ci�ncia","CO03-Ci�nc Ling","CO04-Comunic","CO05-Arte","CO06-Hist�ria",
			"CC01-Constru��o","CC02-Proc Const","CC03-Org Constr","CC04-Obra P�bl","CC05-Estrutura","CC06-Edifica��o","CC07-Tecn Const","CC08-Hig Const","CC09-Eng Hidrl","CC10-Solo",
			"DI01-Legisla��o","DI02-Dir Constl","DI03-Disc Dr.",
			"EL01-Ecologia","EL02-Ecofisiol","EL03-Ecol Human","EL04-EcVeg/Anm","EL05-Etologia",
			"EC01-Economia","EC02-AnMicroec","EC03-TeoMicroe","EC04-AtivEconm","EC05-Contab Nac","EC06-Econ Monet","EC07-Mercado","EC08-Bens Econom","EC09-Eng/Din Ec","EC10-Econ Espec","EC11-Propriedad","EC12-Ec Internac","EC13-Pol�t Econ","EC14-Empresa",
			"ED01-Ensin Regl","ED02-Ensin-Supl","ED03-Adm/Pr Ens","ED04-Formas Ens","ED05-Curr�culo","ED06-Educa��o",
			"EN01-Energia","EN02-Rec Energ","EN03-Combust�vl","EN04-Tecn Energ","EN05-Eng Eltr�n","EN06-Eng Nucle",
			"FN01-Finan P�bl","FN02-Finan Priv","FN03-Sist Finan","FN04-Rec/Instrum","FN05-Adm Finan","FN06-Contabilid",
			"FQ01-F�s Part�c","FQ02-Ac�st/�tic","FQ03-Onda","FQ04-Metrologia","FQ05-Mec�nica","FQ06-Fis Solid","FQ07-Termodin�m","FQ08-Eletr�nica","FQ09-Magn/Elmag","FQ10-F�s SupDis","FQ11-Radia��o","FQ12-Espectrosc","FQ13-F�s Molecl","FQ14-Qu�mica","FQ15-Qu�m An/Po","FQ16-F�s-Qu�m","FQ17-Qu�m Org�n","FQ18-Qu�m Inorg",
			"GC01-Geog F�sic","GC02-Geog Humna","GC03-Geog Regio","GC04-Orient Geo","GC05-Geodesia","GC06-Topografia","GC07-Fotogramet","GC08-Mapeamento","GC09-Met Cartog","GC10-Plan Carto",
			"GL01-Geol F�sic","GL02-Glaciolog","GL03-Geotectonc","GL04-Geol Marin","GL05-Geol Hist","GL06-Geol Econ","GL07-GeoQuiFiTe",
			"HB01-Habita��o","HB02-Tipol Habt",
			"HD01-Hidrologia","HD02-Hidrograf","HD03-Hidrometr","HD04-Oceanograf",
			"IN01-Ind�stria","IN02-Tecnologia","IN03-Engenharia","IN04-Ind Ext Mi","IN05-Ind Transf",
			"IF01-Informa��o","IF02-Documenta�","IF03-Reprograf","IF04-Documento","IF05-Biblioteco","IF06-Arquivolog","IF07-Ci�nc Info","IF08-Serv Info","IF09-Uso Inform","IF10-Gen�rico",
			"MT01-L�gica Mat","MT02-�lgebra","MT03-Geometria","MT04-An�lis Mat","MT05-C�lculo","MT06-Mat Aplic",
			"MA01-Meio Amb","MA02-Recurs Nat","MA03-Polui��o","MA04-Qualid Amb",
			"ME01-Metodolg","ME02-Atmosfera","ME03-Climatolog",
			"PD01-Pedologia","PD02-Pedog�nese","PD03-Tipos de Solo",
			"PL01-Ci�nc Pol","PL02-Pol�tica",
			"PR01-Previd�nc","PR02-Benef Prev","PR03-Assist Soc",
			"PS01-Psicologia","PS02-Comportamt","PS03-Teor Psic",
			"SM01-Saneamento","SM02-Res�duo","SM03-Limpeza","SM04-Abast �gua","SM05-Esgoto",
			"SD01-Sa�de","SD02-Adm Sanit","SD03-Doen�a","SD04-Defic F�s","SD05-Assist M�d","SD06-Terap Diag","SD07-Medicina","SD08-Espec Med","SD09-Eng Biomed","SD10-Farmacolog","SD11-Odontolog",
			"SV01-Servi�os","SV02-Seguro","SV03-Com�rcio","SV04-Turismo",
			"TC01-Telecom","TC02-Sist Telec","TC03-Eng Telec","TC04-Serv/Redes",
			"TB01-Trabalho","TB02-Rec Human","TB03-Merc Trab","TB04-Cond Trab","TB05-Estr Ocup","TB06-Lazer",
			"TP01-Transporte","TP02 -Sist Trans","TP03-Serv Trans","TP04-Eng Transp","TP05-Mod Transp",
			"UB01-Urbanismo","UB02-Solo urban","UB03-�rea urban","UB04-Circ Urban","UB05-Arquitetur"};//serah colocado na combobox

	private String[] opcoesTipoDeProgramaDefault = {"AP01-Aplicativo","AP02-Planejament","AP03-Controle","AP04-Auditoria",
			"AP05-Contabiliz","AT01-Automa��o","AT02-Atm Escrt","AT03-Atm Comerc","AT04-Atm Bancar",
			"AT05-Atm Indust","AT06-Contr Proc","AT07-Atm Manuf","AT08-Elet Autom","AV01-Aval Desemp",
			"AV02-Cont Recurs","CD01-Com Dados","CD02-Emul Termnl","CD03-Monitor TP","CD04-Ger Dispost",
			"CD05-Ger de Rede","CD06-Rede Local","CT01-Comuta��o","CT02-Impl Fun Ad","CT03-Ger Op&Man",
			"CT04-Term Op&Man","DS01-Ferrm Desnv","DS02-Gerd Aplic.","DS03-CASE","DS04-Desv c/Metd",
			"DS05-Bib Rotinas","DS06-Apoio Progm","DS07-Sup Documt","DS08-Convers Sis","ET01-Entrtmnto",
			"ET02-Jogos Anim","ET03-Gerad Desen","ET04-Simuladores","FA01-Ferrm Apoio","FA02-Proc Texto",
			"FA03-Planil Elet","FA04-Gerad Gr�fc","GI01-Gerenc Info","GI02-Gerenc BD","GI03-Gerad Telas",
			"GI04-Gerad Relat","GI05-Dicion Dad","GI06-Ent Val Dad","GI07-Org Man Arq","GI08-Recup Dados",
			"IA01-Intlg Artf","IA02-Sist Especl","IA03-Proc Lng Nt","IT01-Instrument","IT02-Inst T&M",
			"IT03-Inst Biomd","IT04-Inst Analt","LG01-Linguagem","LG02-Compilador","LG03-Montador",
			"LG04-Pr�-Compld","LG05-Comp Cruz","LG06-Pr�-Proces","LG07-Interptd","LG08-Ling Procd",
			"LG09-Ling N Prcd","PD01-Seg Prot Dd","PD02-Senha","PD03-Criptograf","PD04-Man Intg Dd",
			"PD05-Cont Acess","SM01-Simul & Mod","SM02-Simulador","SM03-Sim Amb Op","SM04-CAE/CAD/CAM",
			"SO01-Sist Operac","SO02-Interf E&S","SO03-Interf Disc","SO04-Interf Com","SO05-Geren Usuar",
			"SO06-Adm Dispost","SO07-Cont Proces","SO08-Cont Redes","SO09-Proc Comand","TC01-Aplc Tcn Ct",
			"TC02-Pesq Operac","TC03-Recnh Padr","TC04-Proc Imagem","TI01-Teleinform","TI02-Terminais",
			"TI03-Transm Dados","TI04-Comut Dados","UT01-Utilit�rios","UT02-Compress Dd","UT03-Conv Arq",
			"UT04-Class/Inter","UT05-Cont Spool","UT06-Transf Arq"};
	
	private void criarArquivoCamposDeAplicacao()
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("camposDeAplicacao.dat", "UTF-8");
			
			for(int i = 0; i < this.opcoesTiposDeAplicacaoDefault.length; i++)
			{
				String umCampoDeAplicacao = this.opcoesTiposDeAplicacaoDefault[i];
				String oQueEscrever = umCampoDeAplicacao;
				
				if(i < this.opcoesTiposDeAplicacaoDefault.length - 1)
				{
					oQueEscrever = oQueEscrever + ",";
				}
				
				writer.println(oQueEscrever);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void criarArquivoTiposDePrograma()
	{
		PrintWriter writer;
		try {
			writer = new PrintWriter("tiposDePrograma.dat", "UTF-8");
			
			for(int i = 0; i < this.opcoesTipoDeProgramaDefault.length; i++)
			{
				String umTipoDePrograma = this.opcoesTipoDeProgramaDefault[i];
				String oQueEscrever = umTipoDePrograma;
				
				if(i < this.opcoesTipoDeProgramaDefault.length - 1)
				{
					oQueEscrever = oQueEscrever + ",";
				}
				
				writer.println(oQueEscrever);
			}
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//serah que o arquivo ja existe ou nao?
	private boolean existeArquivoCamposDeAplicacaodat()
	{
		File f = new File("camposDeAplicacao.dat");
		if(f.exists() && !f.isDirectory()) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
		
	//serah que o arquivo ja existe ou nao?
	private boolean existeArquivoTiposDeProgramadat()
	{
		File f = new File("tiposDePrograma.dat");
		if(f.exists() && !f.isDirectory()) 
		{
			return true;
		}
		else
		{
			return false;
		}
	}
		
	
	public String[] pegarCamposDeAplicacao()
	{
		LinkedList<String> retorno = new LinkedList<String>();
		
		//serah que o arquivo ja existe?
		boolean existeArquivo = this.existeArquivoCamposDeAplicacaodat();
		if(existeArquivo == false)
		{
			//nao existe. Vamos cria-lo e retornar os valores default!
			this.criarArquivoCamposDeAplicacao();
			
			for(int i = 0; i < this.opcoesTiposDeAplicacaoDefault.length; i++)
			{
				String umElemento = this.opcoesTiposDeAplicacaoDefault[i];
				retorno.add(umElemento);
			}
		}
		else
		{
			//existe! Vamos ler do arquivo
			String textoNoArquivo = 
					LeitorArquivoTexto.lerArquivoQualquerDeTexto("camposDeAplicacao.dat");
			
			String textoNoArquivoSemEspacos = textoNoArquivo.replaceAll("\\s+",""); //remove todos os espacos em branco
			String[] elementosNoArquivo = textoNoArquivoSemEspacos.split(",");
			
			for(int i = 0; i < elementosNoArquivo.length; i++)
			{
				if(elementosNoArquivo[i].length() > 0)
				{
					String umElemento = elementosNoArquivo[i];
					
					//ele pode ser um nome composto que nos tiramos o espaco em branco! Vamos ver
					String umElementoComEspacoEmBranco = "";
					boolean passouOTravessao = false; //so considera depois do travessao
					for(int ii = 0; ii < umElemento.length(); ii++)
					{
						String umaLetra = String.valueOf(umElemento.charAt(ii));
						if(umaLetra.compareTo("-") == 0)
						{
							passouOTravessao = true;
						}
						
						if(passouOTravessao == true && Character.isUpperCase(umElemento.charAt(ii)) == true)
						{
							umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + " ";
						}
						
						umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + umaLetra;
					}
					
					retorno.add(umElementoComEspacoEmBranco);
				}
			}
		}
		
		//falta transformar essa linkedlist em array
		String[] arrayRetorno = new String[retorno.size()];
		for(int j = 0; j < retorno.size(); j++)
		{
			arrayRetorno[j] = retorno.get(j);
		}
		return arrayRetorno;
	}
	
	public String[] pegarTiposDePrograma()
	{
		LinkedList<String> retorno = new LinkedList<String>();
		
		//serah que o arquivo ja existe?
		boolean existeArquivo = this.existeArquivoTiposDeProgramadat();
		if(existeArquivo == false)
		{
			//nao existe. Vamos cria-lo e retornar os valores default!
			this.criarArquivoTiposDePrograma();
			
			for(int i = 0; i < this.opcoesTipoDeProgramaDefault.length; i++)
			{
				String umElemento = this.opcoesTipoDeProgramaDefault[i];
				retorno.add(umElemento);
			}
		}
		else
		{
			//existe! Vamos ler do arquivo
			String textoNoArquivo = 
					LeitorArquivoTexto.lerArquivoQualquerDeTexto("tiposDePrograma.dat");
			
			String textoNoArquivoSemEspacos = textoNoArquivo.replaceAll("\\s+",""); //remove todos os espacos em branco
			String[] elementosNoArquivo = textoNoArquivoSemEspacos.split(",");
			
			for(int i = 0; i < elementosNoArquivo.length; i++)
			{
				if(elementosNoArquivo[i].length() > 0)
				{
					String umElemento = elementosNoArquivo[i];
					
					//ele pode ser um nome composto que nos tiramos o espaco em branco! Vamos ver
					String umElementoComEspacoEmBranco = "";
					boolean passouOTravessao = false; //so considera depois do travessao
					for(int ii = 0; ii < umElemento.length(); ii++)
					{
						String umaLetra = String.valueOf(umElemento.charAt(ii));
						if(umaLetra.compareTo("-") == 0)
						{
							passouOTravessao = true;
						}
						
						if(passouOTravessao == true && Character.isUpperCase(umElemento.charAt(ii)) == true)
						{
							umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + " ";
						}
						
						umElementoComEspacoEmBranco = umElementoComEspacoEmBranco + umaLetra;
					}
					
					retorno.add(umElementoComEspacoEmBranco);
				}
			}
		}
		
		//falta transformar essa linkedlist em array
		String[] arrayRetorno = new String[retorno.size()];
		for(int j = 0; j < retorno.size(); j++)
		{
			arrayRetorno[j] = retorno.get(j);
		}
		return arrayRetorno;
	}

}
