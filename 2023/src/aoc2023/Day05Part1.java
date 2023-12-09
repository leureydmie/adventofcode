package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Range {
	
	protected long _dest;
	
	protected long _source;
	
	protected long _length;
	
	public Range(long dest, long source, long length) {
		_dest = dest;
		_source = source;
		_length = length;
	}
	
	public long convert(long s) {
		if(s < _source || s > _source + _length) return -1;
		return _dest + (s - _source);
	}
	
	public long reverse(long s) {
		if(s < _dest || s > _dest + _length) return -1;
		return _source + (s - _dest);
	}
	
}

class Converter {
	
	protected List<Range> _ranges;
	
	public Converter() {
		_ranges = new LinkedList<>();
	}
	
	public void putRange(long dest, long source, long length) {
		_ranges.add(new Range(dest, source, length));
	}
	
	public void putRange(String s) {
		String[] splittedString = s.split(" ");
		putRange(Long.parseLong(splittedString[0]), 
				 Long.parseLong(splittedString[1]), 
				 Long.parseLong(splittedString[2]));
	}
	
	public Long convert(long s) {
		for(Range r: _ranges) {
			if(r.convert(s) != -1) return r.convert(s);
		}
		return s;
	}
	
	public long reverse(long s) {
		for(Range r: _ranges) {
			if(r.reverse(s) != -1) return r.reverse(s);
		}
		return s;
	}
	
}

class Process {
	
	List<String> _operations;
	
	Map<String, Converter> _converters;
	
	public Process() {
		_operations = new LinkedList<>();
		_converters = new HashMap<>();
	}
	
	public void put(String op) {
		_operations.add(op);
		_converters.put(op, new Converter());
	}
	
	public void put(String op, String line) {
		_converters.get(op).putRange(line);
	}
	
	public Converter get(String op) {
		return _converters.get(op);
	}
	
	public long process(long source) {
		long temp = source;
		for(String op: _operations) {
			temp = _converters.get(op).convert(temp);
		}
		return temp;
	}
	
	public long reverse(long dest) {
		long temp = dest;
		for(int i = _operations.size()-1 ; i >= 0 ; i--) {
			temp = _converters.get(_operations.get(i)).reverse(temp);
		}
		return temp;
	}
	
	
}

public class Day05Part1 {

	public static final String INPUT_NAME = "input_day05.txt";
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);

		Process seedToLocation = new Process();
		List<Long> seeds = new LinkedList<>();
		
		
		/*
		 * INIT SEEDS
		 */
		String[] splittedLine = fs.readLine().split(" ");
		for(String seed: splittedLine) {
			if(seed.equals("seeds:")) continue;
			seeds.add(Long.parseLong(seed));
		}
		
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
		
		long minLocation = Long.MAX_VALUE;
		for(long seed : seeds) {
			long tempLocation = seedToLocation.process(seed);
			if(tempLocation < minLocation) {
				minLocation = tempLocation;
				System.err.println("New min location: " + tempLocation);
			}
		}
		
		System.out.print("Le résultat est : " + 
				seedToLocation.process(seedToLocation.reverse(minLocation))
		);
		fs.close();
	}
	
}
