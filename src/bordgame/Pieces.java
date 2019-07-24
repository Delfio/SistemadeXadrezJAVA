package bordgame;

public abstract class Pieces {
	
	protected Position position;
	private Board board;
	
	public Pieces(Board board) {
		this.board = board;
		position = null; //Padrão JAVA, ele coloca nulo
	}

	protected Board getBoard() {
		return board;
	}
	
	public abstract boolean[][] possibleMoves();
	
	/*
	 * Hook método.
	 * Metodo concreto que esta usando um abstrato
	 */
	
	public boolean possibleMove(Position position) {
		return possibleMoves()[position.getRow()][position.getColumn()];
	}
	
	public boolean isThereAnyPossibleMove() {
		boolean [][] mat = possibleMoves();
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				if (mat[i][j] == true) {
					return true;
				}
			}	
		}
		return false;
	}

	public abstract void add(Pieces capturedPiece);

	
}
