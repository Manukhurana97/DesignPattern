package state;

import entities.*;
import enums.*;
import exception.InvalidMoveException;
import service.*;
import state.*;

public class InProgressState implements GameState {
	public void handleMove(int r, int c, Player player, Game game) {
		if(game.getCurrentPlayer() != player) {
			throw new InvalidMoveException("Not your turn");
		}

		game.getBoard().placeCell(r, c, player.getSymbol());

		if(game.checkWinner(player)) {
			System.out.printf("==> Player %s is a winner \n", player.getSymbol());
			game.setWinner(player);
			game.setStatus(player.getSymbol() == Symbol.X ? GameStatus.WINNER_X : GameStatus.WINNER_O);
			game.setState(new WinnerState());
		}

		if(game.getBoard().full()) {
			System.out.println("==> Game is Draw");
			game.setStatus(GameStatus.DRAW);
			game.setState(new DrawState());
		} else{
			game.switchUser();
		}
	}
}