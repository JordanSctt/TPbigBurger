package fr.greta.java.livreur.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commande.facade.CommandeDTOWrapper;
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

@WebServlet("/focusGestionLivreur")
public class FocusGestionLivreur extends HttpServlet {

    private LivreurService livreurService = new LivreurService();
    private CommandeService commandeService = new CommandeService();
    private LivreurDTOWrapper livreurDTOWrapper = new LivreurDTOWrapper();
    private CommandeDTOWrapper commandeDTOWrapper = new CommandeDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Livreur> allLivreurs = livreurService.findAllLivreurs();

            request.setAttribute("livreurs", livreurDTOWrapper.toDTOS(allLivreurs));

            List<Commande> allCommandes = commandeService.findAllCommandesPretePourLivraison();
            request.setAttribute("commandes", commandeDTOWrapper.toDTOS(allCommandes));

            request.getRequestDispatcher("focusLivreur.jsp").forward(request, response);
        } catch (ServiceException | RepositoryException e) {
            e.printStackTrace();
        }
    }


}
