//learn guifrom a classmate
package mayzel.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WeatherFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public WeatherFrame() {
		// set up happens here for encapsulation - not everything is public
		setSize(400, 400);
		setTitle("Current Weather");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		// a container holds components
		Container container = getContentPane();
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);
		Container northContainer = new Container();
		northContainer.setLayout(new FlowLayout());
		northContainer.add(new JLabel("Container inside a container!"));
		northContainer.add(new JLabel("So cool!"));
		// replacing with a container inside a container cuz this doesn't work!
		// container.add(new JLabel("Hello World"), BorderLayout.NORTH);
		// container.add(new JLabel("What happens?"), BorderLayout.NORTH);
		container.add(northContainer, BorderLayout.NORTH);
		container.add(new JLabel("This class rocks!"), BorderLayout.SOUTH);
		JLabel label = new JLabel("Thanks for your silence");
		label.setBackground(Color.GREEN);
		label.setOpaque(true);
		container.add(label, BorderLayout.CENTER);
		container.add(new JLabel("Happy Birthday"), BorderLayout.WEST);
		container.add(new JLabel("Happy Tuesday"), BorderLayout.EAST);
	}
	
	public static void main(String args[]) {

		WeatherFrame frame = new WeatherFrame();
		frame.setVisible(true);

	}

}