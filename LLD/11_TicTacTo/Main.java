import service.*;
import entities.*;
import enums.*;

public class Main {
	public static void main(String[] args) {
		TicTacToeService service = TicTacToeService.getInstance();

		Player player1 = new Player("A", Symbol.X);
		Player player2 = new Player("B", Symbol.O);
//
		System.out.println("--- Game A(X) vs B(O) ---");
		service.startGame(player1, player2);
		service.printBoard();

		service.makeMove(player1, 0, 0);
		service.makeMove(player2, 1, 1);
		service.makeMove(player1, 2, 0);
		service.makeMove(player2, 0, 2);
		service.makeMove(player1, 2, 2);
		service.makeMove(player2, 2, 1);
		service.makeMove(player1, 1, 0);

		System.out.println("--- Game Finished ---");


		System.out.println("--- Game A(X) vs B(O) ---");
		service.startGame(player1, player2);
		service.printBoard();

		service.makeMove(player1, 0, 0);
		service.makeMove(player2, 0, 1);
		service.makeMove(player1, 0, 2);
		service.makeMove(player2, 1, 1);
		service.makeMove(player1, 1, 0);
		service.makeMove(player2, 1, 2);
		service.makeMove(player1, 2, 1);
		service.makeMove(player2, 2, 0);
		service.makeMove(player1, 2, 2);

		System.out.println("--- Game Finished ---");
	}
}