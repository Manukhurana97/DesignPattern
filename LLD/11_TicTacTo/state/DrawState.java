package state;

import entities.*;
import enums.*;
import service.*;
import exception.*;

public class DrawState implements GameState {
	public void handleMove(int r, int c, Player player, Game game) {
		throw new InvalidMoveException("Game is already over. It was a draw");
	}
}