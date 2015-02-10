package mayzel.rottentomatoes;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import org.apache.commons.io.IOUtils;
import com.google.gson.Gson;

public class RottenTomatoesThread extends Thread{
	
	private JLabel img;
	private String movie;
	public RottenTomatoesThread(JLabel img,String movie){
		this.img = img;
		this.movie = movie;
	}
	
	@Override
	public void run(){
		try {
			URL url = new URL("http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=[your_api_key]&q="+movie+"&page_limit=1");
			URLConnection connection = url.openConnection();
			InputStream in = connection.getInputStream();
			
			String json = IOUtils.toString(in);
			Gson gson = new Gson();
			RottenTomatoes now = gson.fromJson(json, RottenTomatoes.class);
			Movies[] movies = now.getMovies();
			
			URL url2 = new URL(movies[0].getPosters().getThumbnail());
			connection = url2.openConnection();
			in = connection.getInputStream();
			Image image = ImageIO.read(in);
			img.setIcon(new ImageIcon(image));


		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
}
