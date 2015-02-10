package mayzel.minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MinesweeperFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private static final int ROWS = 20;
	private static final int COLS = 20;
	private JButton[][] gridButtons = new JButton[ROWS][COLS];
	private String[][] counts = new String[ROWS][COLS];
	private JButton reset = new JButton("RESET");
	private Container outer;
	private int bombs = 40;
	private JLabel numBombs;
	private final String MINE = "X";
	public MinesweeperFrame() throws IOException{
		setSize(1000, 600);
		setTitle("Minesweeper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		outer = getContentPane();
		outer.setLayout(new BorderLayout());
		outer.setBackground(Color.BLUE);
		Container container = new Container();
		GridLayout grid = new GridLayout(ROWS, COLS);
		container.setLayout(grid);
		outer.add(container, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		outer.add(panel, BorderLayout.NORTH);
		panel.add(reset);
		numBombs = new JLabel("                                                            " + String.valueOf(bombs) + " MINES LEFT");
		panel.setBackground(Color.BLUE);
		panel.add(numBombs);
		
		fillMines();
		
		//grid buttons action listener
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				for (int i = 0; i < ROWS; i++) {
					for (int j = 0; j < COLS; j++) {
						if (event.getSource().equals(gridButtons[i][j])) {
							gridButtons[i][j].setBackground(Color.darkGray);
							if (counts[i][j].equals(MINE)) {
								gridButtons[i][j].setBackground(Color.RED);
								gridButtons[i][j].setText("X");
								gameOver();
							}
							else if(counts[i][j].equals("")){
								gridButtons[i][j].setText("");
								gridButtons[i][j].setEnabled(false);
								ArrayList<Integer> toClear = new ArrayList<Integer>();
								toClear.add(i*100+j);
								clearZeros(toClear);
								checkWin();
							}
							else{
								gridButtons[i][j].setText(counts[i][j]);
								gridButtons[i][j].setEnabled(false);
								checkWin();
							}
						}
					}
				}
			}
		};
		
		//reset action listener
		ActionListener listener2 = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource().equals(reset)) {
					bombs = 40;
					for (int i = 0; i < ROWS; i++) {
						for (int j = 0; j < COLS; j++) {
							gridButtons[i][j].setEnabled(true);
							gridButtons[i][j].setText("");
							gridButtons[i][j].setBackground(Color.lightGray);
							numBombs.setText("                                                            "  + String.valueOf(bombs)+ " MINES LEFT" );
						}
					}
					fillMines();
				}
			}
		};
		
		MouseAdapter mouse = new MouseAdapter(){
		@Override
		 public void mouseClicked(MouseEvent e){
	            if (SwingUtilities.isRightMouseButton(e) || e.isControlDown()){
	                for (int i = 0; i < ROWS; i++){
	                    for (int j = 0; j < COLS; j++){
	                        if (gridButtons[i][j] == e.getSource()) {  
	                        	if(gridButtons[i][j].getBackground()==Color.lightGray){
		                        	gridButtons[i][j].setText("P");
		                        	gridButtons[i][j].setBackground(Color.CYAN);
		                        	numBombs.setText("                                                            "  + String.valueOf(--bombs)+ " MINES LEFT" );
								
	                        	}
	                        	else if(gridButtons[i][j].getBackground()==Color.CYAN){
		                        	gridButtons[i][j].setText("");
		                        	gridButtons[i][j].setBackground(Color.lightGray);
		                        	numBombs.setText("                                                            "  + String.valueOf(++bombs)+ " MINES LEFT" );
									
	                        	}
	                        }
	                    }
	                }
	            }
	        }
		};
		
		MouseAdapter mouse2 = new MouseAdapter(){
			@Override
			 public void mouseClicked(MouseEvent e){
		            if (SwingUtilities.isRightMouseButton(e)&& SwingUtilities.isLeftMouseButton(e)){
		                for (int i = 0; i < ROWS; i++){
		                    for (int j = 0; j < COLS; j++){
		                    	//?????????????????????????????????????????????????????????????????????
		                    }
		                }
		            }
		        }
			};
		
		
		reset.addActionListener(listener2);
		//set up buttons
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				gridButtons[i][j] = new JButton();
				gridButtons[i][j].setBackground(Color.lightGray);
				gridButtons[i][j].addActionListener(listener);
				gridButtons[i][j].addMouseListener(mouse);
				gridButtons[i][j].addMouseListener(mouse2);
				container.add(gridButtons[i][j]);
			}
		}		
	}

	public void fillMines() {
		//keep track of all choices
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < counts.length; i++) {
			for (int j = 0; j < counts[i].length; j++) {
				list.add(i * 100 + j);
			}
		}
		//fill mines
		counts = new String[ROWS][COLS];
		for (int k = 0; k < bombs; k++) {
			int choice = (int) (Math.random() * list.size());
			counts[list.get(choice) / 100][list.get(choice) % 100] = MINE;
			list.remove(choice);
		}
		//fill numbers
		for (int x = 0; x < counts.length; x++) {
			for (int y = 0; y < counts[x].length; y++) {
				if (counts[x][y] != MINE) {
					counts[x][y] = getNumMines(x, y);
				}
			}
		}
	}
	
	public void clearZeros(ArrayList<Integer> toClear){
		if(toClear.size()==0){
			return;
		}
		else{
			int x = toClear.get(0) / 100;
			int y = toClear.get(0) % 100;
			toClear.remove(0);
				if(x>0 && y>0 && gridButtons[x-1][y-1].isEnabled() ){
					if(gridButtons[x-1][y-1].getBackground()!= Color.CYAN){
						gridButtons[x-1][y-1].setText(counts[x-1][y-1]);
						gridButtons[x-1][y-1].setBackground(Color.darkGray);
						gridButtons[x-1][y-1].setEnabled(false);
						if(counts[x-1][y-1] == ""){
							toClear.add((x-1)*100+(y-1));
						}
					}
				}
				if (y>0 && gridButtons[x][y-1].isEnabled()) {
					if(gridButtons[x][y-1].getBackground()!= Color.CYAN){
						gridButtons[x][y-1].setText(counts[x][y-1]);
						gridButtons[x][y-1].setBackground(Color.darkGray);
						gridButtons[x][y-1].setEnabled(false);
						if(counts[x][y-1] == ""){
							toClear.add(x*100+(y-1));
						}
					}
				}
				if (x<ROWS-1 && y>0 && gridButtons[x+1][y-1].isEnabled()) {
					if(gridButtons[x+1][y-1].getBackground()!= Color.CYAN){
						gridButtons[x+1][y-1].setText(counts[x+1][y-1]);
						gridButtons[x+1][y-1].setBackground(Color.darkGray);
						gridButtons[x+1][y-1].setEnabled(false);
						if(counts[x+1][y-1] == ""){
							toClear.add((x+1)*100+(y-1));
						}
					}
				}
				if (x>0 && gridButtons[x-1][y].isEnabled()) {
					if(gridButtons[x-1][y].getBackground()!= Color.CYAN){
						gridButtons[x-1][y].setText(counts[x-1][y]);
						gridButtons[x-1][y].setBackground(Color.darkGray);
						gridButtons[x-1][y].setEnabled(false);
						if(counts[x-1][y] == ""){
							toClear.add((x-1)*100+y);
						}
					}
				}
				if (x<ROWS-1 && gridButtons[x+1][y].isEnabled()) {
					if(gridButtons[x+1][y].getBackground()!= Color.CYAN){
						gridButtons[x+1][y].setText(counts[x+1][y]);
						gridButtons[x+1][y].setBackground(Color.darkGray);
						gridButtons[x+1][y].setEnabled(false);
						if(counts[x+1][y] == ""){
							toClear.add((x+1)*100+y);
						}
					}
				}
				if (x>0 && y<COLS-1 && gridButtons[x-1][y+1].isEnabled()) {
					if(gridButtons[x-1][y+1].getBackground()!= Color.CYAN){
						gridButtons[x-1][y+1].setText(counts[x-1][y+1]);
						gridButtons[x-1][y+1].setBackground(Color.darkGray);
						gridButtons[x-1][y+1].setEnabled(false);
						if(counts[x-1][y+1] == ""){
							toClear.add((x-1)*100+(y+1));
						}
					}
				}
				if (y<COLS-1 && gridButtons[x][y+1].isEnabled()) {
					if(gridButtons[x][y+1].getBackground()!= Color.CYAN){
						gridButtons[x][y+1].setText(counts[x][y+1]);
						gridButtons[x][y+1].setBackground(Color.darkGray);
						gridButtons[x][y+1].setEnabled(false);
						if(counts[x][y+1] == ""){
							toClear.add(x*100+(y+1));
						}
					}
				}
				if (x<ROWS-1 && y<COLS-1 && gridButtons[x+1][y+1].isEnabled()) {
					if(gridButtons[x+1][y+1].getBackground()!= Color.CYAN){
						gridButtons[x+1][y+1].setText(counts[x+1][y+1]);
						gridButtons[x+1][y+1].setBackground(Color.darkGray);
						gridButtons[x+1][y+1].setEnabled(false);
						if(counts[x+1][y+1] == ""){
							toClear.add((x+1)*100+(y+1));
						}
					}
				}
			clearZeros(toClear);
		}
	}

	public String getNumMines(int i, int j) {
		int numMines = 0;
		String number = null;
		try {
			if (i>0 && j>0 && isMine(i - 1, j - 1)) {numMines++;}
			if (j>0 && isMine(i, j - 1)) {numMines++;}
			if (i<ROWS-1 && j>0 && isMine(i + 1, j - 1)) {numMines++;}
			if (i>0 && isMine(i - 1, j)) {numMines++;}
			if (i<ROWS-1 && isMine(i + 1, j)) {numMines++;}
			if (i>0 && j<COLS-1 && isMine(i - 1, j + 1)) {numMines++;}
			if (j<COLS-1 && isMine(i, j + 1)) {numMines++;}
			if (i<ROWS-1 && j<COLS-1 && isMine(i + 1, j + 1)) {numMines++;}
			number=String.valueOf(numMines);
			if(number.equals("0")){
				number="";
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		return number;
	}

	private boolean isMine(int i, int j) {
		if (counts[i][j] == MINE) {
			return true;
		} else {
			return false;
		}
	}
	
	public void checkWin(){
		boolean won = true;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if(counts[i][j] != MINE && gridButtons[i][j].isEnabled()){
					won = false;
				}                                                          
			}
		}
		if(won == true){
			numBombs.setText("                                                      CONGRATULATIONS! YOU WON!!!" );
		}
	}
	
	public void gameOver(){
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if(gridButtons[i][j].isEnabled()){
					if (!counts[i][j].equals(MINE)) {
						gridButtons[i][j].setText(counts[i][j]);
						gridButtons[i][j].setEnabled(false);
					}
					else {
						gridButtons[i][j].setText("X");
						gridButtons[i][j].setEnabled(false);
					}
				}
				numBombs.setText("                                                            "  + String.valueOf(0)+ " MINES LEFT" );	
			}
		}
	}

	public static void main(String[] args) {	
		MinesweeperFrame game;
		try {
			game = new MinesweeperFrame();
			game.setVisible(true);	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}