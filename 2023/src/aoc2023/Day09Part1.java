package aoc2023;

import java.util.LinkedList;
import java.util.List;

class Substractor {
	
	public long process(String line) {
		List<Long> source = new LinkedList<>();
		for(String s: line.split(" ")) source.add(Long.parseLong(s));
		System.err.println("Processing : " + source.toString());
		return process(source) + source.get(source.size()-1);
	}
	
	public Long process(List<Long> source) {
		boolean endRecursion = true;
		List<Long> result = new LinkedList<>();
		for(int i = 1 ; i < source.size() ; i++) {
			long diff = source.get(i)-source.get(i-1);
			if(diff != 0) endRecursion = false;
			result.add(diff);
		}
		if(endRecursion) return (long) 0;
		return result.get(result.size()-1) + process(result);
	}
	
}

public class Day09Part1 {

	public static final String INPUT_NAME = "input_day09.txt";
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);

		int result = 0;
		
		String line = null;
		while(null != (line = fs.readLine())){
			result += new Substractor().process(line);	
		}
		
		System.out.print("Le résultat est : " + result);
		fs.close();
	}
	
}
