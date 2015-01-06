package mayzel.iss;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import mayzel.earthquakes.Earthquakes;

import com.google.gson.Gson;

public class ISSOverheadFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public ISSOverheadFrame() throws IOException{
		setSize(400, 400);
		setTitle("ISS Overhead");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);
		container.setBackground(Color.BLUE);
		
		JTextField textField = new JTextField(20);
		container.add(textField, BorderLayout.PAGE_START);
		String address = URLEncoder.encode(textField.getText(), "UTF-8");
		JButton button = new JButton("Get Times");
		container.add(button,BorderLayout.PAGE_START);
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				//make button get list
			}

		};
		button.addActionListener(listener);
		
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
		
		JList<String> list = new JList<String>(date);
		list.setBackground(Color.BLUE);
		container.add(list, BorderLayout.CENTER);
		
		
	}

	public static void main(String[] args) throws IOException {
		ISSOverheadFrame iss = new ISSOverheadFrame();
		iss.setVisible(true);
	}

}
