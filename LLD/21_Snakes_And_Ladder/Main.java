
import java.util.*;
import java.util.concurrent.*;

enum GameStatus {NOT_STARTED, RUNNING, FINISHED}

// entities
class BoardEntity {
	final int start;
	final int end;

	public BoardEntity(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getStart(){ return start; }
	public int getEnd(){ return end; }
}

class Snake extends BoardEntity {
	public Snake(int start, int end) {
		super(start, end);
		if(start<=end) {
			throw new IllegalArgumentException("Snake head must be in higher position and tail in lower position");
		}

	}
}

class Ladder extends BoardEntity {
	public Ladder(int start, int end) {
		super(start, end);
		if(start>=end) {
			throw new IllegalArgumentException("Ladder head must be in lower position and tail in higher position");
		}
	}
}


class Board {
	public final int size;
	public Map<Integer, Integer> snakesAndLadder;

	public Board(int size, List<BoardEntity> entities) {
		if (size <= 0) throw new IllegalArgumentException("Board size must be positive");

		this.size = size;
		snakesAndLadder = new ConcurrentHashMap<>();

		for (BoardEntity entity : entities) {
			snakesAndLadder.put(entity.getStart(), entity.getEnd());
		}
	}

	public int getSize() { return size; }
	public int getFinalPosition(int position) {
		return snakesAndLadder.getOrDefault(position, position);
	}
}

class Dice {
	int min;
	int max;
	private final Random random;

	public Dice(int min, int max) {
		this.min = min;
		this.max = max;
		random = new Random();
	}

	public int roll() {
		return random.nextInt(max - min + 1) + min;
	}
}

class Player {
	private final String playerName;
	private int position;

	public Player(String playerName) {
		this.playerName = playerName;
		this.position = 0;
	}

	public String getName() { return playerName; }
	public int getPosition() { return position; }
	public void setPosition(int position) { this.position = position; }
}

class GameService {
	private final Board board;
	private final Dice dice;
	private final Queue<Player> players;
	private GameStatus gameState;
	private Player winner;

	public GameService(Builder builder) {
		this.board = builder.board;
		this.dice = builder.dice;
		players = builder.players;
		this.gameState = GameStatus.NOT_STARTED;
	}

	public synchronized void play(String gameId) {
		if(players.size() < 2) {
			System.out.println("Cannot start game, At least 2 players are required");
			return;
		}

		this.gameState = GameStatus.RUNNING;
		System.out.println("Game started!");


		while(this.gameState == GameStatus.RUNNING) {
			Player currentPlayer = players.poll();
			takeTurn(currentPlayer, gameId);

			if(this.gameState == GameStatus.RUNNING) {
				players.add(currentPlayer);
			}
		}

		if(winner != null) {
				System.out.printf("[Game:%s]%s win the game%n", gameId, winner.getName());
			}
	}

	private void takeTurn(Player currentPlayer, String gameId) {
		int roll = dice.roll();
		int currentPosition = currentPlayer.getPosition();
		int nextPosition = currentPlayer.getPosition() + roll;



		if(nextPosition > board.getSize()) {
			System.out.printf("[Game:%s] Opps %s need to lands exactly on %d . Turn skipped %n", gameId, currentPlayer.getName(), board.getSize());
			return;
		}

		if(nextPosition == board.getSize()) {
			currentPlayer.setPosition(nextPosition);
			this.winner = currentPlayer;
			this.gameState = GameStatus.FINISHED;
			System.out.printf("[Game:%s] Hooray! %s reached the final square %d and won!\n", gameId, currentPlayer.getName(), board.getSize());
            return;
		}

		int finalPosition = board.getFinalPosition(nextPosition);

		if(finalPosition > nextPosition) {
			System.out.printf("Wow! %s found a ladder 🪜 at %d and climbed to %d.\n", currentPlayer.getName(), nextPosition, finalPosition);
		} else if (finalPosition < nextPosition) { // Snake
            System.out.printf("Oh no! %s was bitten by a snake 🐍 at %d and slid down to %d.\n", currentPlayer.getName(), nextPosition, finalPosition);
        } else {
            System.out.printf("[Game:%s] %s moved from %d to %d.\n", gameId, currentPlayer.getName(), currentPosition, finalPosition);
        }

        currentPlayer.setPosition(finalPosition);

        if(roll == 6) {
        	System.out.printf("%s rolled a 6 and get another chance %n", currentPlayer.getName());
        	takeTurn(currentPlayer, gameId);
        }
	}


	static class Builder {
		private Board board;
		private Dice dice;
		private Queue<Player> players;

		public Builder setBoard(int size, List<BoardEntity> entities) {
			this.board = new Board(size, entities);
			return this;
		}

		public Builder setDice(int min, int max) {
			this.dice = new Dice(min, max);
			return this;
		}

		public Builder setPlayer(List<String> playerNames) {
			this.players = new LinkedList<>();
			for(String name: playerNames) {
				this.players.add(new Player(name));
			}
			return this;
		}

		public GameService build() {
			if(board == null || dice == null || players == null) {
				throw new IllegalArgumentException("Board, Dice and players must be set first");
			}

			return new GameService(this);
		}
	}
}

class GameManager {
	public static GameManager INSTANCE;
	public final Map<String, GameService> games = new ConcurrentHashMap<>();
	private final ExecutorService service = Executors.newCachedThreadPool();

	public synchronized static GameManager getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new GameManager();
		}

		return INSTANCE;
	}


	public synchronized String createGate(int minDice, int maxDice, List<String> players, List<BoardEntity> entities) {
		String gameId = UUID.randomUUID().toString();
		GameService gameService = new GameService.Builder().setDice(1, 6).setPlayer(players).setBoard(100, entities).build();
		games.put(gameId, gameService);
		return gameId;
	}

	public void play(String gameId) {
		if(!games.containsKey(gameId)) {
			throw new IllegalArgumentException("Invalid Game id");
		}

		games.get(gameId).play(gameId);
	}
}

public class Main {
	public static void main(String[] args) {
		GameManager gameManager = GameManager.getInstance();

		List<BoardEntity> entities = List.of(new Snake(17, 7), new Snake(62, 9),new Snake(98, 78), new Ladder(2, 40), new Ladder(72, 84));
		String gameId = gameManager.createGate(1, 6, List.of("A", "B", "C"), entities);
		gameManager.play(gameId);
	}
}