package aoc2023;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Node {
	
	protected String _id;
	
	protected String _left;
	
	protected String _right;
	
	public Node(String id, String left, String right) {
		_id = id;
		_left = left;
		_right = right;
	}
	
	public String getId() {
		return _id;
	}
	
	public String get(char inst) {
		if(inst == 'R') return _right;
		if(inst == 'L') return _left;
		return null;
	}
	
}

public class Day08Part1 {

	public static final String INPUT_NAME = "input_day08.txt";
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner(INPUT_NAME);

		Map<String, Node> nodes = new HashMap<>(); 
		
		
		/*
		 * INIT INSTRUCTIONS
		 */
		char[] instructions = fs.readLine().toCharArray();
		/*
		 * INIT NODES
		 */
		String line = fs.readLine();
		while(null != (line = fs.readLine())) {
			String[] splittedLine = line.split(" ");
			String id = splittedLine[0];
			String left = splittedLine[2].substring(1,4);
			String right = splittedLine[3].substring(0,3);
			System.err.println("Creating node " + id + " --> " + left + ", " + right);
			nodes.put(id, new Node(id, left, right));
		}
		
		
		/*
		 * GET MIN LOCATION
		 */
		
		int result = 0;
		Node currentNode = nodes.get("AAA");
		while(!currentNode.getId().equals("ZZZ")) {	
			for(char inst: instructions) {
				currentNode = nodes.get(currentNode.get(inst));
				result++;
				if(currentNode.getId().equals("ZZZ")) break;
			}
		}
		
		System.out.print("Le résultat est : " + result);
		fs.close();
	}
	
}
