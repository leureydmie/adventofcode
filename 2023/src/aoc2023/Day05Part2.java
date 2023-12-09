package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Day05Part2 {

	public static final String INPUT_NAME = "input_day05.txt";
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);

		Process seedToLocation = new Process();
		List<Long> seeds = new LinkedList<>();
		List<Long> ranges = new LinkedList<>();
		
		/*
		 * INIT SEEDS
		 */
		String[] splittedLine = fs.readLine().split(" ");
		boolean isSeed = true;
		for(String seed: splittedLine) {
			if(seed.equals("seeds:")) continue; // is garbage
			if(isSeed) seeds.add(Long.parseLong(seed));
			else ranges.add(Long.parseLong(seed));
			isSeed = !isSeed;
		}
		
		System.err.println(seeds.toString());
		System.err.println(ranges.toString());
		
		/*
		 * INIT CONVERTERS
		 */
		String line = null;
		String op = null;
		while((line = fs.readLine()) != null){
			if(line.length() <= 5) continue;
			if(line.charAt(line.length() - 1) == ':') {
				op = line.split(" ")[0];
				seedToLocation.put(op);
			}
			else {
				seedToLocation.put(op, line);
			}
		}
		
		/*
		 * GET MIN LOCATION
		 */
		
		long minLocation = 51752125;
		for(int location = 0 ; location < 51752125 ; location++) {
			long tempSeed = seedToLocation.reverse(location);
			for(int i = 0 ; i < seeds.size() ; i++) {
				long seed = seeds.get(i);
				long range = ranges.get(i);
				if(seed <= tempSeed && tempSeed <= seed + range ) {
					minLocation = location; 
					System.err.println("New min seed / location: " + tempSeed + " / " + location);
					System.err.println(seed + " " + tempSeed + " " + (seed+range));
					break;
				}
			}
			if(minLocation < 51752125) break;
		}
		
		
		System.out.print("Le résultat est : " + minLocation);
		fs.close();
	}
	
}
