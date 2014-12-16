package mayzel.morsecode;

import java.util.HashMap;
import java.util.Map;

public class MorseCode {
	private String codeTable[][];

	public MorseCode(){
		codeTable = new String [37][2];
		codeTable[0][0] = "A";				
		codeTable[0][1] = ".-";	
		codeTable[1][0] = "B";				
		codeTable[1][1] = "-...";
		codeTable[2][0] = "C";				
		codeTable[2][1] = "-.-.";
		codeTable[3][0] = "D";				
		codeTable[3][1] = "-..";
		codeTable[4][0] = "E";				
		codeTable[4][1] = ".";
		codeTable[5][0] = "F";				
		codeTable[5][1] = "..-.";
		codeTable[6][0] = "G";				
		codeTable[6][1] = "--.";
		codeTable[7][0] = "H";				
		codeTable[7][1] = "....";
		codeTable[8][0] = "I";				
		codeTable[8][1] = "..";
		codeTable[9][0] = "J";				
		codeTable[9][1] = ".---";
		codeTable[10][0] = "K";				
		codeTable[10][1] = "-.-";
		codeTable[11][0] = "L";				
		codeTable[11][1] = ".-..";
		codeTable[12][0] = "M";				
		codeTable[12][1] = "--";
		codeTable[13][0] = "N";				
		codeTable[13][1] = "-.";
		codeTable[14][0] = "O";				
		codeTable[14][1] = "---";
		codeTable[15][0] = "P";				
		codeTable[15][1] = ".--.";
		codeTable[16][0] = "Q";				
		codeTable[16][1] = "--.-";
		codeTable[17][0] = "R";				
		codeTable[17][1] = ".-.";
		codeTable[18][0] = "S";				
		codeTable[18][1] = "...";
		codeTable[19][0] = "T";				
		codeTable[19][1] = "-";
		codeTable[20][0] = "U";				
		codeTable[20][1] = "..-";
		codeTable[21][0] = "V";				
		codeTable[21][1] = "...-";
		codeTable[22][0] = "W";				
		codeTable[22][1] = ".--";
		codeTable[23][0] = "X";				
		codeTable[23][1] = "-..-";
		codeTable[24][0] = "Y";				
		codeTable[24][1] = "-.--";
		codeTable[25][0] = "Z";				
		codeTable[25][1] = "--..";
		codeTable[26][0] = "0";				
		codeTable[26][1] = "-----";
		codeTable[27][0] = "1";				
		codeTable[27][1] = ".----";
		codeTable[28][0] = "2";				
		codeTable[28][1] = "..---";
		codeTable[29][0] = "3";				
		codeTable[29][1] = "...--";
		codeTable[30][0] = "4";				
		codeTable[30][1] = "....-";
		codeTable[31][0] = "5";				
		codeTable[31][1] = ".....";
		codeTable[32][0] = "6";				
		codeTable[32][1] = "-....";
		codeTable[33][0] = "7";				
		codeTable[33][1] = "--...";
		codeTable[34][0] = "8";				
		codeTable[34][1] = "---..";
		codeTable[35][0] = "9";				
		codeTable[35][1] = "----.";
		codeTable[36][0] = " ";				
		codeTable[36][1] = "/";
		
	/*	Map<String,String> map = new HashMap<String,String>();
		
			for(String key : codeTable){
				String value = codeTable[0][i];
				String value2 = codeTable[1][i];
				int i++;
					map.put();
					//bbbbbblllllllllaaaaaaahhhhhhhhhhh!!!!
				
			}*/

	}

	public String toMorseCode(String text){
		StringBuilder codeWord= new StringBuilder();
		for(int i=0; i<text.length();i++){
			char wordLetter = text.charAt(i);
			String letter = "" + wordLetter;
			for(int k=0;k<codeTable.length;k++){
				if(letter.equalsIgnoreCase(codeTable[k][0])){
					String code = codeTable[k][1]+ " ";
					codeWord.append(code);
				}
			}	
		}
		if(codeWord.length()> 0){
		codeWord.deleteCharAt(codeWord.length()-1);
		}
		return codeWord.toString();
	}

	public String toPlainText(String morseCode){
		StringBuilder word= new StringBuilder();
		String [] codeLetter = morseCode.split(" ");
		for(int i=0; i<codeLetter.length;i++){
			for(int k=0;k<codeTable.length;k++){
				if(codeLetter[i].equalsIgnoreCase(codeTable[k][1])){
					String letter = codeTable[k][0];
					word.append(letter);
				}
			}	
		}
		return word.toString();

	}

/*	public static void main(String []args){
		MorseCode code = new MorseCode();
		System.out.println(code.toMorseCode("hello"));
		System.out.println(code.toPlainText(".... . .-.. .-.. ---"));
		System.out.println(code.toMorseCode(""));
		System.out.println(code.toMorseCode("bye"));


		
	}*/
}
