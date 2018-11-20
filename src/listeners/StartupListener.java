package listeners;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import data.NumberBook;

public class StartupListener implements ServletContextListener {
	
	Runnable requestMonitor = new Runnable() {
	    public void run() {
	        System.out.println("Hello world");
	    }
	};
	
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		NumberBook book = new NumberBook();
		int recordsInsertedTotalCounter = 0;
		int recordsInsertedClockCounter = 0;
		int recordsRejectedClockCounter = 0;
		sc.setAttribute("recordsInsertedTotalCounter", recordsInsertedTotalCounter);
		sc.setAttribute("recordsInsertedClockCounter", recordsInsertedClockCounter);
		sc.setAttribute("recordsRejectedClockCounter", recordsRejectedClockCounter);
		Runnable requestMonitor = new Runnable() {
		    public void run() {
		    	int totalRecordsAdded = (int) sc.getAttribute("recordsInsertedTotalCounter");
				int totalRecordsClock = (int) sc.getAttribute("recordsInsertedClockCounter");
				int totalRecordsRejected = (int) sc.getAttribute("recordsRejectedClockCounter");
		        System.out.println("Received " + totalRecordsClock + " unique requests.");
		        System.out.println("Received " + totalRecordsRejected + " duplicates.");
		        System.out.println("Received " + totalRecordsAdded + " total.");
		        totalRecordsClock = 0;
		        totalRecordsRejected = 0;
		        sc.setAttribute("recordsInsertedClockCounter", totalRecordsClock);
		        sc.setAttribute("recordsRejectedClockCounter", totalRecordsRejected);
		    }
		};
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
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(requestMonitor, 0, 10, TimeUnit.SECONDS);
	}
	
	public void contextDestroyed(ServletContextEvent event) {
		
	}	
	
}