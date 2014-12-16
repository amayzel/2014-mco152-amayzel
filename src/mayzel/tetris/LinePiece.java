package mayzel.tetris;

public class LinePiece extends Piece {
	
	public LinePiece(){
		super();
		
		squares[0][1] = new Square();
		squares[1][1] = new Square();
		squares[2][1] = new Square();
		squares[3][1] = new Square();

	}

}
