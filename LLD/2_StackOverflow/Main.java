import java.util.*;

import entities.*;
import enums.*;
import strategy.*;

public class Main {
	public static void main(String[] args) {
		StackOverFlowService service = StackOverFlowService.getInstance();


		String uId1 = service.createUser("u1");
		String uId2 = service.createUser("u2");
		
		String qId1 = service.askQuestion("Java out of memory exception", "hi, I'm facing out of memory exception issue with java code", uId1, Set.of(new Tag("Java"), new Tag("Spring boot")));
		String aId1 = service.submitAnswer(qId1, "test answer1", uId1);
		String aId2 = service.submitAnswer(qId1, "test answer2", uId2, Set.of(new Tag("Java")));

		service.acceptAnswer(uId2, qId1, aId2);
		
		service.vote(aId1, uId1, VoteType.UPVOTE);
		service.vote(aId1, uId2, VoteType.UPVOTE);
		System.out.println("Reputation: " +service.repuation(aId1));


		List<Question> response = service.searchQuestion(List.of(new KeywordStrategy("Java")));
		for(Question q: response) {
			System.out.println(q.getId() +" :-> "+q.getTitle());
		}


		service.acceptAnswer(uId1, qId1, aId2);

	}
}