package fr.greta.java.user.facade;

import fr.greta.java.user.domain.Admin;
import fr.greta.java.user.domain.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/accueil")
public class AccueilServletController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		User user = (User)session.getAttribute("userConnected");
		req.setAttribute("isAdmin", user instanceof Admin);
		
		RequestDispatcher dispatch = req.getRequestDispatcher("accueil.jsp");
		dispatch.forward(req, resp);			
	}
}
