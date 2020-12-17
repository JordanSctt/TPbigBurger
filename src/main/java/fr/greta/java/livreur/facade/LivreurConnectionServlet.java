package fr.greta.java.livreur.facade;

import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.livreur.domain.Livreur;
import fr.greta.java.livreur.domain.LivreurService;
import fr.greta.java.user.domain.User;
import fr.greta.java.user.domain.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/connectionLivreur")
public class LivreurConnectionServlet extends HttpServlet {


    private static final String NAME = "name_parameter";
    private static final String PASSWORD = "password_parameter";

    private static LivreurService livreurService = new LivreurService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter(NAME);
        String password = request.getParameter(PASSWORD);

        try {
            Livreur livreur = livreurService.findByNameAndPassword(name, password);

            if (livreur.getName() == null) {
                request.getRequestDispatcher("connectionFail.jsp").forward(request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("livreurConnected", livreur);

                request.getRequestDispatcher("accueil.jsp").forward(request, response);
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
