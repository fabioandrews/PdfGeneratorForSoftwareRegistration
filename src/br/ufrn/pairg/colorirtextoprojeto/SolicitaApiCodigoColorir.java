package br.ufrn.pairg.colorirtextoprojeto;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class SolicitaApiCodigoColorir {

	/**
	 * @param args
	 */
	public static String colorirCodigo(String codigo, String extensaoArquivoSemPonto) 
	{
		StringBuilder result = new StringBuilder();
	      URL url;
		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://hilite.me/api");

			// Request parameters and other properties.
			List<NameValuePair> params = new ArrayList<NameValuePair>(2);
			params.add(new BasicNameValuePair("code", codigo));
			params.add(new BasicNameValuePair("Lexer", extensaoArquivoSemPonto));
			httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

			//Execute and get the response.
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
			    InputStream instream = entity.getContent();
			    try {
			    	BufferedReader bReader = new BufferedReader(new InputStreamReader(instream, "UTF-8"));
		            StringBuilder sBuilder = new StringBuilder();

		            String line = null;
		            while ((line = bReader.readLine()) != null) {
		            	if(line.startsWith("<meta") == false)//pula linha de metadados
		            	{
		            		 sBuilder.append(line + "\n");
		            	}
		               
		            }

		            String resultadoString = sBuilder.toString();
					
				    return resultadoString;
			    } finally {
			        instream.close();
			    }
			}
			else
			{
				return null;
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	     

	}

}
