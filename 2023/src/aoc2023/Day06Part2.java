package aoc2023;

public class Day06Part2 {

	public static final String INPUT_NAME = "input_day06.txt";
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);
		String time = "";
		String distance = "";
		
		for(String t: fs.readLine().split(" ")) {
			if(t.equals("Time:")) continue;
			if(t.equals("")) continue;
			time += t;
		}

		for(String d: fs.readLine().split(" ")) {
			if(d.equals("Distance:")) continue;
			if(d.equals("")) continue;
			distance += d;
		}
		
		long result = new Race(Long.parseLong(time), Long.parseLong(distance)).resolve();
		
		System.out.print("Le résultat est : " + result);
		fs.close();
	}
	
}
