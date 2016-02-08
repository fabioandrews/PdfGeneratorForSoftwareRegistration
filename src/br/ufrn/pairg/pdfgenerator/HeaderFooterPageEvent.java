package br.ufrn.pairg.pdfgenerator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
public class HeaderFooterPageEvent extends PdfPageEventHelper {
	private Font footerFont = new Font(Font.FontFamily.COURIER, 7,
		      Font.NORMAL);
	private String textoDoHeader = "umHeaderLegal";
	private String nomeDoArquivoProHeader;
	private volatile int quantidadeTotalDePaginas = -1;
	private int contadorTodasAsPaginasDoDocumento = 2;
	 
	 
    public void onStartPage(PdfWriter writer,Document document) {
    	 //PdfContentByte cb = writer.getDirectContent();
         //ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(textoDoHeader, footerFont), (document.left() + document.right())/2 , document.top(), 0);
    }
    public void onEndPage(PdfWriter writer,Document document) {
    	Rectangle rect = new Rectangle(36, 54, 559, 788);
    	Rectangle page = document.getPageSize();
    	
    	if(quantidadeTotalDePaginas > 0)
    	{
    		PdfContentByte cb = writer.getDirectContent();
    		BaseFont bf = FontFactory.getFont(FontFactory.COURIER).getCalculatedBaseFont(false);
    		cb.setFontAndSize(bf, 8);
    		/*cb.showTextAligned(
                    Element.ALIGN_LEFT, "Arquivo:" + nomeDoArquivoProHeader,
                    document.left(), page.getHeight()- document.topMargin() + 30, 0);*/
    		cb.showTextAligned(
                    Element.ALIGN_RIGHT, String.format("(Página %d / %d)", writer.getPageNumber(), quantidadeTotalDePaginas),
                    document.right(), page.getHeight()- document.topMargin() + 30, 0);
    		
    		final LineSeparator lineSeparator = new LineSeparator();
    		lineSeparator.setLineWidth((float) 0.5);
    		lineSeparator.drawLine(writer.getDirectContent(), document.left(), document.right(), page.getHeight()- document.topMargin() + 15);
    		
    		cb.showTextAligned(
                    Element.ALIGN_CENTER, String.format("%d", contadorTodasAsPaginasDoDocumento),
                    (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);
    		final LineSeparator lineSeparatorFim = new LineSeparator();
    		lineSeparatorFim.setLineWidth((float) 0.5);
    		lineSeparatorFim.drawLine(writer.getDirectContent(), document.left(), document.right(), rect.getBottom() - 5);
    		
    		contadorTodasAsPaginasDoDocumento = contadorTodasAsPaginasDoDocumento + 1;
    		//pro diretorio do arquivo, preciso mudar o tamanho da fonte
    		/*cb.showTextAligned(
                    Element.ALIGN_LEFT, "Caminho:" + textoDoHeader,
                    document.left(), page.getHeight()- document.topMargin() + 10, 0);*/
    		cb.showTextAligned(
                    Element.ALIGN_LEFT, textoDoHeader,
                    document.left(), page.getHeight()- document.topMargin() + 30, 0);
    		
    	}
        
    	
        
    }
	public String getTextoDoHeader() {
		return textoDoHeader;
	}
	public void setTextoDoHeader(String textoDoHeader) {
		this.textoDoHeader = textoDoHeader;
	}
	public int getQuantidadeTotalDePaginas() {
		return quantidadeTotalDePaginas;
	}
	public void setQuantidadeTotalDePaginas(int quantidadeTotalDePaginas) {
		this.quantidadeTotalDePaginas = quantidadeTotalDePaginas;
	}
	public String getNomeDoArquivoProHeader() {
		return nomeDoArquivoProHeader;
	}
	public void setNomeDoArquivoProHeader(String nomeDoArquivoProHeader) {
		this.nomeDoArquivoProHeader = nomeDoArquivoProHeader;
	}
	
	
	
	
    
    
} 
