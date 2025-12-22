
import java.util.*;

import entities.*;
import enums.EventType;
import enums.VoteType;
import strategy.*;
import observer.*;

public class StackOverFlowService {

    Map<String, User> users;
    Map<String, Question> questions;
    Map<String, Answer> answers;
    Map<String, Set<User>>observers;

    PostObserver postObserver;


    public StackOverFlowService() {
        users = new HashMap<>();
        questions = new HashMap<>();
        answers = new HashMap<>();
        observers = new HashMap<>();

        postObserver = new AnswerAndComment();
    }

    private static StackOverFlowService INSTANCE;

    public static synchronized StackOverFlowService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StackOverFlowService();
        }

        return INSTANCE;
    }


    public String createUser(String name) {
        User user = new User(name);
        users.put(user.getId(), user);
        return user.getId();

    }


    public String askQuestion(String title, String description, String userId) {
        if (!users.containsKey(userId)) {
            System.out.print("No user found");
            return null;
        }

        return askQuestion(new Question(title, description, users.get(userId)));

    }

    public String askQuestion(String title, String description, String userId, Set<Tag> tags) {
        if (!users.containsKey(userId)) {
            System.out.print("No user found");
            return null;
        }
        return askQuestion(new Question(title, description, users.get(userId), tags));
    }

    private String askQuestion(Question question) {
        questions.put(question.getId(), question);

        delay();
        System.out.printf("( %s ) Question posted successfully with id: %s\n", question.getTitle(), question.getId());

        observers.computeIfAbsent(question.getId(), k -> new HashSet<>()).add(question.getAuthor());
        System.out.print(observers);
        return question.getId();
    }

    public String submitAnswer(String questionId, String description, String userId) {
        return submitAnswer(questionId, new Answer(description, users.get(userId)));
    }

    public String submitAnswer(String questionId, String description, String userId, Set<Tag> tags) {
        return submitAnswer(questionId, new Answer(description, users.get(userId), tags));
    }

    private String submitAnswer(String questionId, Answer answer) {
        if (!users.containsKey(answer.getAuthor().getId())) {
            System.out.print("No user found");
            return null;
        }

        if (!questions.containsKey(questionId)) {
            System.out.println("Question not Fount");
            return null;
        }

        Question question = questions.get(questionId);

        answers.put(answer.getId(), answer);
        question.addAnswer(answer);

        delay();
        System.out.printf("Answer posted to question: %s\n", question.getTitle());

        observers.computeIfAbsent(question.getId(), k -> new HashSet<>()).add(answer.getAuthor());
        postObserver.notify(new Event(EventType.ANSWER_SUBMITTED, observers.get(questionId), answer));

        return answer.getId();
    }


    public void vote(String contentId, String userId, VoteType type) {
        if (!users.containsKey(userId)) {
            System.out.print("No user found");
            return;
        }

        Post post = findPostById(contentId);
        post.vote(users.get(userId), type);

    }


    public int repuation(String contentId) {
        Post post = findPostById(contentId);
        return post.getVoteVount();
    }

    public List<Question> searchQuestion(List<SearchStrategy> strategies) {

        System.out.println("Searching question...");
        List<Question> values = new ArrayList<>(questions.values());

        for (SearchStrategy strategy : strategies) {
            values = strategy.filter(values);
        }

        return values;
    }

    private Post findPostById(String contentId) {
        if (questions.containsKey(contentId)) {
            return questions.get(contentId);
        } else if (answers.containsKey(contentId)) {
            return answers.get(contentId);
        }

        throw new IllegalArgumentException("No Post Found");
    }


    public void acceptAnswer(String userId, String questionId, String answerId) {
        if (!users.containsKey(userId)) {
            throw new IllegalArgumentException("No user Found");
        }

        if (!questions.containsKey(questionId)) {
            throw new IllegalArgumentException("No Question Found");
        }

        if (!answers.containsKey(answerId)) {
            throw new IllegalArgumentException("No Answer Found");
        }

        Question question = questions.get(questionId);
        if (!question.getAuthor().getId().equals(userId)) {
            System.out.println("Action not allowed");
            return;
        }

        Answer answer = answers.get(answerId);
        question.acceptAnswer(answer);

        System.out.println("Answer accepted");
        postObserver.notify(new Event(EventType.ACCEPT_ANSWER, observers.get(questionId), answer));

    }


    private void delay() {
        try {
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}