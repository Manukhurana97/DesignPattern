package strategy;

import entity.*;
import java.util.*;
import java.util.stream.Collectors;

public class UserSearchStrategy implements SearchStrategy {
	private final User user;

	public UserSearchStrategy(User user) {
		this.user = user;
	}


	public List<Question> filter(List<Question> questions) {
		return questions.stream().filter(q -> q.getAuthor().getId().equals(user.getId())).collect(Collectors.toList());
	}
}