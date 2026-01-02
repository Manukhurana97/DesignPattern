package strategy;

import entities.Board;
import entities.Player;

public class DiagonalWinningStrategy implements WinningStrategy {

    public boolean checkWinner(Board board, Player player) {
        int size = board.getSize(), r = 0;

        for (r = 0; r < size; r++) {
            if (board.getCell(r, r).getSymbol() != player.getSymbol()) break;
        }

        for (r = 0; r < size; r++) {
            if (board.getCell(size - r - 1, r).getSymbol() != player.getSymbol()) break;
        }

        return r == size;
    }
}