package br.ufrn.pairg.pdfgenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeraPDFDeString {

	 private static String FILE = "c:/temp/FirstPdf.pdf";
	  private static Font catFont = new Font(Font.FontFamily.COURIER, 18,
	      Font.BOLD);
	  private static Font redFont = new Font(Font.FontFamily.COURIER, 12,
	      Font.NORMAL, BaseColor.RED);
	  private static Font subFont = new Font(Font.FontFamily.COURIER, 16,
	      Font.BOLD);
	  private static Font smallBold = new Font(Font.FontFamily.COURIER, 12,
	      Font.BOLD);
	  private static Font smallFont = new Font(Font.FontFamily.COURIER, 9,
		      Font.NORMAL);
	
	  /**
	   * gera o PDF de UM �nico arquivo j� lido 
	   * @param textoLido texto lido pelo buffer
	   * @param nomeDoArquivoLido nome do arquivo lido, j� obtido pelo LeitorArquivoTexto.java
	   * @return
	   */
	public static boolean gerarPDFDeString(String textoLido, String nomeDoArquivoLido,FileOutputStream fos)
	{
		 try {
			  Document document = new Document();
		      PdfWriter.getInstance(document, fos);
		      document.open();
		      addMetaData(document);
		      addTitlePage(document);
		     
		      String textoLido2 = textoLido.replaceAll("\\t", "        ");
		      
		      addContent(document, textoLido2, nomeDoArquivoLido);
		      document.close();
		      return true;
		    } catch (Exception e) {
		      e.printStackTrace();
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

	  private static void addTitlePage(Document document)
	      throws DocumentException {
	    Paragraph preface = new Paragraph();
	    // We add one empty line
	    addEmptyLine(preface, 1);
	    // Lets write a big header
	    preface.add(new Paragraph("Nome Do Projeto", redFont));

	    addEmptyLine(preface, 1);
	    // Will create: Report generated by: _name, _date
	    preface.add(new Paragraph("Report generated by: " + System.getProperty("user.name") + ", " + new Date(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	        smallBold));
	    addEmptyLine(preface, 3);
	    

	    document.add(preface);
	    // Start a new page
	    document.newPage();
	  }

	  private static void addContent(Document document, String textoArquivoLido, String nomeDoArquivoLido) throws DocumentException {
	    Anchor anchor = new Anchor(nomeDoArquivoLido, redFont);
	    anchor.setName(nomeDoArquivoLido);

	    // Second parameter is the number of the chapter
	    Chapter catPart = new Chapter(new Paragraph(anchor), 1);

	    Paragraph p = new Paragraph(textoArquivoLido, smallFont);
	    //p.setTabSettings(new TabSettings(56f));
	    catPart.add(p);

	    


	    

	    // now add all this to the document
	    document.add(catPart);


	  }

	  private static void createTable(Section subCatPart)
	      throws BadElementException {
	    PdfPTable table = new PdfPTable(3);

	    // t.setBorderColor(BaseColor.GRAY);
	    // t.setPadding(4);
	    // t.setSpacing(4);
	    // t.setBorderWidth(1);

	    PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Table Header 2"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);

	    c1 = new PdfPCell(new Phrase("Table Header 3"));
	    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
	    table.addCell(c1);
	    table.setHeaderRows(1);

	    table.addCell("1.0");
	    table.addCell("1.1");
	    table.addCell("1.2");
	    table.addCell("2.1");
	    table.addCell("2.2");
	    table.addCell("2.3");

	    subCatPart.add(table);

	  }

	  private static void createList(Section subCatPart) {
	    List list = new List(true, false, 10);
	    list.add(new ListItem("First point"));
	    list.add(new ListItem("Second point"));
	    list.add(new ListItem("Third point"));
	    subCatPart.add(list);
	  }

	  private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	      paragraph.add(new Paragraph(" "));
	    }
	  }
	  
	  public static void main(String args [])
	  {
		  /*File file = new File("out.txt");
		  FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			PrintStream ps = new PrintStream(fos);
			System.setOut(ps);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		  
		  try
		  {
			  FileOutputStream fileOutputStream = new FileOutputStream(FILE);
		      /*PrintStream ps = new PrintStream(fileOutputStream);
			  System.setOut(ps);*/
			  
			  String url = "C:\\Users\\F�bioPhillip\\Documents\\GitHub\\sumosensei\\src\\armazenamentointerno\\ConcreteDAOArmazenaInternamenteDadosDePartidasRealizadas.java";
			  String nomeProjeto = "PdfGeneratorForSoftwareRegistration";
			  String arquivoLido = LeitorArquivoTexto.lerArquivoQualquerDeTexto(url);
			  String nomeArquivoLido = LeitorArquivoTexto.pegarNomeArquivo(url, nomeProjeto);
			  gerarPDFDeString(arquivoLido, nomeArquivoLido,fileOutputStream);
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
	  }

}
