package br.ufrn.pairg.pdfgenerator;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes 
{
	//extensoes nao precisam ser .java, podemser soh java mesmo, por exemplo
	public LinkedList<String> pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(String urlPasta,LinkedList<String> extensoes)
	{
		File dir = new File(urlPasta);
		String[] extensions = new String[extensoes.size()];
		for(int i = 0; i < extensoes.size(); i++)
		{
			extensions[i] = extensoes.get(i);
		}
		
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		LinkedList<String> caminhosDeArquivos = new LinkedList<String>();
		
		try 
		{
			for (File file : files) 
			{
				caminhosDeArquivos.add(file.getCanonicalPath());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return caminhosDeArquivos;
		
	}
	
	public static void main(String[] args) throws IOException {

		/*File dir = new File("C:/Users/fábioandrews/Desktop/adt-bundle-windows-x86-20130717/adt-bundle-windows-x86_64-20131030/adt-bundle-windows-x86_64-20131030/eclipse/projetos/KarutaKanji");
		String[] extensions = new String[] { "java" };
		System.out.println("Getting all files from that extension in " + dir.getCanonicalPath()
				+ " including those in subdirectories");
		List<File> files = (List<File>) FileUtils.listFiles(dir, extensions, true);
		for (File file : files) {
			System.out.println("file: " + file.getCanonicalPath());
		}*/
		
		LinkedList<String> extensoes = new LinkedList<String>();
		extensoes.add("java");
		
		PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes pega = new PegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes();
		String urlPasta = "C:/Users/fábioandrews/Desktop/adt-bundle-windows-x86-20130717/adt-bundle-windows-x86_64-20131030/adt-bundle-windows-x86_64-20131030/eclipse/projetos/KarutaKanji";
		LinkedList<String> caminhosArquivos = 
				pega.pegaTodosOsCaminhosDeArquivosNaPastaComBaseNasExtensoes(urlPasta, extensoes);

		System.out.println("///Caminhos dos arquivos na pasta:" + urlPasta);
		
		for(int i = 0; i < caminhosArquivos.size(); i++)
		{
			System.out.println(caminhosArquivos.get(i));
		}
	}

}
