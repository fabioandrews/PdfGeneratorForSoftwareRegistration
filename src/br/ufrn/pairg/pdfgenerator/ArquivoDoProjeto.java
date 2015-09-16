package br.ufrn.pairg.pdfgenerator;

public class ArquivoDoProjeto {

	private String nomeDoArquivo;
	private String urlDoArquivo;
	private String extensaoDoArquivo;
	
	public ArquivoDoProjeto() {
		// TODO Auto-generated constructor stub
	}
	
	public ArquivoDoProjeto(String nomeArquivo, String urlArquivo)
	{
		this.nomeDoArquivo = nomeArquivo;
		this.urlDoArquivo = urlArquivo;
		if(this.nomeDoArquivo.contains("."))
		{
			int percorredorNomeArquivo = 0;
			while(this.nomeDoArquivo.charAt(percorredorNomeArquivo) != '.')
			{
				percorredorNomeArquivo = percorredorNomeArquivo + 1;
			}
			extensaoDoArquivo = this.nomeDoArquivo.substring(percorredorNomeArquivo);
		}
		else
		{
			extensaoDoArquivo = "";
		}
	}
	
	public String getNomeDaPasta() {
		return nomeDoArquivo;
	}
	public void setNomeDaPasta(String nomeDaPasta) {
		this.nomeDoArquivo = nomeDaPasta;
	}
	public String getUrlDoArquivo() {
		return urlDoArquivo;
	}
	public void setUrlDoArquivo(String urlDaPasta) {
		this.urlDoArquivo = urlDaPasta;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nomeDoArquivo;
	}

	public String getExtensaoDoArquivo() {
		return extensaoDoArquivo;
	}

	public void setExtensaoDoArquivo(String extensaoDoArquivo) {
		this.extensaoDoArquivo = extensaoDoArquivo;
	}
	
	
	

}
