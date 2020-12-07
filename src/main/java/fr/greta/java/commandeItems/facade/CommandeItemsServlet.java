package fr.greta.java.commandeItems.facade;

import fr.greta.java.burger.domain.Burger;
import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeEtat;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commandeItems.domain.CommandeItems;
import fr.greta.java.commandeItems.domain.CommandeItemsService;
import fr.greta.java.commandeItems.persistence.CommandeItemsEntity;
import fr.greta.java.commandeItems.persistence.CommandeItemsRepository;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.User;
import fr.greta.java.user.domain.UserWrapper;
import fr.greta.java.user.facade.UserDTO;
import fr.greta.java.user.facade.UserDTOWrapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/actionCommanderUser")
public class CommandeItemsServlet extends HttpServlet {

    //System.out.println(key + " : " + map.get(key)[0] );

    /*   //  LocalDateTime to Timestamp
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);

        System.out.println(now);            // 2019-06-14T15:50:36.068076300
        System.out.println(timestamp);      // 2019-06-14 15:50:36.0680763

        //  Timestamp to LocalDateTime
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        System.out.println(localDateTime);  // 2019-06-14T15:50:36.068076300
*/


    private CommandeService serviceCommande = new CommandeService();
    private CommandeItemsService serviceCommandeItems = new CommandeItemsService();
    private UserDTOWrapper wrapper = new UserDTOWrapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDateTime dateDebutCommande = LocalDateTime.now();
        Commande commande = new Commande();
        HttpSession session = request.getSession();
        UserDTO userEnCours = (UserDTO) session.getAttribute("userConnected");

        commande.setUser(wrapper.fromDTO(userEnCours));
        commande.setStartDatePrep(dateDebutCommande);
        serviceCommande.calculDateFin(commande);
        commande.setEtatCommande(CommandeEtat.EN_COURS_DE_PREPARATION);

        try {
            Commande commandeCreer;
            commandeCreer = serviceCommande.create(commande);

            commande.setId(commandeCreer.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        Map<String, String[]> map = request.getParameterMap();

        for (String key : map.keySet()) {
            if (!(map.get(key)[0]== null || map.get(key)[0].isEmpty())) {
            CommandeItems commandeItems = new CommandeItems();
            Burger burger = new Burger();
            burger.setId(Integer.parseInt(key));
            commandeItems.setBurger(burger);

            commandeItems.setQuantity(Integer.parseInt(map.get(key)[0]));
            commandeItems.setCommande(commande);

            try {
                serviceCommandeItems.create(commandeItems);
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        }

            session.setAttribute("commande", commande);
            request.getRequestDispatcher("recapCommande").forward(request, response);

        }
    }




// ----------
    /*   // commandeItemsDTO.setBurgerID(Integer.parseInt(key));

            // faire requette SQL pour rentrer l'id cle etrangere du burger, et la quantite + user name dans commande
*/
// key = burgerID , [0] = quantite    System.out.println(key + " : " + map.get(key)[0] );