package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.accounts.Accounts;
import com.data.ReadData;

/**
 * Servlet implementation class LogInServlet
 */
@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
		private List<Accounts> users;
		private ReadData readData;
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
       readData=new ReadData(); 
       users=readData.info();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String email=request.getParameter("Email");
		String password=request.getParameter("Password");
		
		HttpSession session=request.getSession(true);
		
		boolean loggedIn=false;
		readData=new ReadData(); 
		for(Accounts account:users){
		
			if ((email.equals(account.getEmail())) && (password.equals(account.getPassword()))){
				loggedIn=true;
				session.setAttribute("accounts", account);
				
				RequestDispatcher rs=request.getRequestDispatcher("openAcct.jsp");
				rs.forward(request, response);
				break;
			}
		}	
		
		if (!loggedIn)
			{			
			RequestDispatcher rs=request.getRequestDispatcher("verifyAcct.jsp");
			request.setAttribute("msg3", "<h4>Your account was not found, PLEASE re-enter email and password</h4>");
			rs.forward(request, response);
			}
		
	}

}
//doGet(request, response);