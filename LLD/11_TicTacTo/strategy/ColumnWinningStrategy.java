package strategy;

import entities.Board;
import entities.Player;

public class ColumnWinningStrategy implements WinningStrategy {

    public boolean checkWinner(Board board, Player player) {
        for (int c = 0; c < board.getSize(); c++) {
            boolean colWon = true;
            for (int r = 0; r < board.getSize(); r++) {
                if (board.getCell(r, c).getSymbol() != player.getSymbol()) {
                    colWon = false;
                    break;
                }
            }

            if (colWon) return true;
        }

        return false;
    }
}