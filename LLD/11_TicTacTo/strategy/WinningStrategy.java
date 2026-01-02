package strategy;

import entities.*;

public interface WinningStrategy {
	boolean checkWinner(Board board, Player player);
}