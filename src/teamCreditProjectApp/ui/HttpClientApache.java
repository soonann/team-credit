package teamCreditProjectApp.ui;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class HttpClientApache {

	private final String USER_AGENT = "Mozilla/5.0";

	

	// HTTP GET request
	public void sendGet(String messagePart1,String messagePart2 ) throws Exception {
		
		try{
			
			
			
			String backEncoded = URLEncoder.encode(messagePart1,"UTF-8");
			String endEncoded = URLEncoder.encode(messagePart2,"UTF-8");
			
			String simpleUrl = "http://sms.sit.nyp.edu.sg/SMSWebService/sms.asmx/sendMessage?";
			
			String backPart = "SMSAccount=CC1&Pwd=623490&Mobile=91759586&Message=" + backEncoded + endEncoded;
			
		
			String url = simpleUrl + backPart;	
			
			System.out.println(backPart);
			
			HttpClient client = new DefaultHttpClient();
			
			HttpGet request = new HttpGet(url);
	
			// add request header
			request.addHeader("User-Agent", USER_AGENT);
	
			HttpResponse response = client.execute(request);
	
			System.out.println("\nSending 'GET' request to URL : " + url);
			//System.out.println("Response Code : " +response.getStatusLine().getStatusCode());
	/*
			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	
			StringBuffer result = new StringBuffer();
			String line = "";
			
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		System.out.println(result.toString());
		*/
		} catch(Exception e){
			
			if(e instanceof UnknownHostException || e instanceof NullPointerException){
				
				JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),e.getCause());	
				
			}
			
			
		}
	}

	
}
