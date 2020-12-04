package fr.greta.java.commandeItems.facade;


import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commande.facade.CommandeDTOWrapper;
import fr.greta.java.commandeItems.domain.CommandeItems;
import fr.greta.java.commandeItems.domain.CommandeItemsService;
import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


// SERVLET NON UTILISER POUR LE MOMENT ---------------------

@WebServlet("/affichageCommande/detail")
public class AffichageDetailServlet extends HttpServlet {

    private CommandeItemsService service = new CommandeItemsService();
    private CommandeItemsDTOWrapper wrapper = new CommandeItemsDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            String commandeId = request.getParameter("commande_id");

            /*List<CommandeItems> allDetail =*/ service.findById(Integer.parseInt(commandeId));
            //request.setAttribute("commandeItems", wrapper.toListDTOByModel(allDetail));

            request.getRequestDispatcher("/cuisineDetail.jsp").forward(request, response);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

}
