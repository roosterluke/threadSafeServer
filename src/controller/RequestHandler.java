package controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.NumberBook;
import service.RequestValidator;

/**
 * Servlet implementation class RequestHandler
 */
@WebServlet("/numbers")
public class RequestHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestHandler() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		NumberBook book = (NumberBook) request.getServletContext().getAttribute("book");
		response.getWriter().append("Book Number: ").append(book.getNumber());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sc = request.getServletContext();
		NumberBook book = (NumberBook) sc.getAttribute("book");
		Set validChars = (HashSet) sc.getAttribute("validChars");
		String numberPosted = request.getParameter("number");
		RequestValidator validator = new RequestValidator();
		if(validator.isValidNumber(request.getParameter("number"), (HashSet<Character>) sc.getAttribute("validChars"))) {
			try {
				book.saveNumber(numberPosted);
			} catch (Exception e) {
				System.out.println(".txt file could not be found");
				e.printStackTrace();
			}
		} else {
			return; // POST data is not well formed
		}
	}

}
