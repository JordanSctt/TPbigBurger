package fr.greta.java.commandeItems.facade;

import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;
import fr.greta.java.commandeItems.persistence.CommandeItemsRepository;
import fr.greta.java.generic.exception.RepositoryException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet("/affichageCommande/detail2")
public class AffichageDetailServlet extends HttpServlet {

    private CommandeItemsRepository repository = new CommandeItemsRepository();
    private CommandeItemsDTOWrapper wrapper = new CommandeItemsDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String commandeId = request.getParameter("commande_id");

            List<CommandeItemsEntity> allDetail = repository.findAllCommandeItemsByCommandeID(Integer.parseInt(commandeId));
            request.setAttribute("commandeItems", wrapper.toListDTO(allDetail));
            request.setAttribute("commande_id", commandeId);

            request.getRequestDispatcher("/cuisineDetail.jsp").forward(request, response);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
    }

}
