package mayzel.acm2;
import java.util.Scanner;

public class RascalTriangle {
	
	public RascalTriangle(){
		
	}
	public static void main(String []args){
		StringBuilder info = new StringBuilder();
		Scanner keyboard = new Scanner(System.in);
		int examples = keyboard.nextInt();
		keyboard.nextLine();
		String turn;
		int n;
		int m;
		int answer;
		String input;
		String [] array;
		for(int i=1;i<=examples;i++){
			input = keyboard.nextLine();
			array = input.split(" ");
			turn = array[0]; 
			info.append(turn);
			info.append(" ");
			n = Integer.parseInt(array[1]);
			m = Integer.parseInt(array[2]);
			answer = m*(n-m)+1;
			info.append(answer);
			info.append("\n");
		}
		System.out.println(info.toString());
		keyboard.close();
	}
}
