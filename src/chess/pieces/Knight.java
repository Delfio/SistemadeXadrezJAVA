package chess.pieces;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Knight extends ChessPiece{

	public Knight(Board board, Color color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "N";
	}
	
	private boolean canMove(Position position) {
		ChessPiece p = (ChessPiece)getBoard().piece(position);
		return p == null || p.getColor() != getColor();
	}

	//movimentação do cavalo é sempre 2 1 = 2 reto e 1 lado
	
	@Override
	public boolean[][] possibleMoves() {
		boolean [][] mat = new boolean [getBoard().getRows()][getBoard().getColumns()];
		
		Position p = new Position(0, 0);
		
		//Acima
		p.setValues(position.getRow() -1, position.getColumn() - 2);
		if (getBoard().positionExists(p)&& canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Abaixo
		p.setValues(position.getRow() -2, position.getColumn() - 1);
		if (getBoard().positionExists(p)&& canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Esquerda
		p.setValues(position.getRow()-2, position.getColumn()+1);
		if (getBoard().positionExists(p)&& canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Direita
		p.setValues(position.getRow()-1, position.getColumn()+2);
		if (getBoard().positionExists(p)&& canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Noroeste
		p.setValues(position.getRow()+1, position.getColumn()+2);
		if (getBoard().positionExists(p)&& canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Nordeste
		p.setValues(position.getRow()+2, position.getColumn()+1);
		if (getBoard().positionExists(p)&& canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//SulDoOeste
		p.setValues(position.getRow()+2, position.getColumn()-1);
		if (getBoard().positionExists(p)&& canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		//Suldeste
		p.setValues(position.getRow()+1, position.getColumn()-2);
		if (getBoard().positionExists(p)&& canMove(p)) {
			mat[p.getRow()][p.getColumn()] = true;
		}
		
		return mat;
	}
	
}
