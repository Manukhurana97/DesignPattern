package service;

import entities.*;
import enums.ConnectionType;

import java.util.*;

public class AccountService {
	private static AccountService INSTANCE;
	private final Map<String, User> users;

	public AccountService() {
		this.users = new HashMap<>();
	}

	public static synchronized AccountService getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AccountService();
		}
		return INSTANCE;
	}

	public User createAccount(String name, String email, String password) {
		System.out.print("--- Creating account ---");
		if(users.containsKey(email)) {
			System.out.printf("-> Email already mapped with account %s\n", users.get(email).getName());
			return null;
		}

		User user = new User(name, email, password);
		users.put(user.getEmail(), user);
		System.out.printf(" -> Account Created %s\n", user.getName());
		return user;
	}

	public void updatedProfile(User user, Profile profile) {
		updateUserDetails(user.updatedProfile(profile));
	}

	public void follow(User followee, User follower) {
		System.out.print("--- Requesting ---");
		updateUserDetails(followee.addFollower(follower));
		System.out.printf(" -> following request sent to %s \n", followee.getName());
	}

	public void updateState(ConnectionType type, User followee, User follower) {
		updateUserDetails(followee.updateState(type, follower));
	}

	public void makeAccountPublic(User user) {
		updateUserDetails(user.makeAccountPublic());
	}

	public void makeAccountPrivate(User user) {
		updateUserDetails(user.makeAccountPrivate());
	}

	public void viewNotifications(User user) {
		user = user.viewNotifications();
		updateUserDetails(user);
	}

	public void viewNotificationsCount(User user) {
		user.viewNotificationsCount();
	}

	public Map<String, User> getAllAccounts() {
		return users;
	}

	private void updateUserDetails(User user) {
		users.put(user.getEmail(), user);
	}
}