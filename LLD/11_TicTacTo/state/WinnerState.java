package state;

import exception.*;
import entities.*;
import service.*;

public class WinnerState implements GameState {


	public void handleMove(int r, int c, Player player, Game game) {
		throw new InvalidMoveException("Game is already over. " + game.getWinner().getName() + " has won. ");
	}
}