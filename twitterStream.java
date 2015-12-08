package com.twitter;

import org.scribe.oauth.*;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.builder.ServiceBuilder;
//import org.scribe.builder.api.Api;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;

public class TwitterStream extends Thread {
	
	private static final String STREAM_URL = "https://stream.twitter.com/1.1/statuses/filter.json";
	
	public void run(){
		try {
			
			System.out.println("Strating thread");
			
			OAuthService service = new ServiceBuilder()
					.provider(TwitterApi.class)
					.apiKey("you_key_here")
					.apiSecret("your_secret_key_here")
					.build();
			
			Token acessToken = new Token("acess_token", "access_token");
			
			
			System.out.println("connecting to stream");
			
			
			OAuthRequest request = new OAuthRequest(Verb.POST, STREAM_URL);
			request.addHeader("version", "HTTP/1.1");
			request.setConnectionKeepAlive(true);
		
			
			
			
			request.addBodyParameter("track", "#travel");
			
			service.signRequest(acessToken, request);
			Response response = request.send();
		    
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(response.getStream()));
			PrintWriter out = new PrintWriter(new FileWriter("E:/Tasks/05-10/out/out.txt"));
			
		/*	String line;
			while((line = reader.readLine())!= null ) {
				
				JSONParser parser = new JSONParser();
				Object obj = parser.parse(line.toString());
				JSONObject jsonObject = (JSONObject) obj;
				String name = (String) jsonObject.get("text");
				System.out.println(name); 
				
				//System.out.println(line);
				
			}  */
			out.close();
			
			
		}
		
		catch (IOException ioe) {
			
			ioe.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	
		
	}

}
