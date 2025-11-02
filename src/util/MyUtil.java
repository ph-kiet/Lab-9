package util;

import java.util.Scanner;

public class MyUtil {

	static Scanner sc = new Scanner(System.in);

	public static int getAnInterger(String message) {
		System.out.print(message);
		return Integer.parseInt(sc.nextLine());
	}

	public static String getString(String message) {
		System.out.print(message);
		return sc.nextLine().trim();
	}
}
