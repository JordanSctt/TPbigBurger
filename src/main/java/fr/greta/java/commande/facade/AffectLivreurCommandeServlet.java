package fr.greta.java.commande.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.livreur.domain.Livreur;
import fr.greta.java.livreur.domain.LivreurService;
import fr.greta.java.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/affichageCommande/affectLivreur")
public class AffectLivreurCommandeServlet extends HttpServlet {

    CommandeService service = new CommandeService();
    LivreurService livreurService = new LivreurService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandeId = request.getParameter("commande_id");

        HttpSession session = request.getSession();
        User userEnCours = (User)session.getAttribute("userConnected");

        // request.setAttribute("commande_id", commandeId);
        //request.setAttribute("userRole", userEnCours.getRole());
        try {
            Commande commande = service.findById(Integer.parseInt(commandeId));
            List<Livreur> livreurs = livreurService.findAllLivreursAvailable();

            if (livreurs.stream().count() > 0) {

                livreurService.setCommande(livreurs.get(0), commande);
                service.updateEtatCommande(userEnCours, commande);
                response.sendRedirect(request.getContextPath() + "/affichageCommande?successMessage=AFFECTED_CREATED");
            } else {
                redirectAffectedLivreurError(request, response);
            }

        } catch (ServiceException | RepositoryException e) {
            e.printStackTrace();
            redirectAffectedLivreurError(request, response);
        }
    }

    private void redirectAffectedLivreurError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(request.getContextPath() + "/affichageCommande?errorMessage=AFFECTED_ERROR");
    }
}