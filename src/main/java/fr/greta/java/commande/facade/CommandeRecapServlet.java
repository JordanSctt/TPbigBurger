package fr.greta.java.commande.facade;

//import com.sun.java.swing.ui.CommonMenuBar;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commande.persistence.CommandeRepository;
import fr.greta.java.commandeItems.domain.CommandeItemsService;
import fr.greta.java.commandeItems.domain.CommandeItemsWrapper;
import fr.greta.java.commandeItems.facade.CommandeItemsDTO;
import fr.greta.java.commandeItems.facade.CommandeItemsDTOWrapper;
import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;
import fr.greta.java.commandeItems.persistence.CommandeItemsRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.user.facade.UserDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/recapCommande")
public class CommandeRecapServlet extends HttpServlet {

   /* ancienne servlet

    private CommandeItemsDTOWrapper wrapperDTO = new CommandeItemsDTOWrapper();
    private CommandeDTOWrapper wrapper = new CommandeDTOWrapper();
    private CommandeItemsRepository repository = new CommandeItemsRepository();
    */

    CommandeRepository repository = new CommandeRepository();
    CommandeDTOWrapper wrapperDTO = new CommandeDTOWrapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        UserDTO userEnCours = (UserDTO) session.getAttribute("userConnected");


        try {
            CommandeDTO commandeDTO = wrapperDTO.toDTOByEntity(repository.findLastCommandeByUserID(userEnCours.getId()));
            request.setAttribute("commande", commandeDTO);
        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("recapCommande.jsp").forward(request, response);

    }
}

      /*  Ancienne servlet


      Double prixTotal = 0.0;
        HttpSession session = request.getSession();

        CommandeDTO commandeDTO = null;
        try {
            commandeDTO = wrapper.toDTO ( (Commande) session.getAttribute("commande"));
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        commandeDTO.setHeureRecup(commandeDTO.getEndDatePrep());

        try {

            List<CommandeItemsEntity> commandeItemsEntityList = repository.findAllCommandeItemsByCommandeID(commandeDTO.getId());
            List <CommandeItemsDTO> listCommandeDTO = wrapperDTO.toListDTO(commandeItemsEntityList);
            request.setAttribute("commandeItemsDTO", listCommandeDTO);
            for (CommandeItemsDTO commandeItemsDTO : listCommandeDTO) {

                prixTotal += commandeItemsDTO.getTotalPrixLigne();

            }

        } catch (RepositoryException e) {
            e.printStackTrace();
        }

        commandeDTO.setPrixTotal(prixTotal);


        session.setAttribute("commande",commandeDTO );*/


        //request.getRequestDispatcher("recapCommande.jsp").forward(request, response);

