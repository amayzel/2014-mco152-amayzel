package mayzel.opportunity;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class NasaImageThread extends Thread {

	private String url;
	private JLabel main;
	private JButton button;
	private JButton button2;
	public NasaImageThread(String url, JLabel main, JButton button, JButton button2) {
		this.url = url;
		this.main = main;
		this.button = button;
		this.button2 = button2;
	}

	@Override
	public void run(){
		try {
			final ImageIcon image = new ImageIcon(new URL(url));
			//for(int i=0;i<url.length;i++){
			//	 image[i] = new ImageIcon(new URL(url[i]));
			//}
			
			ActionListener listener = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					JButton button = (JButton) event.getSource();
					//for(int i=0;i<image.length;i++){
					//	main.setIcon(image[i]);
					//}
					main.setIcon(image);
				}

			};
			
			button.addActionListener(listener);
			

			ActionListener listener2 = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent event) {
					JButton button2 = (JButton) event.getSource();
					//for(int i=0;i<image.length;i--){
						//main.setIcon(image[i]);
					//}
				}

			};
			
			button2.addActionListener(listener2);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
