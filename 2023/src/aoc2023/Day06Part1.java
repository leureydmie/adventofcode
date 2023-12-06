package aoc2023;

import java.util.LinkedList;
import java.util.List;

class Race {
	
	protected long _time;
	protected long _record;
	
	
	public Race(long time, long record) {
		_time = time;
		_record = record;
	}
	
	public long resolve() {
		int result = 0;
		
		for(int i = 1 ; i < _time ; i++) {
			if( i * (_time - i) > _record) result++;
		}
		
		return result;
	}
	
}

public class Day06Part1 {

	public static final String INPUT_NAME = "input_day06.txt";
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);
		int result = 1;
		List<Long> times = new LinkedList<>();
		List<Long> distances = new LinkedList<>();
		
		for(String time: fs.readLine().split(" ")) {
			if(time.equals("Time:")) continue;
			if(time.equals("")) continue;
			times.add(Long.parseLong(time));
		}

		for(String distance: fs.readLine().split(" ")) {
			if(distance.equals("Distance:")) continue;
			if(distance.equals("")) continue;
			distances.add(Long.parseLong(distance));
		}
		
		for(int i = 0 ; i < times.size() ; i++) {
			result *= new Race(times.get(i), distances.get(i)).resolve();
		}
		
		System.out.print("Le résultat est : " + result);
		fs.close();
	}
	
}
