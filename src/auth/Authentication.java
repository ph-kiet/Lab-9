package auth;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import user.User;
import util.MyUtil;

public class Authentication {
	protected int maxAttempts = 3;

	protected File usersFile;
	protected ArrayList<User> userList;

//	public Authentication(Bank bank) {
//		this.userList = bank.userList;
//		this.usersFile = bank.usersFile;
//	}

	// Find user by its username
	private User getUserByUsername(String username) {
		for (User user : this.userList) {
			if (user.username.equals(username)) {
				return user;
			}
		}
		return null;
	}

	// Login
	public void login(String username, String password) {
		User foundUser = getUserByUsername(username);

		if (foundUser == null) {
			System.out.println("\nUSER NOT FOUND!\n");
			return;
		}

		if (foundUser.isActive == false) {
			System.out.println("\nThis user is blocked!\n");
			return;
		}

		int attemptCount = 1;

		while (attemptCount <= maxAttempts) {
			// Return if reached maxAttempts
			if (attemptCount == maxAttempts) {
				System.out.println("\nYou have reached the maximum attempts!");

				foundUser.blockUser();
				blockUserInFile(foundUser.username);

				System.out.println("This user has been blocked!\n");
				return;
			}

			if (foundUser.getPassword().equals(password)) {
				System.out.println("\nLOG IN SUCCESSFULLY!");
				System.out.println();
				return;
			} else {
				System.out.println("\nWrong Password! Remaining attempt(s): " + (maxAttempts - attemptCount));
				attemptCount++;
				password = MyUtil.getString("Enter password again: ");
			}
		}

	}

	// Register
	public void register(String name, String username, String password) {
		// Create new user
		User newUser = new User(name, username, password, true);

		// Add user to list
		userList.add(newUser);

		// Write user to file
		try {
			FileWriter fileWriter = new FileWriter(usersFile, true);
			fileWriter.write(newUser.name + ";" + newUser.username + ";" + newUser.getPassword() + ";"
					+ newUser.isActive + "\n");
			fileWriter.close();
			System.out.println("\nRegistered user successfully!\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void viewAllUsers() {
		if (userList.size() == 0) {
			System.out.println("\nEmpty list!\n");
			return;
		}

		System.out.println();
		for (User user : this.userList) {
			System.out.println(user.name + " - " + user.username + " - " + user.getPassword(2));
		}
		System.out.println();
	}

	public void blockUserInFile(String username) {
		try {
			List<String> lines = Files.readAllLines(usersFile.toPath());

			for (int i = 0; i < lines.size(); i++) {
				String result = lines.get(i);
				String[] data = result.split(";");

				if (username.equals(data[1])) {
					lines.set(i, data[0] + ";" + data[1] + ";" + data[2] + ";" + "false");

					Files.write(usersFile.toPath(), lines);
					return;
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
