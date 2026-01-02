package strategy;

import entities.Board;
import entities.Player;

public class RowWinningStrategy implements WinningStrategy {

    public boolean checkWinner(Board board, Player player) {
        for (int r = 0; r < board.getSize(); r++) {
            boolean rowWon = true;
            for (int c = 0; c < board.getSize(); c++) {
                if (board.getCell(r, c).getSymbol() != player.getSymbol()) {
                    rowWon = false;
                    break;
                }
            }

            if (rowWon) return true;
        }

        return false;
    }
}