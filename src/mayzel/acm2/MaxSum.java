package mayzel.acm2;
import java.util.Scanner;

public class MaxSum {	
	public MaxSum(){
		
	}
	public static void main(String[] args){
		MaxSum ms = new MaxSum();
		Scanner keyboard = new Scanner(System.in);
		int size = keyboard.nextInt();
		keyboard.nextLine();
		//create rectangle from input
		int[][] rect = new int[size][size];
		for(int i=0;i<size;i++){
			for(int j=0;j<size;j++){
				rect[i][j] = keyboard.nextInt();
			}
		}
		int maxSum = ms.findMaxSum(rect);
		System.out.println(maxSum);
		keyboard.close();
	}
	
	private int findMaxSum(int[][] rect) {
		int[] singleArray;
		int maxSum = Integer.MIN_VALUE;//gives lowest possible integer value
		for (int i = 0; i < rect.length; i++) {
			singleArray = new int[rect[0].length];
			int curr = 0;
			int maxCurrent = Integer.MIN_VALUE;
			for (int k = i; k < rect.length; k++) {
				curr = 0;
				for (int j = 0; j < rect[0].length; j++) {
					singleArray[j] = singleArray[j] + rect[k][j];
					curr = curr + singleArray[j];
					if (curr > maxCurrent) {
						maxCurrent = curr;
					}
					if (curr < 0) {
						curr = 0;
					}
				}
				if (maxSum < maxCurrent){
					maxSum = maxCurrent;
				}
			}
		}
		return maxSum;
	}
}
