package strategy;


import entity.*;

import java.util.*;


public interface SearchStrategy {

	List<Question> filter(List<Question> questions);
}