package data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberBook {
	
	private static int num = 1;
	private static String logFileLocation = "./numbers.log";
	
	// Initialize new number book by replacing file with blank
	public static void initLogFile() throws IOException {
		List<String> lines = Arrays.asList("");
		Path file = Paths.get(logFileLocation);
		Files.write(file, lines, Charset.forName("UTF-8"));
	}

	// Opens file to ensure non-duplicate entry and updates before closing.
	// Returns false if duplicate record exists, returns true if new record added
	public synchronized static boolean saveNumber(String number) throws Exception {
		File file = new File(logFileLocation);
		Path fileLocation = Paths.get(logFileLocation);
		BufferedReader br = new BufferedReader(new FileReader(logFileLocation));
		String fileContents;
		Set existingRecords = new HashSet<String>();
		while ((fileContents = br.readLine()) != null) {
			existingRecords.add(fileContents.toString());
		}
		if(existingRecords.contains(number)) {
			return false;
		} else {
			List<String> lines = Arrays.asList(number);
			Files.write(fileLocation, lines, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
			return true;
		}
	}
	
	public String getNumber() {
		return String.valueOf(num);
	}
	
	public boolean validateUniqueEntry(String number) {
		return false;
	}
}
