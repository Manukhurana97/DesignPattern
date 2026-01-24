/*
	We have many transaction stored in the DB, we need to make a system that able to filter the transaction based on customer_id, start & end duration, etc provided by the user.
*/


import java.util.*;
import java.util.concurrent.*;


class TransactionsRequest {
	private final String userId;
	private final Long stateDuration;
	private final Long endDuration;

	public TransactionsRequest(String userId, Long stateDuration, Long endDuration) {
		this.userId = userId;
		this.stateDuration = stateDuration;
		this.endDuration = endDuration;
	}

	public String getUserId() {
		return userId;
	}

	public Long getStateDuration() {
		return stateDuration;
	}

	public Long getEndDuration() {
		return endDuration;
	}
}


class Transaction {
	public int id;
	private final String userId;
	private final Long stateDuration;
	private final Long endDuration;
	private final double amount;
	private final String status;

	public Transaction(int id, String userId, Long stateDuration, Long endDuration, double amount, String status) {
		this.id = id;
		this.userId = userId;
		this.stateDuration = stateDuration;
		this.endDuration = endDuration;
		this.amount = amount;
		this.status = status;
	}

	public String getUserId() {
		return userId;
	}

	public Long getStateDuration() {
		return stateDuration;
	}

	public Long getEndDuration() {
		return endDuration;
	}

	public String toString() {
		return "Transaction Id: "+id +" by "+userId +" at "+stateDuration +" completed at "+endDuration +" of amount " + amount +" is "+ status;
	}
}



// strategy (as we extends to db or other type)
interface TransactionRepository {
	void saveTransaction(Transaction transaction);
	List<Transaction> getAllTransactions(TransactionSpecification spec);
}

class InMemoryRepository implements TransactionRepository {
	private final List<Transaction> userTransactions = new CopyOnWriteArrayList<>();

	public void saveTransaction(Transaction transaction) {
		userTransactions.add(transaction);
	}

	public List<Transaction> getAllTransactions(TransactionSpecification spec) {

		if(spec == null) {
			return new ArrayList(userTransactions);
		}

		return userTransactions.stream().filter(spec::isValid).toList();
	}
}


// composite
interface TransactionSpecification {
	boolean isValid(Transaction transaction);
}

class AndSpecification implements TransactionSpecification {
	private final TransactionSpecification left;
	private final TransactionSpecification right;

	public AndSpecification(TransactionSpecification left, TransactionSpecification right) {
		this.left = left;
		this.right = right;
	}

	public boolean isValid(Transaction transaction) {
		return left.isValid(transaction) && right.isValid(transaction);
	}
}


class OrSpecification implements TransactionSpecification {
	private final TransactionSpecification left;
	private final TransactionSpecification right;

	public OrSpecification(TransactionSpecification left, TransactionSpecification right) {
		this.left = left;
		this.right = right;
	}

	public boolean isValid(Transaction transaction) {
		return left.isValid(transaction) || right.isValid(transaction);
	}
}


class UserSpecification implements TransactionSpecification {
	private final String userId;

	public UserSpecification(String userId) {
		this.userId = userId;
	}

	public boolean isValid(Transaction transaction) {
		return transaction.getUserId().equals(userId);
	}
}


class DurationSpecification implements TransactionSpecification {
	private final Long startDuration;
	private final Long endDuration;

	public DurationSpecification(Long startDuration, Long endDuration) {
		this.startDuration = startDuration;
		this.endDuration = endDuration;
	}

	public boolean isValid(Transaction transaction) {
		return transaction.getStateDuration()<=startDuration && transaction.getEndDuration() <= endDuration;
	}
}


enum SpecType {USER, DURATION};

// Factory 
class SpecificationFactory {
	public TransactionSpecification getSpecification(SpecType type, TransactionsRequest request) {
		return switch(type) {
			case USER -> new UserSpecification(request.getUserId());
			case DURATION -> new DurationSpecification(request.getStateDuration(), request.getEndDuration());
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
	}
}


// Facade
class TransactionService {
	public static TransactionService INSTANCE;
	public TransactionRepository repository;
	public final SpecificationFactory factory;

	public TransactionService() {
		this.repository = new InMemoryRepository();
		this.factory = new SpecificationFactory();
	}


	public synchronized static TransactionService getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new TransactionService();
		}

		return INSTANCE;
	}

	public void saveTransaction(Transaction request) {
		repository.saveTransaction(request);
	}

	public List<Transaction> getUserTransactions(TransactionsRequest request) {
		TransactionSpecification spec = null;

		if(request.getUserId() != null && !request.getUserId().isBlank()) {
			spec = factory.getSpecification(SpecType.USER, request);
		}

		if(request.getStateDuration() != null && request.getEndDuration() != null) {
			TransactionSpecification date = factory.getSpecification(SpecType.DURATION, request);
			spec  = (spec == null) ? date : new AndSpecification(spec, date);
		}


		return repository.getAllTransactions(spec);
	}
}


public class Main {
	public static void main(String[] args) {
		TransactionService service = TransactionService.getInstance();
		long now = System.currentTimeMillis();
        service.saveTransaction(new Transaction(1, "User_1", now - 10_000L, now - 5_000L, 100.0, "FAILED"));
        service.saveTransaction(new Transaction(2, "User_1", now - 1_000L, now, 150.0, "SUCCESS"));
        service.saveTransaction(new Transaction(3, "User_2", now - 500L, now, 200.0, "SUCCESS"));


		List<Transaction> transaction1 = service.getUserTransactions(new TransactionsRequest("User_1", now - 1000L , now));
		transaction1.forEach(System.out::println);

		List<Transaction> transaction2 = service.getUserTransactions(new TransactionsRequest(null, now - 1000L , now));
		transaction2.forEach(System.out::println);

	}	
}