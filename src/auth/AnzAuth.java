package auth;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import bank.Bank;
import user.User;

public class AnzAuth extends Authentication {
	public AnzAuth(Bank bank) {
		super.userList = bank.userList;
		super.usersFile = bank.usersFile;
		this.loadUsers();
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

	private void loadUsers() {
		try {

			Scanner myReader = new Scanner(usersFile);

			while (myReader.hasNextLine()) {
				String result = myReader.nextLine();
				String[] data = result.split(";");
				User newUser = new User(data[0], data[1], data[2], Boolean.parseBoolean(data[3]));
				userList.add(newUser);
			}

			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
