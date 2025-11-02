package app;

import auth.AnzAuth;
import auth.Authentication;
import auth.CwAuth;
import auth.NabAuth;
import bank.Bank;
import util.MyUtil;

public class App {
	public static void main(String[] args) {
		int userChoice;

		do {
			System.out.println("=== Bank App ===");
			System.out.println("1. NAB.");
			System.out.println("2. Commonwealth.");
			System.out.println("3. ANZ.");
			System.out.println("4. Exit.");
			userChoice = MyUtil.getAnInterger("Your choice: ");

			switch (userChoice) {

			case 1: {
				Bank nab = new Bank("NAB", "nab.txt");
				NabAuth auth = new NabAuth(nab);
				showSubMenu(nab, auth);
				break;
			}
			case 2: {
				Bank cw = new Bank("Commonwealth", "cw.txt");
				CwAuth auth = new CwAuth(cw);
				showSubMenu(cw, auth);
				break;
			}
			case 3:
				Bank anz = new Bank("ANZ", "anz.txt");
				AnzAuth auth = new AnzAuth(anz);
				showSubMenu(anz, auth);
				break;
			case 4:
				System.out.println("\nGOOD BYE!");
				return;
			default:
				System.out.println("\nPlease input 1 - 4!\n");
				break;
			}

		} while (userChoice != 4);
	}

	static void showSubMenu(Bank bank, Authentication auth) {
		int userChoice;
		do {
			System.out.println("\n=== Welcome to " + bank.name + " bank! ===");
			System.out.println("1. Register.");
			System.out.println("2. Login.");
			System.out.println("3. View All Users.");
			System.out.println("4. Go back.");

			userChoice = MyUtil.getAnInterger("Your choice: ");

			switch (userChoice) {

			case 1: {
				String name = MyUtil.getString("\nName: ");

				String username = MyUtil.getString("Username: ");

				String password = MyUtil.getString("Password: ");

				auth.register(name, username, password);

				break;
			}
			case 2: {
				String username = MyUtil.getString("\nUsername: ");

				String password = MyUtil.getString("Password: ");

				auth.login(username, password);

				break;
			}
			case 3:
				auth.viewAllUsers();
				break;
			case 4:
				System.out.println();
				return;
			default:
				System.out.println("\nPlease input 1 - 4!\n");
				break;
			}

		} while (userChoice != 4);
	}
}
