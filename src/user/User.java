package user;

public class User {
	public String name;
	public String username;
	private String password;
	private String phoneNumber;
	public String email;
	public boolean isActive;

	public User(String name, String username, String password, boolean isActive) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}

	public User(String name, String username, String password, String phoneNumber, boolean isActive) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.isActive = isActive;
	}

	public User(String name, String username, String password, String phoneNumber, String email, boolean isActive) {
		this.name = name;
		this.username = username;
		this.password = password;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.isActive = isActive;
	}

	public void blockUser() {
		this.isActive = false;
	}

	public String getPassword() {
		return this.password;
	}

	public String getPassword(int numOfLastChars) {
		return this.password.substring(this.password.length() - numOfLastChars, this.password.length());
	}

	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public String getPhoneNumber(int numOfLastChars) {
		return this.phoneNumber.substring(this.phoneNumber.length() - numOfLastChars, this.phoneNumber.length());
	}
}
