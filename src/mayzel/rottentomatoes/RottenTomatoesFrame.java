package mayzel.rottentomatoes;

import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class RottenTomatoesFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	public RottenTomatoesFrame() {
		setSize(400, 400);
		setTitle("Rotten Tomatoes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		Container container = getContentPane();
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);
		Container left =  new Container();
		container.add(left, BorderLayout.WEST);
		Container right =  new Container();
		container.add(right, BorderLayout.EAST);
		JLabel img = new JLabel();
		
		
		RottenTomatoesThread thread = new RottenTomatoesThread(img, "Toy Story 3");
		thread.start();
		
		left.add(img);
		
	}
	
	
	public static void main(String[] args){
		RottenTomatoesFrame rt = new RottenTomatoesFrame();
		rt.setVisible(true);
	}

}
