package bordgame;

public class Pieces {
	
	protected Position position;
	private Board board;
	
	public Pieces(Board board) {
		this.board = board;
		position = null; //Padr�o JAVA, ele coloca nulo
	}

	protected Board getBoard() {
		return board;
	}

	
}
