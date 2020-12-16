package fr.greta.java.livreur.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.livreur.domain.Livreur;
import fr.greta.java.livreur.domain.LivreurService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/gestionLivreur")
public class GestionLivreurServlet extends HttpServlet {

    private LivreurService service = new LivreurService();
    private LivreurDTOWrapper wrapper = new LivreurDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Livreur> allLivreurs = service.findAllLivreurs();

            request.setAttribute("livreurs", wrapper.toDTOS(allLivreurs));


            request.getRequestDispatcher("livreur.jsp").forward(request, response);
        } catch (ServiceException | RepositoryException e) {
            e.printStackTrace();
        }
    }


}
