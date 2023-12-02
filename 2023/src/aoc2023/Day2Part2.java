package aoc2023;

class DiceGame2 extends DiceGame{
	
	public DiceGame2(String game) {
		super(game);
	}
	
	public int minset_pow() {
		int g = 0;
		int b = 0;
		int r = 0;
		
		for(Round round: _rounds) {
			if(g < round.get("green")) g = round.get("green");
			if(b < round.get("blue")) b = round.get("blue");
			if(r < round.get("red")) r = round.get("red");
		}
		
		return g * b * r;
	}
	
}

public class Day2Part2 {

	public static final String INPUT_NAME = "input_day2.txt";
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);
		int result = 0;
		String s = "";
		
		while(true){
			s = fs.readLine();
			if(null == s) break;
			DiceGame2 d = new DiceGame2(s);
			result += d.minset_pow();
		}
		
		System.out.print("Le résultat est : " + result);
		fs.close();
	}
	
}
