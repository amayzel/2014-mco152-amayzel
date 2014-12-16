package mayzel.scrabble;

public class TimingScrabbleDictionary {

	public static void main(String[] args) {
		ScrabbleDictionary list = new ScrabbleDictionary();
		
		long startTime = System.currentTimeMillis();
		for(int i =0; i< 1000000;i++){
			list.contains("hello");
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		
		System.out.println(totalTime);

	}

}
