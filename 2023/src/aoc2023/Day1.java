package aoc2023;

public class Day1 {

	public static int calibrationValue(String s) {
		int result = 0;
		
		System.err.println("Recherche valeur de calibration dans : " + s);
		
		for (int i = 0; i<s.length(); i++) {
			int temp = Character.getNumericValue(s.charAt(i));
			if(temp != -1 && temp < 10) {
				result += temp * 10;
				break;
			}
		}
		for (int i = s.length()-1; i >= 0; i--) {
			int temp = Character.getNumericValue(s.charAt(i));
			if(temp != -1 && temp < 10) {
				result += temp;
				break;
			}
		}
		
		System.err.println("Valeur de calibration intermédiaire : " + result);
		return result;
	}
	
	public static void main(String[] args) {
		/*
		 * PART ONE
		 */	
		FileScanner fs = new FileScanner("input.txt");
		int result = 0;
		String s = "";
		while(null != s){
			s = fs.readLine();
			if(null == s) break;
			result += calibrationValue(s);
		}
		
		System.out.print("La valeur de calibration est : " + result);
		fs.close();
	}
	
}
