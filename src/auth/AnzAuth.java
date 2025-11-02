package auth;

import java.io.FileWriter;
import java.io.IOException;

import bank.Bank;
import user.User;

public class AnzAuth extends Authentication {
	public AnzAuth(Bank bank) {
		super.userList = bank.userList;
		super.usersFile = bank.usersFile;
	}

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

}
