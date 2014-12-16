package mayzel.weather;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.google.gson.Gson;

public class WeatherFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public WeatherFrame() throws IOException {
		setSize(400, 400);
		setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);
		container.setBackground(Color.BLUE);

		Container northContainer = new Container();
		northContainer.setLayout(new FlowLayout());
		northContainer.add(new JLabel("WEATHER"));
		container.add(northContainer, BorderLayout.NORTH);

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
		WeatherNow wn = gson.fromJson(json, WeatherNow.class);
		double numWeather = wn.getMain().getTemp();
		double min = wn.getMain().getTemp_min();
		double max = wn.getMain().getTemp_max();

		String temp = "   TEMP: " + String.valueOf(numWeather) + " MIN: " + String.valueOf(min) + " MAX: "
				+ String.valueOf(max);
		JLabel label = new JLabel(temp);

		Weather[] icons = wn.getWeather();
		String icon = icons[0].getIcon();
		URL imageUrl = new URL("http://openweathermap.org/img/w/" + icon + ".png");
		URLConnection imageConnection = imageUrl.openConnection();
		InputStream inputImage = imageConnection.getInputStream();
		Image image = ImageIO.read(inputImage);

		JLabel label2 = new JLabel(new ImageIcon(image));
		label.setBackground(Color.GREEN);
		label2.setBackground(Color.GREEN);
		label.setOpaque(true);
		label2.setOpaque(true);
		container.add(label, BorderLayout.WEST);
		container.add(label2, BorderLayout.CENTER);

		String weatherMain = icons[0].getMain();
		String weatherDescription = icons[0].getDescription();
		JLabel label3 = new JLabel(weatherMain + " - " + weatherDescription + "   ");
		label3.setBackground(Color.GREEN);
		label3.setOpaque(true);

		container.add(label3, BorderLayout.EAST);

	}

	public static void main(String args[]) throws IOException {

		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);

	}

}