package strategy;

import java.util.*;
import java.util.stream.Collectors;

import entities.*;


public class KeywordStrategy implements SearchStrategy{

	private final String keyword;
	public KeywordStrategy(String keyword) {
		this.keyword = keyword.toLowerCase();
	}

	public List<Question> filter(List<Question> questions) {
		return questions.stream()
		.filter(q ->
			q.getTitle().toLowerCase().contains(keyword) ||
			q.getDescription().toLowerCase().contains(keyword)
		).collect(Collectors.toList());
	}
}