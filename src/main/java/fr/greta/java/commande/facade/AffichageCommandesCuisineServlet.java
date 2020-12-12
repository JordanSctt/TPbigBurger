package fr.greta.java.commande.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commandeItems.domain.CommandeItemsService;
import fr.greta.java.commandeItems.facade.CommandeItemsDTOWrapper;
import fr.greta.java.commandeItems.persistence.CommandeItemsRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/affichageCommande")
public class AffichageCommandesCuisineServlet extends HttpServlet {

    private CommandeService service = new CommandeService();
    private CommandeDTOWrapper wrapper = new CommandeDTOWrapper();
    private CommandeItemsRepository repository = new CommandeItemsRepository();
    private CommandeItemsService itemsService = new CommandeItemsService();
    private CommandeItemsDTOWrapper itemsWrapper = new CommandeItemsDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Commande> allCommandes = service.findAllCommandes();
            request.setAttribute("commandes", wrapper.toDTOS(allCommandes));

            request.getRequestDispatcher("cuisine.jsp").forward(request, response);
        } catch (ServiceException | RepositoryException e) {
            e.printStackTrace();
        }
    }
}
