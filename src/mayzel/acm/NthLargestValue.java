//didn't do data validation
package mayzel.acm;

import java.util.Arrays;
import java.util.Scanner;

public class NthLargestValue {

	public NthLargestValue(){
		
	}
	
	public int getValue(int []array){
		int nth=3;
		int temp;
		Arrays.sort(array);
		return array[7];
	}
	public static void main(String[] args){
		NthLargestValue nlv = new NthLargestValue();
		StringBuilder info = new StringBuilder();
		Scanner keyboard = new Scanner(System.in);
		//System.out.println("Enter the number of data sets that will follow:");
		int dataSets = keyboard.nextInt();
		int thisTurn = 1;
		while(thisTurn<= dataSets){
			//System.out.println("Enter 10 values to put in the array");
			int[] array = new int[10];
			keyboard.nextInt();
			for(int i=0; i<array.length;i++){
				array[i] = keyboard.nextInt();
			}
			if ( keyboard.hasNextLine() ) {
			    keyboard.nextLine();
			}
			info.append(thisTurn);
			info.append(" ");
			info.append(nlv.getValue(array));
			info.append("\n");
			for(int i=0; i<array.length;i++){
				array[i] = -1;
			}
			thisTurn++;
		}
		
		System.out.println(info.toString());
	}

}
