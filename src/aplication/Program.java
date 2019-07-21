package aplication;

import java.rmi.server.UID;

import chess.ChassMatch;

public class Program {

	public static void main(String[] args) {
		
		ChassMatch chessMatch = new ChassMatch();
		
		UI.printBoard(chessMatch.getPieces());
	}

}
