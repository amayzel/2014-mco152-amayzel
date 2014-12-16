package mayzel.connectFour;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Connect4Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int ROWS = 6;
	public static final int COLS = 7;

	public Connect4Frame() {
		setSize(800, 600);
		setTitle("Connect 4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container container = getContentPane();
		container.setLayout(new GridLayout(ROWS, COLS));

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				if(button.getBackground() == Color.GREEN){
					button.setBackground(Color.PINK);
				}
				else{
					button.setBackground(Color.GREEN);
				}
			}

		};

		for (int i = 0; i < ROWS * COLS; i++) {
			final JButton button = new JButton();
			container.add(button);
			button.addActionListener(listener);
		}
	}

	public static void main(String args[]) {
		Connect4Frame game = new Connect4Frame();
		game.setVisible(true);
	}
}
