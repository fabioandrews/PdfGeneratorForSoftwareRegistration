package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import br.ufrn.pairg.interfacegrafica.SingletonBarraDeProgresso;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class GeraPDFDeStringVariosArquivos 
{
	  private static Font catFont = new Font(Font.FontFamily.COURIER, 18,
	      Font.BOLD);
	  private static Font redFont = new Font(Font.FontFamily.COURIER, 12,
	      Font.NORMAL, BaseColor.RED);
	  private static Font subFont = new Font(Font.FontFamily.COURIER, 16,
	      Font.BOLD);
	  private static Font smallBold = new Font(Font.FontFamily.COURIER, 12,
	      Font.BOLD);
	  private static Font smallFont = new Font(Font.FontFamily.COURIER, 7,
		      Font.NORMAL); //antes era 9
	  
	  private static String nomeDaFerramenta = "code2inpi"; //sera usado para criar o id necessario p gerar o numero de paginas
	  
	  private HashMap<String,String> nomesDosArquivosLidosESeusIds; 
	   //no metodo gerarPDFDeStringVariosArquivos, sao usados nomesDosArquivosLidos e 
	  //cada um deles eh pintado de vermelho no pdf. Antes de cada um deles, eh gerado um id tb pintado 
	  //em vermelho em cima do nome do arquivo(que na verdade eh todo o caminho ate ele)
	  //esse hashmap associa nomes e ids
	  
	  private static HeaderFooterPageEvent event;
	  
	  private static String nomeDaFerramentaNaoBugado = "code2inpi";
	  
	  
	  /**
	   * Gera um ï¿½nico PDF de vï¿½rios arquivo lidos, mas sem o numero das paginas associadas
	   * @param textosLidos lista com texto dos arquivos lidos. ele tem de ter o msm tamanho de nomesDosArquivosLidos
	   * @param nomesDosArquivosLidos
	   * @param arquivoPdfOutput arquivo PDF de output
	   * @return
	   */
	public boolean gerarPDFDeStringVariosArquivosSemNumeroDePaginas(LinkedList<String> textosLidos, String tituloDoProjeto, LinkedList<String> nomesDosArquivosLidos, File arquivoPdfOutput, String nomeDiretorioRaizDoProjeto, String versaoDoProjeto, String nomeDosAutoresSeparadosPorVirgula,HashMap<String,Integer> extensoesEQuantosArquivosExistemDestaExtensao,String linguagens, LinkedList<String> tiposDeAlicacao, LinkedList<String> tiposDePrograma, String moduloComponenteDoProjeto)
	{
		 try {
			  nomesDosArquivosLidosESeusIds = new HashMap<String,String>();
			  FileOutputStream fos = new FileOutputStream(arquivoPdfOutput);
			  Document document = new Document();
			  document.setPageSize(PageSize.A4);
			  document.setMargins(50, 45, 50, 60);
			  document.setMarginMirroring(false);
		      PdfWriter writer = PdfWriter.getInstance(document, fos);
		      document.open();
		      event = new HeaderFooterPageEvent();
		      writer.setBoxSize("bleed", new Rectangle(36, 54, 559, 788));
			  writer.setPageEvent(event);
		      addMetaData(document);
		      addTitlePage(document,tituloDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula,extensoesEQuantosArquivosExistemDestaExtensao,linguagens, tiposDeAlicacao, tiposDePrograma, moduloComponenteDoProjeto);
		      
		      
		    //vamos criar a barrinha de progresso para a etapa 3: criar pdf
			String textoBarraDeProgresso = "Criando PDF...";
			SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(textosLidos.size() - 1,textoBarraDeProgresso);
		      for(int i = 0; i < textosLidos.size(); i++)
		      {
		    	  SingletonBarraDeProgresso.getInstance().updateBarraDeProgresso(i);
		    	  String umTextoLido = textosLidos.get(i);
		    	  String umNomeArquivoLido = nomesDosArquivosLidos.get(i);
		    	  String labelParaHeader = umNomeArquivoLido;
		    	  String idUmNomeArquivoLido = "&" + this.nomeDaFerramenta + "#id_" +  i + "%";
		    	  this.nomesDosArquivosLidosESeusIds.put(umNomeArquivoLido, idUmNomeArquivoLido);
		    	  String textoLido2 = umTextoLido.replaceAll("\\t", "        ");
			      addContent(writer, document, textoLido2, "", -1, labelParaHeader);
		      }
		      
		      SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
		      document.close();
		      fos.close();
		     
		     
		      return true;
		    } catch (Exception e) {
		    	SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
		      e.printStackTrace();
		      return false;
		    }
	}
	
	  /**
	   * Gera um ï¿½nico PDF de vï¿½rios arquivos lidos, mas sem o numero das paginas associadas e com o id para gerar as paginas
	   * @param textosLidos lista com texto dos arquivos lidos. ele tem de ter o msm tamanho de nomesDosArquivosLidos
	   * @param nomesDosArquivosLidos
	   * @param arquivoPdfOutput arquivo PDF de output
	   * @return
	   */
	public boolean gerarPDFDeStringVariosArquivosSemNumeroDePaginasComId(LinkedList<String> textosLidos, String tituloDoProjeto, LinkedList<String> nomesDosArquivosLidos, File arquivoPdfOutput, String nomeDoProjeto, String versaoDoProjeto, String nomeDosAutoresSeparadosPorVirgula,HashMap<String,Integer> extensoesEQuantosArquivosExistemDestaExtensao,String linguagens, LinkedList<String> tiposDeAlicacao, LinkedList<String> tiposDePrograma, String moduloComponenteDoProjeto)
	{
		 try {
			  nomesDosArquivosLidosESeusIds = new HashMap<String,String>();
			  FileOutputStream fos = new FileOutputStream(arquivoPdfOutput);
			  Document document = new Document();
			  document.setPageSize(PageSize.A4);
			  document.setMargins(50, 45, 50, 60);
			  document.setMarginMirroring(false);
		      PdfWriter writer = PdfWriter.getInstance(document, fos);
		      document.open();
		      event = new HeaderFooterPageEvent();
		      writer.setBoxSize("bleed", new Rectangle(36, 54, 559, 788));
			  writer.setPageEvent(event);
		      addMetaData(document);
		      addTitlePage(document,tituloDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula,extensoesEQuantosArquivosExistemDestaExtensao,linguagens, tiposDeAlicacao, tiposDePrograma, moduloComponenteDoProjeto);
		    //vamos criar a barrinha de progresso para a etapa 3: calcular numero de paginas
		    String textoBarraDeProgresso = "Calculando número de páginas de cada arquivo...";
		    SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(textosLidos.size() - 1,textoBarraDeProgresso);
		      for(int i = 0; i < textosLidos.size(); i++)
		      {
		    	  SingletonBarraDeProgresso.getInstance().updateBarraDeProgresso(i);
		    	  String umTextoLido = textosLidos.get(i);
		    	  String umNomeArquivoLido = nomesDosArquivosLidos.get(i);
		    	  String labelParaHeader = umNomeArquivoLido;
		    	  String idUmNomeArquivoLido = "&" + this.nomeDaFerramenta + "#id_" +  i + "%";
		    	  this.nomesDosArquivosLidosESeusIds.put(umNomeArquivoLido, idUmNomeArquivoLido);
		    	  String umNomeArquivoLidoEIdDele = idUmNomeArquivoLido + " \n" + umNomeArquivoLido; //o id servirah para sabermos quantas paginas o arquivo possui no pdf
		    	  String textoLido2 = umTextoLido.replaceAll("\\t", "        ");
			      addContent(writer, document, textoLido2, umNomeArquivoLidoEIdDele, -1, labelParaHeader);
		      }
		      document.close();
		      fos.close();
		     
		      SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
		     
		      return true;
		    } catch (Exception e) {
		      e.printStackTrace();
		      SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
		      return false;
		    }
	}
	
	public boolean gerarPDFDeStringVariosArquivosComNumeroDePaginas(LinkedList<String> textosLidos, String tituloDoProjeto, LinkedList<String> nomesDosArquivosLidos, File arquivoPdfOutput, File arquivoPdfOutputComNumeroDePaginas, String nomeDiretorioRaizDoProjeto, String versaoDoProjeto, String nomeDosAutoresSeparadosPorVirgula,HashMap<String,Integer> extensoesEQuantosArquivosExistemDestaExtensao,String linguagens, LinkedList<String> tiposDeAlicacao, LinkedList<String> tiposDePrograma, String moduloComponenteDoProjeto)
	{
		/*primeiro vou executar gerarPDFDeStringVariosArquivosSemNumeroDePaginas para gerar um pdf com os 
		 * ids de cada arquivo, seus textos, mas sem o numero de paginas e vou alterar a variavel local this.nomesDosArquivosLidosESeusIds
		 */
		boolean conseguiGerarPrimeiroPdf = gerarPDFDeStringVariosArquivosSemNumeroDePaginasComId(textosLidos, tituloDoProjeto, nomesDosArquivosLidos, arquivoPdfOutput,nomeDiretorioRaizDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula,extensoesEQuantosArquivosExistemDestaExtensao,linguagens, tiposDeAlicacao, tiposDePrograma, moduloComponenteDoProjeto);
		
		if(conseguiGerarPrimeiroPdf == true)
		{
			//agora vou pegar quantas paginas os arquivos tem
			VerificaNumeroDePaginasDeCadaArquivoNoPdfGerado verificaNumeroDePaginas = new VerificaNumeroDePaginasDeCadaArquivoNoPdfGerado();
			HashMap<String,Integer> arquivosEQuantasPaginasElesTem = verificaNumeroDePaginas.pegarNumeroDePaginasNoPdfDeCadaArquivo(this.nomesDosArquivosLidosESeusIds, nomesDosArquivosLidos, Main.outputFILE);
			
			//arquivoPdfOutput.delete(); //voudeletar esse primeiro pdf. n eh mais util
			//agora comeco a criar o segundo pdf que terah o numero de paginas de cada arquivo
			try 
			 {
				  FileOutputStream fos = new FileOutputStream(arquivoPdfOutputComNumeroDePaginas);
				  Document document = new Document();
				  document.setPageSize(PageSize.A4);
				  document.setMargins(50, 45, 50, 60);
				  document.setMarginMirroring(false);
			      PdfWriter writer = PdfWriter.getInstance(document, fos);
			      document.open();
			      event = new HeaderFooterPageEvent();
			      writer.setBoxSize("bleed", new Rectangle(36, 54, 559, 788));
				  writer.setPageEvent(event);
			      addMetaData(document);
			      addTitlePage(document,tituloDoProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula,extensoesEQuantosArquivosExistemDestaExtensao,linguagens, tiposDeAlicacao, tiposDePrograma, moduloComponenteDoProjeto);
			     
			    //vamos criar a barrinha de progresso para a etapa 3: calcular numero de paginas
				    String textoBarraDeProgresso = "Gerando PDF...";
				    SingletonBarraDeProgresso.getInstance().inicializarBarraDeProgresso(textosLidos.size() - 1,textoBarraDeProgresso);
			      for(int i = 0; i < textosLidos.size(); i++)
			      {
			    	  SingletonBarraDeProgresso.getInstance().updateBarraDeProgresso(i);
			    	  String umTextoLido = textosLidos.get(i);
			    	  String umNomeArquivoLido = nomesDosArquivosLidos.get(i);
			    	  String labelParaHeader = umNomeArquivoLido;
			    	  
			    	  int quantasPaginasTemOArquivoLido = arquivosEQuantasPaginasElesTem.get(umNomeArquivoLido);
			    	  String umNomeArquivoLidoEPaginas;
			    	  
			    	  if(quantasPaginasTemOArquivoLido > 1)
			    	  {
			    		  umNomeArquivoLidoEPaginas = umNomeArquivoLido + " (" + quantasPaginasTemOArquivoLido + " páginas)"; 
			    	  }
			    	  else
			    	  {
			    		  umNomeArquivoLidoEPaginas = umNomeArquivoLido + " (" + quantasPaginasTemOArquivoLido + " página)"; 
			    	  }
			    	  String textoLido2 = umTextoLido.replaceAll("\\t", "        ");
				     
				      addContent(writer, document, textoLido2, "", quantasPaginasTemOArquivoLido, labelParaHeader);
			      }
			      document.close();
			      fos.close();
			      
			      SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
			      
			      return true;
			    } catch (Exception e) {
			      e.printStackTrace();
			      SingletonBarraDeProgresso.getInstance().tornarBarraDeProgressoInvisivel();
			      return false;
			    }
		}
		else
		{
			return false;
		}
		
	}
	
	
	 // iText allows to add metadata to the PDF which can be viewed in your Adobe
	  // Reader
	  // under File -> Properties
	  private static void addMetaData(Document document) {
	    document.addTitle("My first project to register");
	    document.addSubject("Using iText");
	    document.addKeywords("Java, PDF, iText");
	    document.addAuthor("Lars Vogel");
	    document.addCreator("Lars Vogel");
	  }

	  private static void addTitlePage(Document document, String tituloDoProjeto, String versaoDoProjeto, String nomeDosAutoresSeparadoPorVirgula,HashMap<String,Integer> extensoesEQuantosArquivosExistemDestaExtensao,String linguagens, LinkedList<String>tiposDeAlicacao, LinkedList<String> tiposDePrograma, String moduloComponenteDoProjeto)
	      throws DocumentException {
		  
	    Paragraph preface = new Paragraph();
	    preface.setAlignment(Element.ALIGN_CENTER);
	    addEmptyLine(preface, 1);
	    addEmptyLine(preface, 13);
	    // We add one empty line
	    addEmptyLine(preface, 1);
	    // Lets write a big header
	    Paragraph paragraph = new Paragraph(tituloDoProjeto, catFont);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);

	    
	    paragraph = new Paragraph("Versão: " + versaoDoProjeto, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		        smallBold);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);
	    paragraph = new Paragraph("Autor(es): " + nomeDosAutoresSeparadoPorVirgula, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		        smallBold);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);	
	    
	    //Parte de linguagens e tipos de aplicação e programa
	    paragraph = new Paragraph("Linguagem: " + linguagens, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		        smallBold);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);	
	    
	    //Parte de módulo
	    paragraph = new Paragraph("Módulo: " + moduloComponenteDoProjeto, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		        smallBold);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);	
	    
	    
	    String tiposDeProgramaSeparadosPorVirgula = "";
	    String tiposDeAplicacaoSeparadosPorVirgula = "";
	    
	    for(int i = 0; i < tiposDeAlicacao.size(); i++)
	    {
	    	tiposDeAplicacaoSeparadosPorVirgula = tiposDeAplicacaoSeparadosPorVirgula + tiposDeAlicacao.get(i);
	    	if(i < tiposDeAlicacao.size() - 1)
	    	{
	    		tiposDeAplicacaoSeparadosPorVirgula = tiposDeAplicacaoSeparadosPorVirgula + ",";
	    	}
	    }
	    for(int j = 0; j < tiposDePrograma.size(); j++)
	    {
	    	tiposDeProgramaSeparadosPorVirgula = tiposDeProgramaSeparadosPorVirgula + tiposDePrograma.get(j);
	    	if(j < tiposDePrograma.size() - 1)
	    	{
	    		tiposDeProgramaSeparadosPorVirgula = tiposDeProgramaSeparadosPorVirgula + ",";
	    	}
	    }
	    
	    paragraph = new Paragraph("Campos de aplicação: " + tiposDeAplicacaoSeparadosPorVirgula, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		        smallBold);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);	
	    paragraph = new Paragraph("Tipos de programa: " + tiposDeProgramaSeparadosPorVirgula, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		        smallBold);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);	
	    
	    
	   
	    
	    
	  //PARTE QUE ESCREVE QUANTOS ARQUIVOS DE QUANTAS EXTENSOES
	    addEmptyLine(preface, 1);
	    paragraph = new Paragraph("Arquivos-fonte", redFont);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);
	    
	    Iterator<String> iteraExtensoes = extensoesEQuantosArquivosExistemDestaExtensao.keySet().iterator();
	    int quantosArquivosSaoNoTotal = 0; //tb vou sair pegando quantos arquivos sao no total para escrever
	    while(iteraExtensoes.hasNext() == true)
	    {
	    	String umaExtensao = iteraExtensoes.next();
	    	int quantosArquivosDaExtensao = extensoesEQuantosArquivosExistemDestaExtensao.get(umaExtensao);
	    	paragraph = new Paragraph(umaExtensao + ": "  + quantosArquivosDaExtensao + " arquivos", redFont);
		    paragraph.setAlignment(Element.ALIGN_CENTER);
		    quantosArquivosSaoNoTotal = quantosArquivosSaoNoTotal + quantosArquivosDaExtensao;
		    preface.add(paragraph);
	    }
	    
	    //falta escrever no documento quantos arquivos eram no total!
	    addEmptyLine(preface, 1);
	    paragraph = new Paragraph("Total: "  + quantosArquivosSaoNoTotal + " arquivos", redFont);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);
	    //FIM DA PARTE QUE ESCREVE QUANTOS ARQUIVOS DE QUANTAS EXTENSOES
	    
	    
	    addEmptyLine(preface, 3);
	    addEmptyLine(preface, 3);
	    
	    paragraph = new Paragraph("Documentação gerada por " + nomeDaFerramentaNaoBugado + "(www.code2inpi.pairg.ufrn.br)", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		        smallBold);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);
	    String data = "";
	    Calendar c = Calendar.getInstance();
	    
	    int dia = c.get(Calendar.DAY_OF_MONTH);
	    String diaEmString = String.valueOf(dia);
	    if(dia < 10)
	    {
	    	diaEmString = "0" + String.valueOf(dia);
	    }
	    
	    int mes = c.get(Calendar.MONTH);
	    mes = mes + 1;
	    String mesEmString = String.valueOf(mes);
	    if(mes < 10)
	    {
	    	mesEmString = "0" + String.valueOf(mes);
	    }
	    
	    int hora = c.get(Calendar.HOUR_OF_DAY);
	    String horaEmString = String.valueOf(hora);
	    if(dia < 10)
	    {
	    	horaEmString = "0" + String.valueOf(hora);
	    }
	    
	    int minutos = c.get(Calendar.MINUTE);
	    String minutosEmString = String.valueOf(minutos);
	    if(minutos < 10)
	    {
	    	minutosEmString = "0" + String.valueOf(minutos);
	    }
	    
	    int segundos = c.get(Calendar.SECOND);
	    String segundosEmString = String.valueOf(segundos);
	    if(segundos < 10)
	    {
	    	segundosEmString = "0" + String.valueOf(segundos);
	    }
	    
	    
	    data = data + diaEmString + "/" + mesEmString + "/" + c.get(Calendar.YEAR) + " às " + horaEmString + ":" + minutosEmString + ":" + segundosEmString;
	    paragraph = new Paragraph("Em "  + data, smallBold);
	    paragraph.setAlignment(Element.ALIGN_CENTER);
	    preface.add(paragraph);
	    

	    document.add(preface);
	  }

	  private static void addContent(PdfWriter writer, Document document, String textoArquivoLido, String nomeDoArquivoLido, int quantasPaginasTemArquivo, String labelParaHeader) throws DocumentException 
	  {
		document.newPage();
		document.setPageCount(1);
		if(event != null)
		{
			event.setQuantidadeTotalDePaginas(quantasPaginasTemArquivo);
			//writer.resetPageCount();
			event.setTextoDoHeader(labelParaHeader);
			File arquivoLido = new File(labelParaHeader);
			String apenasNomeDoArquivoSemPath = arquivoLido.getName();
			event.setNomeDoArquivoProHeader(apenasNomeDoArquivoSemPath);
		}
		
	    Anchor anchor = new Anchor(nomeDoArquivoLido, redFont);
	    anchor.setName(nomeDoArquivoLido);

	    // Second parameter is the number of the chapter
	    if(nomeDoArquivoLido.compareTo("") != 0)
	    {
	    	Chapter catPart = new Chapter(new Paragraph(anchor), 1);

		    Paragraph p = new Paragraph(textoArquivoLido, smallFont);
		    //p.setTabSettings(new TabSettings(56f));
		    catPart.add(p);
		    // now add all this to the document
		    document.add(catPart);
	    }
	    else
	    {
	    	//sem capítulos
	    	 Paragraph p = new Paragraph(textoArquivoLido, smallFont);
	    	 document.add(p);
	    	
	    }
	    
	    

	  }
	  
	  private static void addEmptyLine(Paragraph paragraph, int number) {
		    for (int i = 0; i < number; i++) {
		      paragraph.add(new Paragraph(" "));
		    }
		  }
	  
	  
	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		 try
		  {
			  File arquivoPdfGerar = new File(Main.outputFILE);
			  File arquivopdfGerarComNumeroDePaginas = new File(Main.outputFILE2);
		      /*PrintStream ps = new PrintStream(fileOutputStream);
			  System.setOut(ps);*/
			  LinkedList<String> nomesArquivosLidos = new LinkedList<String>();
			  LinkedList<String> textosArquivosLidos = new LinkedList<String>();
			  String url = "C:\\Users\\FábioPhillip\\Documents\\GitHub\\sumosensei\\src\\bancodedados\\ArmazenaKanjisPorCategoria.java";
			  String nomeProjeto = "sumosensei";
			  String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(url);
			  String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(url, nomeProjeto);
			  nomesArquivosLidos.add(nomeArquivoLido);
			  textosArquivosLidos.add(arquivoLido);
			  url = "C:\\Users\\FábioPhillip\\Documents\\GitHub\\sumosensei\\src\\armazenamentointerno\\ConcreteDAOArmazenaInternamenteDadosDePartidasRealizadas.java";
			  nomeProjeto = "sumosensei";
			  arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(url);
			  nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(url, nomeProjeto);
			  nomesArquivosLidos.add(nomeArquivoLido);
			  textosArquivosLidos.add(arquivoLido);
			  
			  String nomeDosAutoresSeparadosPorVirgula = "Fábio Andrews, Fábio Phillip";
			  String versaoDoProjeto = "1.0";
			  
			  HashMap<String,Integer> extensoesEQuantosArquivosExistemDestaExtensao = new HashMap<String, Integer>();
			  
			  LinkedList<String> tiposDeAplicacao = new LinkedList<String>();
			  LinkedList<String> tiposDePrograma = new LinkedList<String>();
			  tiposDeAplicacao.add("AD01- Administr");
			  tiposDeAplicacao.add("AD02- FunçãoAdm");
			  tiposDePrograma.add("SO01-Sist Operac");
			  
			  String linguagens = "java,xml";
			  
			  String moduloComponente = "Cliente";
			  
			  GeraPDFDeStringVariosArquivos geradorPdf = new GeraPDFDeStringVariosArquivos();
			  geradorPdf.gerarPDFDeStringVariosArquivosComNumeroDePaginas(textosArquivosLidos, nomeProjeto, nomesArquivosLidos,arquivoPdfGerar,arquivopdfGerarComNumeroDePaginas,nomeProjeto,versaoDoProjeto,nomeDosAutoresSeparadosPorVirgula,extensoesEQuantosArquivosExistemDestaExtensao,linguagens,tiposDeAplicacao,tiposDePrograma, moduloComponente);
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }

	}

	public String getNomeDaFerramenta() {
		return nomeDaFerramenta;
	}

	public void setNomeDaFerramenta(String nomeDaFerramenta) {
		this.nomeDaFerramenta = nomeDaFerramenta;
	}

}