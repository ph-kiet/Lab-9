package bank;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import user.User;

public class Bank {
	public String name;
	public File usersFile;
	public ArrayList<User> userList = new ArrayList<User>();

	public Bank(String name, String filename) {
		this.name = name;
		this.usersFile = new File(filename);
		this.createUserFile();
	}

	// Init users file
	private void createUserFile() {
		try {
			usersFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
