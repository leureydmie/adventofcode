package aoc2023;

public class Day03Part1 {

	public static final String INPUT_NAME = "input_day3.txt";
	
	public static final String NUMBERS = "0123456789";
	
	public static final String NUMBERS_AND_DOT = "0123456789.";
	
	public static boolean testSymbol(String line, int index) {
		if(null == line) return false;
		if(index >= line.length()) return false;
		if(index < 0) return false;
		return !NUMBERS_AND_DOT.contains(line.substring(index, index+1));
	}
	
	public static int searchParts(String current, String previous, String next) {
		System.err.println("Searching parts in : " + current);

		int result = 0;
		
		for(int i = current.length() - 1 ; i >= 2 ; i--) {
			String s0 = current.substring(i,i+1);
			String s1 = current.substring(i-1,i);
			String s2 = current.substring(i-2,i-1);
			
			int temp = 0;
			boolean isEnginePart = false;
			
			if(!NUMBERS.contains(s0)) continue;
			
			isEnginePart = isEnginePart 
					|| testSymbol(previous, i) 
					|| testSymbol(next, i)
					|| testSymbol(current, i+1)
					|| testSymbol(previous, i+1)
					|| testSymbol(next, i+1);
			temp += Integer.parseInt(s0);
			
			if(NUMBERS.contains(s1)){
				isEnginePart = isEnginePart 
						|| testSymbol(previous, i-1) 
						|| testSymbol(next, i-1);
				temp += Integer.parseInt(s1) * 10;
			}
			else {
				isEnginePart = isEnginePart 
						|| testSymbol(previous, i-1) 
						|| testSymbol(next, i-1)
						|| testSymbol(current, i-1);
				if(isEnginePart) result += temp;
				continue;
			}
			
			if(NUMBERS.contains(s2)){
				isEnginePart = isEnginePart 
						|| testSymbol(previous, i-2) 
						|| testSymbol(next, i-2)
						|| testSymbol(previous, i-3)
						|| testSymbol(current, i-3)
						|| testSymbol(next, i-3);
				temp += Integer.parseInt(s2) * 100;
			}
			else {
				isEnginePart = isEnginePart 
						|| testSymbol(previous, i-2) 
						|| testSymbol(next, i-2)
						|| testSymbol(current, i-2);
				if(isEnginePart) result += temp;
				i--;
				continue;
			}
			i -= 2;
			if(isEnginePart) result += temp;
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
