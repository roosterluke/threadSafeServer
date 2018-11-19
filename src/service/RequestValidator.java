package service;

import java.util.HashSet;

public class RequestValidator {
	
	
	// Checks the input is a well formed String, exactly 9 characters and each character
	// belongs to the valid character set
	public boolean isValidNumber(String lineIn, HashSet<Character> validChars) {
		if(!(lineIn.length() == 9)) {
			return false;
		}
		for(int i = 0; i < lineIn.length(); i++) {
			if(!validChars.contains(lineIn.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
