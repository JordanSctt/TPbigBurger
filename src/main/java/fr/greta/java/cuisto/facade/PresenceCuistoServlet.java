package fr.greta.java.cuisto.facade;


import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.cuisto.domain.CuistoService;
import fr.greta.java.cuisto.persistence.CuistoRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/PresenceCuisto/present")
public class PresenceCuistoServlet extends HttpServlet {


    CuistoRepository repository = new CuistoRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cuistoId = request.getParameter("cuisto_id");
        try {
            repository.updatePresencePresentCuisto(Integer.parseInt(cuistoId));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/gestionCuisto");
    }
}

