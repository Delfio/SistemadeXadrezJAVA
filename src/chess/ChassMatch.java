package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import bordgame.Board;
import bordgame.Pieces;
import bordgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChassMatch {
	
	
	private int turn;
	private Color currentPlayer;
	private Board board;
	private boolean check;
	
	private List<Pieces> piecesOnTheBoard = new ArrayList<Pieces>();
	private List<ChessPiece> capturedPieces = new ArrayList<ChessPiece>();
	
	public ChassMatch() {
		board = new Board(8, 8);
		turn = 1;
		check = false;
		currentPlayer = Color.WHITE;
		initialSetup();
	}
	
	public int getTurn() {
		return turn;
	}

	public Color getCurrentPlayer() {
		return currentPlayer;
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
		for (int i=0; i<board.getRows(); i++) {
			for (int j=0; j<board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;
	}
	
	public boolean[][] possiblesMovies(ChessPosition sourcePosition){
		Position position = sourcePosition.toPosition();
		validateSourcePosition(position);
		return board.piece(position).possibleMoves();
	}
	
	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();
		validateTargetPosition(source, target);
		validateSourcePosition(source);
		Pieces capturedPiece = makeMove(source, target);
		nextTurn();
		return (ChessPiece)capturedPiece;
	}
	
	private Pieces makeMove(Position source, Position target) {
		Pieces p = board.removePiece(source);
		Pieces capturedPiece = board.removePiece(target);
		board.placePiece(p, target);
		
		if (capturedPiece != null) {
			piecesOnTheBoard.remove(capturedPiece);
			capturedPiece.add(capturedPiece);
		}
		
		return capturedPiece;
	}
	
	//Logica desfazer movimento - Ao contrario do makeMove()
	private void undoMove(Position source, Position target, Pieces capturedPiece) {
		Pieces p = board.removePiece(target);// Tirar a peça que foi pro destino
		board.placePiece(p, source);// Devolver peça para a posicao de origem
		
		//Devolver uma peça capturada para a posicao de destino -- vai q...
		if (capturedPiece != null) {// verificando foi caputrado algo
			board.placePiece(capturedPiece, target);//devolvendo
			capturedPieces.remove(capturedPiece);//tirando peça da lista de captura
			piecesOnTheBoard.add(capturedPiece); // Devolvendo a peça para o tabuleiro
			//Jogada refeita !!
		}
	}
	
	private void validateSourcePosition(Position position) {
		if (!board.thereIsAPiece(position)) {
			throw new ChessExceptions("There is no piece on source position");
		}
		if(currentPlayer !=(((ChessPiece) board.piece(position)).getColor())) {
			throw new ChessExceptions("These chosen piece is not yours");
		}
		
		if (!board.piece(position).isThereAnyPossibleMove()) {
			throw new ChessExceptions("There is no possible moves for the chosen piece");
		}
	}
	
	private void validateTargetPosition(Position surce, Position target) {
		if(!board.piece(surce).possibleMove(target)){
			throw new ChessExceptions("The chose piece can´t move to target position");
		}
	}
	
	private void nextTurn() {
		turn++;
		currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
	}
	
	private Color opponent(Color color) {
		return (color == Color.WHITE)? Color.BLACK : Color.WHITE;
	}
	
	private ChessPiece king(Color color) {
		List<Pieces> list = (List<Pieces>) piecesOnTheBoard.stream().filter(x ->((ChessPiece)x).getColor() == color).collect(Collectors.toList());
		for (Pieces pieces : list) {
			if(pieces instanceof King) {
				return (ChessPiece) pieces;
			}
		}
		throw new IllegalStateException("There is no " + color + " king on the board");
	}
	
	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.placePiece(piece, new ChessPosition(column, row).toPosition());
		piecesOnTheBoard.add(piece);
	}
	
	private void initialSetup() {
		placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new Rook(board, Color.WHITE));
        placeNewPiece('d', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new Rook(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new Rook(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
	}
}
