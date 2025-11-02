package auth;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Scanner;

import bank.Bank;
import user.User;
import util.MyUtil;

public class CwAuth extends Authentication {
	public CwAuth(Bank bank) {
		super.maxAttempts = 5;
		super.userList = bank.userList;
		super.usersFile = bank.usersFile;
		this.loadUsers();
	}

	public void register(String name, String username, String password) {
		String phoneNumber = MyUtil.getString("Phone number: ");
		String email = MyUtil.getString("Email: ");
		// Create new user
		User newUser = new User(name, username, password, phoneNumber, email, true);

		// Add user to list
		userList.add(newUser);

		// Write user to file
		try {
			FileWriter fileWriter = new FileWriter(usersFile, true);
			fileWriter.write(newUser.name + ";" + newUser.username + ";" + newUser.getPassword() + ";"
					+ newUser.getPhoneNumber() + ";" + newUser.email + ";" + newUser.isActive + "\n");
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
			System.out.println(user.name + " - " + user.username + " - " + user.getPassword(2) + " - "
					+ user.getPhoneNumber(3) + " - " + user.email);
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
					lines.set(i,
							data[0] + ";" + data[1] + ";" + data[2] + ";" + data[3] + ";" + data[4] + ";" + "false");

					Files.write(usersFile.toPath(), lines);
					return;
				}
			}

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
				User newUser = new User(data[0], data[1], data[2], data[3], data[4], Boolean.parseBoolean(data[5]));
				userList.add(newUser);
			}

			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
