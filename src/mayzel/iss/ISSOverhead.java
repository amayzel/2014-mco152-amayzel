package mayzel.iss;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;

import com.google.gson.Gson;

public class ISSOverhead {
	
	public static void main(String[] args) throws IOException{
		//Scanner keyboard = new Scanner(System.in);
		//System.out.println("Enter an address");
		String address = URLEncoder.encode("82 80 116th street kew gardens ny 11418","UTF-8");
		
		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+address+"&key=AIzaSyCWVQ_LlkxwMyS6Acv4FyQKI1KBkyXkjxw");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		StringBuilder builder = new StringBuilder();
		
		byte b[] = new byte[4096];
		int n = -1;
		while ((n = in.read(b)) != -1) {
			builder.append(new String(b, 0, n));
		}

		String json = builder.toString();
		Gson gson = new Gson();

		ISS now = gson.fromJson(json, ISS.class);
		Results [] results = now.getResults();
		
		double lat = results[0].getGeometry().getLocation().getLat();
		double lng = results[0].getGeometry().getLocation().getLng();
		
		URL url2 = new URL("http://api.open-notify.org/iss-pass.json?lat="+ String.valueOf(lat) +"&lon=" + String.valueOf(lng));
		URLConnection connection2 = url2.openConnection();
		InputStream in2 = connection2.getInputStream();
		StringBuilder builder2 = new StringBuilder();
		
		byte b2[] = new byte[4096];
		int n2 = -1;
		while ((n2 = in2.read(b2)) != -1) {
			builder2.append(new String(b2, 0, n2));
		}

		String json2 = builder2.toString();
		Gson gson2 = new Gson();
		
		ISSOverheads now2 =  gson2.fromJson(json2, ISSOverheads.class);
		Response[] response = now2.getResponse();
		int seconds[] = new int[response.length];
		String[] date = new String[seconds.length];
		
		for (int j = 0; j < seconds.length; j++) {
			seconds[j] = response[j].getRisetime();
			DateFormat pstFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pstFormat.setTimeZone(TimeZone.getTimeZone("EST"));
			date[j] = pstFormat.format(seconds[j]);
		}
		
		for(int i=0;i<date.length;i++){
			System.out.println(date[i] +" EST");
		}

		
	}

}
