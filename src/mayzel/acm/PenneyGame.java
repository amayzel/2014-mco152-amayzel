package mayzel.acm;

import java.util.Scanner;

public class PenneyGame {
	
	public PenneyGame(){
		
	}
	
	public int getOccurance(char[] array, String playerSequence){
		int count = 0;
		for(int i=0; i<array.length-2;i++){
			char first = array[i];
			char second = array[i+1];
			char third = array[i+2];
			String currentSequence = String.valueOf(first) + String.valueOf(second) + String.valueOf(third) ;
			if(currentSequence.equalsIgnoreCase(playerSequence)){
				count++;
			}
		}
		return count;		
	}
	public static void main(String [] args){
		PenneyGame pg = new PenneyGame();
		StringBuilder info = new StringBuilder();
		Scanner keyboard = new Scanner(System.in);
		//System.out.println("Enter the number of data sets that will follow:");
		int dataSets = keyboard.nextInt();
		int thisTurn = 1;
		while(thisTurn<= dataSets){
			keyboard.nextInt();
			//System.out.println("Enter 40 coin toss results to put in the array");
			String toss = keyboard.next();
			char[] array = new char[40];
			for(int i=0; i<array.length;i++){
				array[i] = toss.charAt(i);
			}
			info.append(thisTurn + " ");
			info.append(pg.getOccurance(array, "TTT")+ " ");
			info.append(pg.getOccurance(array, "TTH")+ " ");
			info.append(pg.getOccurance(array, "THT")+ " ");
			info.append(pg.getOccurance(array, "THH")+ " ");
			info.append(pg.getOccurance(array, "HTT")+ " ");
			info.append(pg.getOccurance(array, "HTH")+ " ");
			info.append(pg.getOccurance(array, "HHT")+ " ");
			info.append(pg.getOccurance(array, "HHH")+ "\n");
			for(int i=0; i<array.length;i++){
				array[i] = 'Z';
			}
			thisTurn++;
		}
	System.out.println(info.toString());
	}
}
