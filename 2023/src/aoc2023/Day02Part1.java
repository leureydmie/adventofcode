package aoc2023;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Round {
	
	Map<String, Integer> _revealed;
	
	public Round(String round) {
		_revealed = new HashMap<>();
		for (String s: round.split(",")) {
			String[] splitted_set = s.split(" ");
			if(splitted_set[0].equals("")) {
				_revealed.put(splitted_set[2], Integer.parseInt(splitted_set[1]));
			}
			else {
				_revealed.put(splitted_set[1], Integer.parseInt(splitted_set[0]));
			}	
		}
	}
	
	public int get(String s) {
		return null == _revealed.get(s) ? 0 : _revealed.get(s);
	}
}

class DiceGame{
	
	protected int _id;
	
	protected LinkedList<Round> _rounds;
	
	public DiceGame(String game) {
		System.err.println("Creating game for line : " + game);
		_id = Integer.parseInt(game.split(":")[0].split(" ")[1]);
		
		_rounds = new LinkedList<>();
		for(String s: game.split(":")[1].split(";")) {
			_rounds.add(new Round(s));
		}
	}
	
	public int getId() {
		return _id;
	}
	
	public boolean isPossible() {
		for(Round r: _rounds) {
			if(r.get("green") > 13 || r.get("blue") > 14 || r.get("red") > 12) {
				return false;
			}
		}
		return true;
	}
	
}

public class Day02Part1 {

	public static final String INPUT_NAME = "input_day2.txt";
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);
		int result = 0;
		String s = "";
		
		while(true){
			s = fs.readLine();
			if(null == s) break;
			DiceGame d = new DiceGame(s);
			if(d.isPossible()) result += d.getId();
		}
		
		System.out.print("Les parties possibles sont : " + result);
		fs.close();
	}
	
}
