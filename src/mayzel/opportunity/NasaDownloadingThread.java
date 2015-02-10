package mayzel.opportunity;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class NasaDownloadingThread extends Thread {

	private JLabel main;
	private JButton button;
	private JButton button2;
	public NasaDownloadingThread(JLabel main,JButton button, JButton button2) {
		this.main = main;
		this.button = button;
		this.button2 = button2;
	}

	@Override
	public void run() {
		try {
			URL url = new URL("http://merpublic.s3.amazonaws.com/oss/mera/images/images_sol13.json");
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			String json = IOUtils.toString(in);
			Gson gson = new Gson();
			Nasa now = gson.fromJson(json, Nasa.class);
			ImageData[] pics1 = now.getFcam_images();
			Images [] pictures1 = new Images[pics1.length];
			String urls;
			//String []manyUrls = new String[pictures1.length];
			for(int i=0;i<pics1.length;i++){
				pictures1 = pics1[i].getImages();
				for(int j=0;j<pictures1.length;j++){
					urls = pictures1[j].getUrl();
					//manyUrls[j] = urls; 
					NasaImageThread imageThread = new NasaImageThread(urls, main, button, button2);
					imageThread.start();
					
				}
			}
			//NasaImageThread imageThread = new NasaImageThread(manyUrls, main, button, button2);
			//imageThread.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
