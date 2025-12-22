package strategy;

import java.util.*;

import entities.*;

public interface SearchStrategy {

    List<Question> filter(List<Question> questions);
}