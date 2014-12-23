package mayzel.weather;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import com.google.gson.Gson;

public class WeatherDownloadThread extends Thread {

	@Override
	public void run() {
		try {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Brooklyn&units=imperial");
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
			WeatherNow now = gson.fromJson(json, WeatherNow.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
