package mayzel.gameOfLife;
//finish this code
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class GameOfLife extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final int ROWS = 40;
	public static final int COLS = 40;
	private JButton cells[][];
	private JButton nextCells [][];

	public GameOfLife() {
		setSize(800, 600);
		setTitle("Connect 4");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		Container outer = getContentPane();
		outer.setLayout(new BorderLayout());
		Container container = new Container();
		GridLayout grid = new GridLayout(ROWS, COLS);
		container.setLayout(grid);
		outer.add(container,BorderLayout.CENTER);
		final JButton button2 = new JButton();
		outer.add(button2,BorderLayout.WEST);
		JLabel buttonLabel = new JLabel("Next Generation");
		button2.add(buttonLabel);
		
		
		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button = (JButton) event.getSource();
				if (button.getBackground() == Color.GREEN) {
					button.setBackground(Color.BLACK);
				} else {
					button.setBackground(Color.GREEN);
				}
			}

		};
		
		ActionListener listener2 = new ActionListener() {
			//fix...
			@Override
			public void actionPerformed(ActionEvent event) {
				JButton button2 = (JButton) event.getSource();
				for (int i = 0; i < COLS; i++) {
					for (int j = 0; j < ROWS; j++) {
						nextGeneration();
						if(nextCells[i][j].getBackground() == Color.BLACK){
							button2.setBackground(Color.BLACK);
						}
						else if(nextCells[i][j].getBackground() == Color.GREEN){
							button2.setBackground(Color.GREEN);
						}
					}
				}
				
			}

		};
		button2.addActionListener(listener2);

		Random random = new Random();
		cells = new JButton[COLS][ROWS];

		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				final JButton button = new JButton();
				button.setOpaque(true);
				button.setBorderPainted(false);
				cells[i][j] = button;
				container.add(button);
				button.addActionListener(listener);
				int n = random.nextInt(100);
				if (n < 30) {
					button.setBackground(Color.GREEN);
				} else {
					button.setBackground(Color.BLACK);
				}
			}
		}
	}
	
	public void nextGeneration(){
		nextCells = new JButton[COLS][ROWS];
		for (int i = 0; i < COLS; i++) {
			for (int j = 0; j < ROWS; j++) {
				int neighbors = getNumAliveNeighbors(i,j);
				switch(neighbors){
				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
				case 8:
					cells[i][j].setBackground(Color.BLACK);
					nextCells[i][j] = cells[i][j];
					break;
				case 2:
					nextCells[i][j] = cells[i][j];
					break;
				case 3:
					cells[i][j].setBackground(Color.GREEN);
					nextCells[i][j] = cells[i][j];
					break;
				}
				
			}
		}
	}
	
	public int getNumAliveNeighbors(int i, int j){
		int numAlive =0;
	
		if(isAlive(i-1, j-1)){numAlive++;}
		if(isAlive(i, j-1)){numAlive++;}
		if(isAlive(i+1, j-1)){numAlive++;}
		if(isAlive(i-1, j)){numAlive++;}
		if(isAlive(i+1, j)){numAlive++;}
		if(isAlive(i-1, j+1)){numAlive++;}
		if(isAlive(i, j+1)){numAlive++;}
		if(isAlive(i+1, j+1)){numAlive++;}
	
		return numAlive;
	}

	private boolean isAlive(int i, int j) {
		try{
		return cells[i][j].getBackground() == Color.GREEN;
		}
		catch(Exception e){
			return false;
		}
	}

	public static void main(String args[]) {
		GameOfLife game = new GameOfLife();
		game.setVisible(true);
	}
}
