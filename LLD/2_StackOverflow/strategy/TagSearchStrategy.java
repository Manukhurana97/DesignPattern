package strategy;

import java.util.*;
import java.util.stream.Collectors;

import entities.*;


public class TagSearchStrategy {

	private final Tag tag;
	public TagSearchStrategy(Tag tag) {
		this.tag = tag;
	}

	public List<Question> filter(List<Question> questions) {
		return questions.stream()
		.filter(q -> q.getTags().contains(tag))
		.collect(Collectors.toList());
	}
}