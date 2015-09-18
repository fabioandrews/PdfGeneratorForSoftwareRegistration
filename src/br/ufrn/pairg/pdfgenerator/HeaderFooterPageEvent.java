package br.ufrn.pairg.pdfgenerator;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
public class HeaderFooterPageEvent extends PdfPageEventHelper {
	private Font footerFont = new Font(Font.FontFamily.COURIER, 9,
		      Font.NORMAL);
	private String textoDoHeader = "umHeaderLegal";
	private volatile int quantidadeTotalDePaginas = -1;
	 
	 
    public void onStartPage(PdfWriter writer,Document document) {
    	 PdfContentByte cb = writer.getDirectContent();
         ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(textoDoHeader, footerFont), (document.left() + document.right())/2 , document.top()+20, 0);
    }
    public void onEndPage(PdfWriter writer,Document document) {
    	 PdfContentByte cb = writer.getDirectContent();
    	 if(quantidadeTotalDePaginas > 0)
    	 {
    		 ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase(String.format("Page %d / %d", writer.getPageNumber(), quantidadeTotalDePaginas),footerFont), (document.left() + document.right())/2 , document.bottom()-20, 0);
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
	
	
    
    
} 
