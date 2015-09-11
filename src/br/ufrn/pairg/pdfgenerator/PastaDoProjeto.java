package br.ufrn.pairg.pdfgenerator;

public class PastaDoProjeto {

	private String nomeDaPasta;
	private String urlDaPasta;
	
	public PastaDoProjeto() {
		// TODO Auto-generated constructor stub
	}
	
	public PastaDoProjeto(String nomePasta, String urlPasta)
	{
		this.nomeDaPasta = nomePasta;
		this.urlDaPasta = urlPasta;
	}
	
	public String getNomeDaPasta() {
		return nomeDaPasta;
	}
	public void setNomeDaPasta(String nomeDaPasta) {
		this.nomeDaPasta = nomeDaPasta;
	}
	public String getUrlDaPasta() {
		return urlDaPasta;
	}
	public void setUrlDaPasta(String urlDaPasta) {
		this.urlDaPasta = urlDaPasta;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nomeDaPasta;
	}
	
	

}
