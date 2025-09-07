import java.util.*;

import entity.*;
import enums.VoteType;
import strategy.*;


public class Main {
	public static void main(String[] args) {
		StackOverFlowService service = new StackOverFlowService();


		User a = service.createUser("a");	
		User b = service.createUser("b");
		User c = service.createUser("c");


		System.out.println("\n-- A asking question -- ");
		Set<Tag> tags = Set.of(new Tag("java"), new Tag("HelloWorld"));
		Question question = service.postQuestion(a.getId(), "How to write hello world in java", "Im new to java, need help in creating hello word", tags);


		System.out.println("\nB and C post answer");
		Answer bAnswer = service.postAnswer(b.getId(), question.getId(), "public class main { public static void main(String[] args){ Systsem.out.println(hello World); } }");
		Answer cAnswer = service.postAnswer(c.getId(), question.getId(), "public class test{ public static void main(String[] args){ Systsem.out.println(hello World); // hello world need to be in double quotes} }");
		service.printReputations(a,b,c);
	

		System.out.println("\n-- voting --");
		service.voteToPost(a.getId(), question.getId(), VoteType.UPVOTE);
		service.voteToPost(b.getId(), cAnswer.getId(), VoteType.UPVOTE);
		service.voteToPost(a.getId(), bAnswer.getId(), VoteType.DOWNVOTE);
		service.printReputations(a,b,c);


		System.out.println("\nA Accepted answer");
		service.acceptAnswer(question.getId(), bAnswer.getId());
		service.printReputations(a,b,c);

		System.out.println("\nSearch Question");
		List<SearchStrategy> filter = List.of(
            new UserSearchStrategy(a),
            new TagSearchStrategy(new Tag("java"))
        );
		List<Question> searchResult = service.searchQuestion(filter);
		searchResult.forEach(q -> System.out.println(q.getTitle()));
	}
}