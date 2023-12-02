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
	/**
	 * @return The next line to be read ; null if no further line can be read.
	 */
	public String readLine() {
		if (_scanner.hasNextLine()) return _scanner.nextLine();
		return null;
	}
	
	
	/**
	 * @param N : The number of lines to read
	 * @param trimNewLines : if true then lines will be concatenated with no \n
	 * @return The next lines, concatenated ; null if no lines can be read. If the end of the file is encountered after a line has been read, then less than N lines can be returned
	 */
	public String readNLines(int N, boolean trimNewLines) {
		if(!_scanner.hasNextLine()) return null;
		String result = "";
		for(int i = 0 ; i < N ; i++) {
			if(_scanner.hasNextLine()) result += _scanner.nextLine();
			if(!trimNewLines) result += '\n';
		}
		return result;
	}
	
	public void close() {
		_scanner.close();
	}
	
}
