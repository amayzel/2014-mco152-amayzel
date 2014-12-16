package mayzel.triangle;

public class Triangle {
	private StringBuilder tri;

	public Triangle(int height){
		tri = new StringBuilder();
		int firstSpace;
		int secondSpace;
		String stars = ("*");
		int lastRow = height * 2 - 1;

		for (int i = 1; i <= height-1; i++ ) {
			firstSpace = (lastRow - 2 * i +1) / 2;

			for(int j = 1; j <= firstSpace; j++){
				tri.append(" ");
			} 

			tri.append(stars);
			secondSpace = i * 2 -3; 
			if( i != 1 ){
				for(int k = 1;  k <= secondSpace; k++){
					tri.append(" ");
				}
			}
			if (i != 1){
				tri.append(stars);
			}
			tri.append("");
			tri.append("\n");
		}
		//last row
		for(int l = 1; l <= lastRow; l++){
			tri.append(stars);
		}
	}

	public String toString(){
		return tri.toString();
	}

	/*	public static void main(String args[]){
		Triangle shape = new Triangle(10);
		System.out.println(shape.toString());
	}*/

}
