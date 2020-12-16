package fr.greta.java.commande.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.commandeItems.domain.CommandeItemsService;
import fr.greta.java.commandeItems.facade.CommandeItemsDTOWrapper;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/historyCommande")
public class HistoryCommande extends HttpServlet {

    CommandeRepository repository = new CommandeRepository();
    CommandeDTOWrapper wrapperDTO = new CommandeDTOWrapper();
    CommandeItemsService commandeItemsService = new CommandeItemsService();
    CommandeItemsDTOWrapper commandeItemsDTOWrapper = new CommandeItemsDTOWrapper();
    CommandeService commandeService = new CommandeService();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User userEnCours = (User) session.getAttribute("userConnected");


        try {
            List<Commande> commandes = commandeService.findAllCommandesByUserID(userEnCours.getId());
            List<CommandeDTO> commandeDTOList = wrapperDTO.toDTOS(commandes);

            for (CommandeDTO commandeDTO : commandeDTOList) {
                commandeDTO.setCommandeItemsDTOList(commandeItemsDTOWrapper.toListDTOByModel(commandeItemsService.findAllCommandeItemsByCommandeID(commandeDTO.getId())));

            }

            request.setAttribute("commande", commandeDTOList);
            request.getRequestDispatcher("historyCommande.jsp").forward(request, response);

        } catch (RepositoryException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }
}
