package mayzel.opportunity;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
//I know that this is incomplete code. I apologize for that.

public class NasaFrame extends JFrame {

	public NasaFrame() {
		setSize(400, 400);
		setTitle("MERA Opportunity Rover");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		BorderLayout layout = new BorderLayout();
		container.setLayout(layout);

		JLabel label = new JLabel();
		container.add(label, BorderLayout.CENTER);

		Container bottom = new Container();
		BorderLayout lay = new BorderLayout();
		bottom.setLayout(lay);

		container.add(bottom, BorderLayout.SOUTH);
		
		JButton rbutton = new JButton("<");
		bottom.add(rbutton, BorderLayout.WEST);
		
		JLabel count = new JLabel("                                            1 of 255");
		bottom.add(count, BorderLayout.CENTER);
		
		JButton lbutton = new JButton(">");
		bottom.add(lbutton, BorderLayout.EAST);
		/*JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 255, 122);

		slider.setMinorTickSpacing(100);
		slider.setMajorTickSpacing(100);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);

		slider.setLabelTable(slider.createStandardLabels(50));

		add(slider, BorderLayout.SOUTH);*/

		NasaDownloadingThread thread = new NasaDownloadingThread(label,rbutton,lbutton);
		thread.start();
	}

	public static void main(String[] args) {
		NasaFrame frame = new NasaFrame();
		frame.setVisible(true);
	}

}
