package mayzel.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;

import com.google.gson.Gson;

public class CurrentWeather extends JFrame{

	public static void main(String[] args) throws IOException {

		URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Brooklyn&units=imperial");
		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		StringBuilder builder = new StringBuilder();

		byte b[] = new byte[4096];
		int n = -1;
		while ((n = in.read(b)) != -1) {
			// n is amount of bytes returned up till 4096
			builder.append(new String(b, 0, n));
			
		}
		
		String json = builder.toString();
		Gson gson = new Gson();
		WeatherNow now = gson.fromJson(json, WeatherNow.class);
		
		System.out.println(now.getMain().getTemp());
		
		
	}
}
