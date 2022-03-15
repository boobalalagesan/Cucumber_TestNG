package com.cucumber.api;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
public class Weather {
	public int getTemp(String location) throws IOException{
		URL url=new URL("https://api.openweathermap.org/data/2.5/weather?q="+location+"&units=metric&appid=7fe67bf08c80ded756e598d6f8fedaea");
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		int resCode= connection.getResponseCode();
		String resStatus=connection.getResponseMessage();

		//System.out.println("Response code "+resCode+" Response Status "+resStatus);
		/*
		 * InputStream inputStream=connection.getInputStream(); InputStreamReader
		 * inputStreamReader=new InputStreamReader(i);
		 */
		BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuffer buffer=new StringBuffer();
		while((line=bufferedReader.readLine())!=null){
			buffer.append(line);
		}
		//System.out.println(buffer);
		JSONObject jsonObject = new JSONObject(buffer.toString()); 
		JSONObject jsonObject2 =jsonObject.getJSONObject("main");
		double temp=jsonObject2.getFloat("temp");
		//System.out.println(temp);
		
		int expectedTemp=(int)(Math.round(temp));
		//System.out.println(expectedTemp);
		return expectedTemp ;
	}
	public static void main(String[] args) throws IOException {
		String location="Salem";
		Weather weather=new Weather();
		int responseValue= weather.getTemp(location);
		System.out.println(responseValue);
	}

}

