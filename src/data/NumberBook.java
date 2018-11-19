package data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class NumberBook {
	
	private static int num = 1;
	private static String logFileLocation = "./numbers.log";
	
	public static void initLogFile() throws IOException {
		List<String> lines = Arrays.asList("");
		Path file = Paths.get(logFileLocation);
		Files.write(file, lines, Charset.forName("UTF-8"));
	}

	public synchronized static void saveNumber(String number) {
		
	}
	
	public String getNumber() {
		return String.valueOf(num);
	}
	
	public boolean validateNumberFormat(String number) {
		return false;
	}
	
	public boolean validateUniqueEntry(String number) {
		return false;
	}
}
