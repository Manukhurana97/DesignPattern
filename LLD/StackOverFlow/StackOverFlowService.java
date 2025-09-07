
import entity.*;
import enums.VoteType;
import strategy.*;
import observer.*;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;


public class StackOverFlowService {

	private final Map<String, User> users = new ConcurrentHashMap<>();
	private final Map<String, Question> questions = new ConcurrentHashMap<>();
	private final Map<String, Answer> answers = new ConcurrentHashMap<>();
	private final PostObserver reputationManager = new ReputationManager();

	public User createUser(String userName) {
		User user = new User(userName);
		users.put(user.getId(), user);
		System.out.println("user "+ userName +" is created with id "+user.getId());
		return user;
	}


	public Question postQuestion(String userId, String title, String body, Set<Tag> tags) {
		User author = users.get(userId);
		Question question = new Question(author, title, body, tags);
		questions.put(question.getId(), question);
		question.addObserver(reputationManager);

		System.out.println("question with title {"+ title+"} posted by "+userId);
		return question;
	}


	public Answer postAnswer(String author, String questionId,  String body) {
		User user = users.get(author);
		Question question = questions.get(questionId);
		
		Answer answer = new Answer(user, body);
		
		answer.addObserver(reputationManager);
		question.addAnswer(answer);
		answers.put(answer.getId(), answer);

		System.out.println("Answer posted by "+user.getUserName());
		return answer;
	}


	public void voteToPost(String userId, String id, VoteType voteType) {
		User user = users.get(userId);
		Post post = findPostById(id);
		post.vote(user, voteType);
	}


	public Post findPostById(String id) {
		if(questions.containsKey(id)) {
			return questions.get(id);
		} else if(answers.containsKey(id)) {
			return answers.get(id);
		}

		throw new NoSuchElementException("Post Not found");
	}


	public void acceptAnswer(String questionId, String answerid) {
		Question question = questions.get(questionId);
		Answer answer = answers.get(answerid);		
		question.acceptAnswer(answer);

		System.out.println("answer accepted");
	}


	public List<Question> searchQuestion(List<SearchStrategy> strategies) {
		List<Question> result = new ArrayList<>(questions.values());

		for(SearchStrategy strategy: strategies) {
			result = strategy.filter(result);
		}

		return result;
	}

	public void printReputations(User... users) {
        System.out.println("--- Current Reputations ---");
        for(User user : users) {
            System.out.printf("%s: %d\n", user.getUserName(), user.getReputation());
        }
    }
}