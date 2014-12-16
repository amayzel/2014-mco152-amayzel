package mayzel.acm2;

import java.util.ArrayList;
import java.util.Scanner;

public class HappyPrimes {

	public HappyPrimes() {

	}

	public static void main(String[] args) {
		HappyPrimes hp = new HappyPrimes();
		StringBuilder info = new StringBuilder();
		Scanner keyboard = new Scanner(System.in);
		int times = keyboard.nextInt();
		keyboard.nextLine();
		boolean answer;
		boolean primeness;
		String input;
		String[] parts;
		for(int i=0;i<times;i++){
			input = keyboard.nextLine();
			parts = input.split(" ");
			String turn = parts[0];
			info.append(turn);
			String num = parts[1];
			info.append(" ");
			info.append(num);
			primeness = hp.isPrime(Integer.parseInt(num));
			answer = hp.isHappyPrime(Integer.parseInt(num));
			if (Integer.parseInt(num) > 2 && primeness && answer) {
				info.append(" YES\n");
			} else {
				info.append(" NO\n");
			}
		}
		System.out.println(info.toString().trim());
		keyboard.close();
	}

	public boolean isHappyPrime(int num) {
		ArrayList<Integer> squaredNums = new ArrayList<Integer>();
		ArrayList<Integer> badNums = new ArrayList<Integer>();
		badNums.add(37);
		badNums.add(58);
		badNums.add(89);
		badNums.add(145);
		badNums.add(42);
		badNums.add(20);
		badNums.add(4);
		badNums.add(16);
		boolean status = false;
		String number = String.valueOf(num);
		while(Integer.parseInt(number) !=1 || !squaredNums.contains(Integer.parseInt(number))){
			int sums = 0;
			for (int i = 0; i < number.length(); i++) {
				char digit = number.charAt(i);
				double digitSquare = Math.pow(Integer.parseInt(String.valueOf(digit)), 2);
				sums += digitSquare;
			}
			number = String.valueOf(sums);
			if(badNums.contains(Integer.parseInt(number))){
				return false;
			}
			if(squaredNums.contains(Integer.parseInt(number))){
				return false;
			}
			if(Integer.parseInt(number)==1){
				return true;
			}
			squaredNums.add(Integer.parseInt(number));
			
		}
		if(Integer.parseInt(number) == 1){
			status = true;
		}
		return status;
	}
	
	public boolean isPrime(int num) {
		boolean primeness = true;
		if (num == 1) {	primeness = false;}
		if (num == 2) {	primeness = false;}
		for (int i = 2; i < num - 1; i++) {
			int remainder = num % i;
			if (remainder == 0) {
				primeness = false;
			}
		}
		return primeness;
	}

}
