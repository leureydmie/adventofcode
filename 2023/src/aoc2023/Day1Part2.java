package aoc2023;

import java.util.HashMap;
import java.util.Map;

public class Day1Part2 {
	
	public static int textCalibrationValue(String s, boolean reverse) {
		
		Map<String, Integer> digits = new HashMap<>();
		digits.put("zero", 0);
		digits.put("one", 1);
		digits.put("two", 2);
		digits.put("three", 3);
		digits.put("four", 4);
		digits.put("five", 5);
		digits.put("six", 6);
		digits.put("seven",7);
		digits.put("eight",8);
		digits.put("nine", 9);
		
		if(reverse == false) {
			for (String digit : digits.keySet()) {
				if (s.startsWith(digit)) {
					return digits.get(digit);
				}
			}
		}
		else {
			for (String digit : digits.keySet()) {
				if (s.endsWith(digit)) {
					return digits.get(digit);
				}
			}
		}
		return -1;
	}
	
	public static int calibrationValue(String s) {
		int result = 0;
		
		System.err.println("Recherche valeur de calibration dans : " + s);
		
		for (int i = 0; i<s.length(); i++) {
			int temp = Character.getNumericValue(s.charAt(i));
			if(temp != -1 && temp < 10) {
				result += temp * 10;
				break;
			}
			temp = textCalibrationValue(s.substring(i), false);
			if(temp != -1) {
				result += temp * 10;
				break;
			}
		}
		for (int i = s.length()-1; i >= 0; i--) {
			int temp = Character.getNumericValue(s.charAt(i));
			if(temp != -1 && temp < 10) {
				result += temp;
				break;
			}
			temp = textCalibrationValue(s.substring(0, i+1), true);
			if(temp != -1) {
				result += temp;
				break;
			}
		}
		
		System.err.println("Valeur de calibration intermédiaire : " + result);
		return result;
	}
	
	public static void main(String[] args) {
		FileScanner fs = new FileScanner("input.txt");
		int result = 0;
		String s = "";
		while(null != s){
			s = fs.readLine();
			if(null == s) break;
			result += calibrationValue(s);
		}
		
		System.out.print("La valeur de calibration est : " + result);
		fs.close();
	}
}
