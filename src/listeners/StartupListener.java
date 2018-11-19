package listeners;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import data.NumberBook;

public class StartupListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		NumberBook book = new NumberBook();
		try {
			book.initLogFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sc.setAttribute("book", book); // Single point of database access
		Set<Character> validChars = new HashSet<>();
		validChars.add('0');
		validChars.add('1');
		validChars.add('2');
		validChars.add('3');
		validChars.add('4');
		validChars.add('5');
		validChars.add('6');
		validChars.add('7');
		validChars.add('8');
		validChars.add('9');		
		sc.setAttribute("validChars", validChars); // Valid input characters
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		
	}	
	
}