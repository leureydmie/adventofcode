package aoc2023;

import java.util.ArrayList;
import java.util.List;


public class Day04Part2 {

	public static final String INPUT_NAME = "input_day04.txt";
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);

		List<Card> cards = new ArrayList<>();
		List<Integer> copies = new ArrayList<>();
		int totalScore = 0;
		
		String current = null;
		while(true){	
			current = fs.readLine();
			if(null == current) break;
			cards.add(new Card(current));
			copies.add(1);
		}
		
		for(Card c: cards) {
			int index = cards.indexOf(c);
			int winningCards = c.getWinningCards();
			
			totalScore += copies.get(index);
			for(int i = index + 1 ; i <= (index + winningCards) && i < cards.size() ; i++) {
				int newCopies = copies.get(i) + copies.get(index);
				copies.add(i, newCopies);
				copies.remove(i+1);
			}
		}
		
		System.out.print("Le résultat est : " + totalScore);
		fs.close();
	}
	
}
