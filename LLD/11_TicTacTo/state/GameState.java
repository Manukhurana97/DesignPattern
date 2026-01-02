package state;

import entities.*;
import service.*;

public interface GameState {
	void handleMove(int r, int c, Player player, Game game);
}