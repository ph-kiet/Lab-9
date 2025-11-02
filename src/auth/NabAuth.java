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

public class NabAuth extends Authentication {
	public NabAuth(Bank bank) {
		super.maxAttempts = 5;
		super.userList = bank.userList;
		super.usersFile = bank.usersFile;
		this.loadUsers();
	}

	public void register(String name, String username, String password) {
		String phoneNumber = MyUtil.getString("Phone number: ");

		// Create new user
		User newUser = new User(name, username, password, phoneNumber, true);

		// Add user to list
		userList.add(newUser);

		// Write user to file
		try {
			FileWriter fileWriter = new FileWriter(usersFile, true);
			fileWriter.write(newUser.name + ";" + newUser.username + ";" + newUser.getPassword() + ";"
					+ newUser.getPhoneNumber() + ";" + newUser.isActive + "\n");
			fileWriter.close();
			System.out.println("\nRegistered user successfully!\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void blockUserInFile(String username) {
		try {
			List<String> lines = Files.readAllLines(usersFile.toPath());

			for (int i = 0; i < lines.size(); i++) {
				String result = lines.get(i);
				String[] data = result.split(";");

				if (username.equals(data[1])) {
					lines.set(i, data[0] + ";" + data[1] + ";" + data[2] + ";" + data[3] + ";" + "false");

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
				User newUser = new User(data[0], data[1], data[2], data[3], Boolean.parseBoolean(data[4]));
				userList.add(newUser);
			}

			myReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
