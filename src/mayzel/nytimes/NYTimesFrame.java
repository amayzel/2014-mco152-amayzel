package mayzel.nytimes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.google.gson.Gson;

public class NYTimesFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public NYTimesFrame() throws IOException {
		setSize(1000, 300);
		setTitle("News");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);

		URL url = new URL(
				"http://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=4403ce968c76b4c15c993494ba455d68%3A14%3A70540368");
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

		NYTimes now = gson.fromJson(json, NYTimes.class);
		final Docs[] docs = now.getResponse().getDocs();
		String info[] = new String[10];
		final String urls[] = new String[10];
		int index = -1;

		for (int i = 0; i < info.length; i++) {
			info[i] = docs[i].getHeadline().getMain() + "         " + docs[i].getLead_paragraph();
			urls[i] = docs[i].getWeb_url();
		}

		final JList<String> list = new JList<String>(info);
		list.setBackground(Color.green);
		
		list.addListSelectionListener(new ListSelectionListener()
		  {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting() == false)
			      {
			        switch(list.getSelectedIndex())
			        {
			          case 0:
			            try {
							Desktop.getDesktop().browse(new URL(urls[0]).toURI());
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
			            break;
			          case 1:
						try {
							Desktop.getDesktop().browse(new URL(urls[1]).toURI());
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
				            break;
			          case 2:
						try {
							Desktop.getDesktop().browse(new URL(urls[2]).toURI());
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
				            break;
			          case 3:
						try {
							Desktop.getDesktop().browse(new URL(urls[3]).toURI());
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
				            break;
			          case 4:
						try {
							Desktop.getDesktop().browse(new URL(urls[4]).toURI());
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
				            break;
			          case 5:
						try {
							Desktop.getDesktop().browse(new URL(urls[5]).toURI());
						} catch (IOException e5) {
							e5.printStackTrace();
						} catch (URISyntaxException e5) {
							e5.printStackTrace();
						}
				            break;
			          case 6:
						try {
							Desktop.getDesktop().browse(new URL(urls[6]).toURI());
						} catch (IOException e4) {
							e4.printStackTrace();
						} catch (URISyntaxException e4) {
							e4.printStackTrace();
						}
				            break;
			          case 7:
						try {
							Desktop.getDesktop().browse(new URL(urls[7]).toURI());
						} catch (IOException e3) {
							e3.printStackTrace();
						} catch (URISyntaxException e3) {
							e3.printStackTrace();
						}
				            break;
			          case 8:
						try {
							Desktop.getDesktop().browse(new URL(urls[8]).toURI());
						} catch (IOException e2) {
							e2.printStackTrace();
						} catch (URISyntaxException e2) {
							e2.printStackTrace();
						}
				            break;
			          case 9:
						try {
							Desktop.getDesktop().browse(new URL(urls[9]).toURI());
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
				            break;
				            
			        }
			      }
			      else
			      {
			        System.out.println("I think the value is adjusting");
			      }
			}
		  });
		container.add(list, BorderLayout.CENTER);
		

	}
	

	public static void main(String[] args) throws IOException {
		NYTimesFrame nytimes = new NYTimesFrame();
		nytimes.setVisible(true);
	}

}
