package fr.greta.java.user.facade;

import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.Admin;
import fr.greta.java.user.domain.User;
import fr.greta.java.user.domain.UserService;
import fr.greta.java.user.domain.UserWrapper;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;



@WebServlet("/ConnectionUser")
public class UserConnectionServlet extends HttpServlet {
		  
	
	private static final String NAME = "name_parameter";
	private static final String PASSWORD = "password_parameter";
	
	private static UserService userService = new UserService();
	private static UserDTOWrapper userDTOWrapper = new UserDTOWrapper();

	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	    	String name = request.getParameter(NAME);
	    	String password = request.getParameter(PASSWORD);

	    	try {
				User user = userService.findByNameAndPassword(name, password);

				if (user.getName() == null) {
					HttpSession session = request.getSession();
					request.getRequestDispatcher("connectionFail.jsp").forward(request, response);
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("userConnected", userDTOWrapper.toDTO(user));
					request.setAttribute("isAdmin", user instanceof Admin);
					request.getRequestDispatcher("accueil.jsp").forward(request, response);
				}

			} catch (ServiceException e) {
				e.printStackTrace();
			}
	    }
}
