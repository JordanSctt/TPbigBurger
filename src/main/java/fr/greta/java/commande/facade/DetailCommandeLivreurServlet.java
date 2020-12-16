package fr.greta.java.commande.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeWrapper;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.commandeItems.domain.CommandeItemsService;
import fr.greta.java.commandeItems.facade.CommandeItemsDTOWrapper;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DetailCommandeLivreur")
public class DetailCommandeLivreurServlet extends HttpServlet {


    CommandeRepository repository = new CommandeRepository();
    CommandeDTOWrapper commandeDTOwrapper = new CommandeDTOWrapper();
    UserService userService = new UserService();
    CommandeWrapper commandeWrapper = new CommandeWrapper();
    CommandeItemsService commandeItemsService = new CommandeItemsService();
    CommandeItemsDTOWrapper commandeItemsDTOWrapper = new CommandeItemsDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int commandeId = Integer.parseInt(request.getParameter("commande_id"));
        try {
            Commande commande = commandeWrapper.fromEntity(repository.findById(commandeId));
            commande.setUser(userService.findById(commande.getUser().getId()));
            CommandeDTO commandeDTO = commandeDTOwrapper.toDTO(commande);
            commandeDTO.setCommandeItemsDTOList(commandeItemsDTOWrapper.toListDTOByModel(commandeItemsService.findAllCommandeItemsByCommandeID(commandeDTO.getId())));

            request.setAttribute("commande", commandeDTO);

            // response.sendRedirect(request.getContextPath() + "/detailCommandeCuisine.jsp");
            request.getRequestDispatcher("/detailCommandeLivreur.jsp").forward(request, response);
        } catch (RepositoryException | ServiceException e) {
            e.printStackTrace();
        }

    }
}
