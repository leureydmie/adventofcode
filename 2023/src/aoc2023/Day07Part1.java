package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class PokerCard {
	
	private int FIVE_KIND = 6;
	private int FOUR_KIND = 5;
	private int FULL_HOUSE = 4;
	private int THREE_KIND = 3;
	private int TWO_PAIRS = 2;
	private int ONE_PAIR = 1;
	private int NOTHING = 0;
	
	protected int _hand;
	
	protected int _value;
	
	protected int _bid;
	
	protected Map<Integer, Integer> _source;
	
	protected long _length;
	
	public PokerCard(String hand, String bid) {
		_hand = Integer.parseInt(hand, 16);
		_source = new HashMap<>();
		fillSource(hand);	
		_value = calculateValue();
		_bid = Integer.parseInt(bid);
	}
	
	private void fillSource(String hand) {
		for (char card : hand.toCharArray()) {
			int hexCard = Integer.parseInt(new String(new char[] {card}), 16);
			if(null == _source.get(hexCard)) _source.put(hexCard, 1);
			else _source.put(hexCard, _source.get(hexCard) + 1);
		}
	}
	
	private int calculateValue() {
		int jCount = _source.get(1);
		_source.remove(1);
		Collection<Integer> cardCounts = _source.values();
		int maxCount = 0;
		for(int i: cardCounts) maxCount = i > maxCount ? i : maxCount;
		
		if(cardCounts.contains(5)) return 6; // Five of a kind
		if(cardCounts.size() == 5) return 0; // High card
		if(cardCounts.size() == 4) return 1; // One pair
		if(cardCounts.contains(3)) {
			if(cardCounts.contains(2)) return 4; // Full House
			return 3; // Three of a kind
		}
		if(cardCounts.contains(4)) return 5; // Four of a kind
		
		return 2; // Must be two pairs ? Maybe ?
	}
	
	public int getValue() {
		return _value;
	}
	
	public int getHand() {
		return _hand;
	}
	
	public int getBid() {
		return _bid;
	}
}

public class Day07Part1 {

	public static final String INPUT_NAME = "input_day07.txt";
	
	public static String replaceHandValues(String hand) {
		String result = new String(hand);
		result = result.replace('A', 'E');
		result = result.replace('K', 'D');
		result = result.replace('Q', 'C');
		result = result.replace('J', 'B');
		result = result.replace('T', 'A');
		return result;
	}
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);

		List<PokerCard> cards = new ArrayList<>();
		int result = 0;
		
		String line = null;
		while(null != (line = fs.readLine())){
			String[] splittedLine = line.split(" ");
			String hand = replaceHandValues(splittedLine[0]);
			PokerCard card = new PokerCard(hand, line.split(" ")[1]);
			
			int sizeBeforeInsert = cards.size();
			
			for(int i = 0 ; i < sizeBeforeInsert ; i++) {
				PokerCard cardAtI = cards.get(i);
				if(card.getValue() < cardAtI.getValue()) {
					cards.add(i, card);
					break;
				}
				if(card.getValue() == cardAtI.getValue()) {
					if(card.getHand() < cardAtI.getHand()) {
						cards.add(i, card);
						break;
					}
				}
			}
			if(sizeBeforeInsert == cards.size()) cards.add(card);
			
		}
		
		
		for(int i = 0 ; i < cards.size() ; i++) {
			result += (i+1) * cards.get(i).getBid();
		}
		
		System.out.print("Le résultat est : " + result);
		fs.close();
	}
	
}
