package service;

import entities.*;
import enums.*;
import state.*;
import strategy.*;

import java.util.*;

public class Game {

	private final Board board;
	private final Player player1;
	private final Player player2;
	private Player currentPlayer;
	private Player winner;
	private GameStatus status;
	private GameState state;
	private List<WinningStrategy> winningStrategies;

	public Game(Player player1, Player player2) {
		this.board = new Board(3);
		this.player1 = player1;
		this.player2 = player2;
		this.currentPlayer = player1;
		this.status = GameStatus.IN_PROGRESS;
		this.state = new InProgressState();
		this.winningStrategies = List.of(
			new RowWinningStrategy(), 
			new ColumnWinningStrategy(), 
			new DiagonalWinningStrategy());
	}

	public void makeMove(Player player, int r, int c) {
		state.handleMove(r, c, player, this);
	}

	public Board getBoard() {
		return board;
	}

	public Player getCurrentPlayer () {
		return currentPlayer;
	}

	public GameStatus getStatus(){
		return status;
	}

	public void setStatus(GameStatus status) {
		this.status = status;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player player) {
		this.winner = winner;
	}

	public boolean checkWinner(Player player) {
		for(WinningStrategy winningStrategy: winningStrategies) {
			if(winningStrategy.checkWinner(board, player)) {
				return true;
			}
		}

		return false;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public void switchUser() {
		this.currentPlayer = (this.currentPlayer == player1) ? player2 : player1;
	}
}