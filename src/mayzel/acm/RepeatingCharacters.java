//i didn't make it through exceptions or specify which chars are okay (data validation)
package mayzel.acm;

import java.util.Scanner;

public class RepeatingCharacters {

	public RepeatingCharacters() {

	}

	public String repeat(int timesRepeated, String input) {
		StringBuilder info = new StringBuilder();
		char letter;
		for (int i = 0; i < input.length(); i++) {
			letter = input.charAt(i);
			for (int j = 0; j < timesRepeated; j++) {
				info.append(letter);
			}
		}
		return info.toString().toUpperCase();
	}

	public static void main(String[] args) {
		RepeatingCharacters rc = new RepeatingCharacters();
		StringBuilder data = new StringBuilder();
		Scanner keyboard = new Scanner(System.in);
		// System.out.println("Enter the amount of data sets that will follow:");
		int numTimes = keyboard.nextInt();
		int currentTurn = 1;
		while (currentTurn <= numTimes) {
			keyboard.nextInt();
			// System.out.println("Enter the amount of times characters should be repeated:");
			int timesRepeated = keyboard.nextInt();
			// System.out.println("Enter the the string of characters you want repeated:");
			String input = keyboard.next();
			data.append(currentTurn);
			data.append(" ");
			data.append(rc.repeat(timesRepeated, input));
			data.append("\n");
			currentTurn++;
		}
		System.out.println(data.toString());
	}

}
