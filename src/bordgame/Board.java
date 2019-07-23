package bordgame;

public class Board {
	
	private int rows;
	private int columns;
	private Pieces [][] pieces;
	
	
	public Board(int rows, int columns) {
		if(rows < 1 || columns < 1) {
			throw new BoardExceptions("Error creating board: there must be at last 1 row and 1 column");
		}
		this.rows = rows;
		this.columns = columns;
		pieces = new Pieces [rows][columns];
	}


	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Pieces piece (int row, int column) {
		if(!positionExists(row, column)) {
			throw new BoardExceptions("Position not on the board");
		}
		return pieces[row][column];
	}
	
	public Pieces piece(Position position) {
		if(!positionExists(position)) {
			throw new BoardExceptions("Position not on the board");
		}
		return pieces[position.getRow()][position.getColumn()];
	}
	
	public void placePiece(Pieces piece, Position position) {
		if(thereIsApiece(position)) {
			throw new BoardExceptions("There is already a pice on position " + position);
		}
		pieces[position.getRow()][position.getColumn()] = piece;
		piece.position = position;
		
	}
	
	private boolean positionExists(int row, int column) {
		return row >= 0 && row < rows && column >= 0 && column < columns;
	}
	
	public boolean positionExists(Position position) {
		return positionExists(position.getRow(), position.getColumn());
	}
	
	public boolean thereIsApiece(Position position) {
		if(!positionExists(position)) {
			throw new BoardExceptions("Position not on the board");
		}
		return piece(position) != null;
	}
}
