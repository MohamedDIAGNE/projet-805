package com.tracking.Servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mongodb.DBObject;
import com.tracking.Database.DatabaseClass;

/**
 * Servlet implementation class DetailActiviteServlet
 */
public class DetailActiviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public DatabaseClass database   ;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DetailActiviteServlet() {
        super();
        this.database= new DatabaseClass(); 
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if (session.getAttribute("login") == null) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/SignInPage.jsp").forward(request, response);	
		}else {
			System.out.println("detailactivite get");
			List<DBObject> activites = this.database.FindActivities(session.getAttribute("id").toString());
			request.setAttribute("activites", activites);
			this.getServletContext().getRequestDispatcher("/WEB-INF/views/DetailActivite.jsp").forward(request, response);	
		}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
