package aoc2023;

import java.util.LinkedList;
import java.util.List;

class Card {
	
	protected int _id;
	
	protected List<Integer> _winningNumbers;
	
	protected List<Integer> _actualNumbers;
	
	protected int _score;
	
	protected int _winningCards;
	
	public Card(int id) {
		_id = id;
	}
	
	public Card(String line) {
		_score = 0;
		_winningCards = 0;
		_winningNumbers = new LinkedList<>();
		_actualNumbers = new LinkedList<>();
		
		System.err.println("Creating card from : " + line);
		
		String[] splitLine_colon = line.split(":");
		String[] id = splitLine_colon[0].split(" ");
		_id = Integer.parseInt(id[id.length-1]);
		System.err.println("--> Card id is : " + _id);
		
		String[] numbers = splitLine_colon[1].split("\\|");
		for(String winningNumber: numbers[0].split(" ")) {
			if(winningNumber.equals("")) continue;
			_winningNumbers.add(Integer.parseInt(winningNumber));
		}
		
		for(String actualNumber: numbers[1].split(" ")) {
			if(actualNumber.equals("")) continue;
			if(_winningNumbers.contains(Integer.parseInt(actualNumber))) {
				_score = _score == 0 ? 1 : _score * 2;
				_winningCards ++;
			}
				
			_actualNumbers.add(Integer.parseInt(actualNumber));
		}
		
		// System.err.println("--> Winning numbers are : " + _winningNumbers.toString());
		// System.err.println("--> Actual numbers are : " + _actualNumbers.toString());
		
		System.err.println("--> Card score is : " + _score);
	}
	
	public int getScore() {
		return _score;
	}
	
	public int getId() {
		return _id;
	}
	
	public int getWinningCards() {
		return _winningCards;
	}

}

public class Day04Part1 {

	public static final String INPUT_NAME = "input_day04.txt";
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);
		int result = 0;
		String current = null;

		while(true){	
			current = fs.readLine();
			if(null == current) break;
			result += new Card(current).getScore();
		}
		
		System.out.print("Le résultat est : " + result);
		fs.close();
	}
	
}
