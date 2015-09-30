package br.ufrn.pairg.pdfgenerator;

/*nao sera mostrado o 'gerado pdf com sucesso' quando o projeto conter um arquivo com o id impossivel de gerar numero de paginas e ele decidir n criar o pdf*/
public class SingletonPDFGeradoComSucessoDeveSerMostrado 
{
	private static SingletonPDFGeradoComSucessoDeveSerMostrado instance;
	private boolean deveSerMostrado;
	
	private SingletonPDFGeradoComSucessoDeveSerMostrado()
	{
		deveSerMostrado = true;
	}
	
	public static SingletonPDFGeradoComSucessoDeveSerMostrado getInstance()
	{
		if(instance == null)
		{
			instance = new SingletonPDFGeradoComSucessoDeveSerMostrado();
		}
		
		return instance;
	}

	public boolean getDeveSerMostrado() {
		return deveSerMostrado;
	}

	public void setDeveSerMostrado(boolean deveSerMostrado) {
		this.deveSerMostrado = deveSerMostrado;
	}
	
	

}
