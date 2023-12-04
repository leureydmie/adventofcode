package aoc2023;

public class Day03Part2 {

	public static final String INPUT_NAME = "input_day3.txt";
	
	public static final String NUMBERS = "0123456789";
	
	public static final String NUMBERS_AND_DOT = "0123456789.";
	
	public static boolean testDigit(String line, int index) {
		if(null == line) return false;
		if(index >= line.length()) return false;
		if(index < 0) return false;
		return NUMBERS.contains(line.substring(index, index+1));
	}
	
	public static int getLeftNumber(String line, int i) {
		if(null == line) return 1;
		if(i-1 < 0) return 1;
		if(!NUMBERS.contains(line.substring(i-1,i))) return 1;
		if(i-2 < 0 || !NUMBERS.contains(line.substring(i-2, i-1))) 
			return Integer.parseInt(line.substring(i-1,  i+1));
		return Integer.parseInt(line.substring(i-3, i));
	}
	
	public static int getRightNumber(String line, int i) {
		if(null == line) return 1;
		if(i+1 >= line.length()) return 1;
		if(!NUMBERS.contains(line.substring(i+1,i+2))) return 1;
		if(i+2 >= line.length() || !NUMBERS.contains(line.substring(i+2, i+3))) 
			return Integer.parseInt(line.substring(i, i+2));
		return Integer.parseInt(line.substring(i, i+3));
	}
	
	public static int searchParts(String current, String previous, String next) {
		System.err.println("Searching parts in : " + current);

		int result = 0;
		
		for(int i = 0 ; i < current.length() ; i++) {
	
			int temp = 1;
			int countEngineParts = 0;
			
			if(!(current.charAt(i) == '*')) continue;
			
			// LEFT
			if(testDigit(current, i-1)) {
				countEngineParts++;
				temp *= getLeftNumber(current, i-1);
			}
			
			// RIGHT
			if(testDigit(current, i+1)) {
				countEngineParts++;
				temp *= getRightNumber(current, i+1);
			}
			
			// TOP
			if(testDigit(previous, i)) {
				countEngineParts++;
				temp *= getLeftNumber(current, i-1);
			} else {
				if(testDigit(previous, i-1)) {
					countEngineParts++;
					temp *= getLeftNumber(previous, i-1);
				}
				if(testDigit(previous, i+1)) {
					countEngineParts++;
					temp *= getRightNumber(previous, i+1);
				}
			}
			
			// BOTTOM
			if(testDigit(next, i)) {
				countEngineParts++;
				temp *= getLeftNumber(current, i-1);
			} else {
				if(testDigit(next, i-1)) {
					countEngineParts++;
					temp *= getLeftNumber(next, i-1);
				}
				if(testDigit(next, i+1)) {
					countEngineParts++;
					temp *= getRightNumber(next, i+1);
				}
				
			}
			
			if(countEngineParts == 2) result += temp;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);
		int result = 0;
		String current = fs.readLine();
		String previous = null;
		String next = null;

		while(true){	
			next = fs.readLine();
			if(null == current) break;
			result += searchParts(current, previous, next);
			previous = current;
			current = next;		
		}
		
		System.out.print("Le résultat est : " + result);
		fs.close();
	}
	
}
