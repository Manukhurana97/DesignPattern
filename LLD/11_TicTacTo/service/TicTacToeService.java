package service;

import entities.*;
import enums.GameStatus;

public class TicTacToeService {

    private static TicTacToeService INSTANCE;
    private Game game;

    public synchronized static TicTacToeService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TicTacToeService();
        }

        return INSTANCE;
    }

    public synchronized void startGame(Player player1, Player player2) {
        game = new Game(player1, player2);
    }

    public synchronized void printBoard() {
        game.getBoard().printBoard();
    }

    public synchronized void makeMove(Player player, int r, int  c) {
        if(game == null) {
            System.out.print("No game in progress, Please create a new game first");
            return;
        }

        System.out.printf("%s plays at (%d %d)%n", player.getName(), r, c);
        game.makeMove(player, r, c);
        if(this.game.getStatus() != GameStatus.IN_PROGRESS)
            this.printBoard();

        if(game.getWinner() != null) {
            System.out.println("Winner "+game.getWinner().getName());
        }
    } 
}