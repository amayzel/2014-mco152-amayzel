package mayzel.earthquakes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JFrame;
import javax.swing.JList;

import com.google.gson.Gson;

public class EarthquakeFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public EarthquakeFrame() throws IOException {
		setSize(400, 400);
		setTitle("Earthquakes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);
		container.setBackground(Color.BLUE);
		
		
		URL url = new URL("http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/significant_month.geojson");
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

		Earthquakes now = gson.fromJson(json, Earthquakes.class);
		Features[] feature = now.getFeatures();
		String info[] = new String[feature.length];
		
		for (int i = 0; i < now.getFeatures().length; i++) {
			info[i] = feature[i].getProperties().getPlace() +"         " +feature[i].getProperties().getMag();
		}
		
		JList<String> list = new JList<String>(info);
		container.add(list, BorderLayout.CENTER);
		list.setBackground(Color.CYAN);

		
	}

	public static void main(String[] args) throws IOException {
		EarthquakeFrame earthquake = new EarthquakeFrame();
		earthquake.setVisible(true);
	}

}
