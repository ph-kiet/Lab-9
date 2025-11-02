package bank;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import user.User;

public class Bank {
	public String name;
	public File usersFile;
	public ArrayList<User> userList = new ArrayList<User>();

	public Bank(String name, String filename) {
		this.name = name;
		this.usersFile = new File(filename);
		this.createUserFile();
		this.loadUsers();
	}

	// Init users file
	private void createUserFile() {
		try {
			usersFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Load users from file
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
