package aoc2023;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileScanner {
	private Scanner _scanner;
	
	public FileScanner(String filePath) {
		File file = new File(filePath);
		try {
			_scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String readLine() {
		if (_scanner.hasNextLine()) {
			return _scanner.nextLine();
		}
		return null;
	}
	
	public void close() {
		_scanner.close();
	}
	
}
