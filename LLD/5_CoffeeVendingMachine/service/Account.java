package service;

import enums.*;
import  java.util.*;
import java.util.concurrent.*;

public class Account {

	private static Account INSTANCE;
	private final Map<Note, Integer> accounts = new ConcurrentHashMap<>();


	public static synchronized Account getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new Account();
		}

		return INSTANCE;
	}

	public void insert(Note note) {
		accounts.put(note, accounts.getOrDefault(note, 0) + 1);
	}
}