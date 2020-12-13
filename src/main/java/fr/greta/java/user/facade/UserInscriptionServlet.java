package fr.greta.java.user.facade;

import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.User;
import fr.greta.java.user.domain.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/InscriptionUser")
public class UserInscriptionServlet extends HttpServlet {

	private static final String NAME = "name_parameter";
	private static final String PASSWORD = "password_parameter";
	private static final String PHONE = "phone_parameter";
	private static final String ADRESSE = "adresse_parameter";

	private static UserService userService = new UserService();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			User user = new User();
			user.setName(request.getParameter(NAME));
			user.setPassword(request.getParameter(PASSWORD));
			user.setPhone(request.getParameter(PHONE));
			user.setAdresse(request.getParameter(ADRESSE));
			userService.createUser(user);
			request.getRequestDispatcher("accueil.jsp").forward(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
