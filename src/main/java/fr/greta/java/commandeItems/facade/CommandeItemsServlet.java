package fr.greta.java.commandeItems.facade;

import fr.greta.java.burger.domain.Burger;
import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commandeItems.domain.CommandeItems;
import fr.greta.java.commandeItems.domain.CommandeItemsService;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.User;
import fr.greta.java.user.domain.UserWrapper;
import fr.greta.java.user.facade.UserDTO;
import fr.greta.java.user.facade.UserDTOWrapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/actionCommanderUser")
public class CommandeItemsServlet extends HttpServlet {

    //System.out.println(key + " : " + map.get(key)[0] );



    private CommandeService serviceCommande = new CommandeService();
    private CommandeItemsService serviceCommandeItems = new CommandeItemsService();
    private UserDTOWrapper wrapper = new UserDTOWrapper();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Commande commande = new Commande();
        HttpSession session = request.getSession();
        UserDTO userEnCours = (UserDTO) session.getAttribute("userConnected");

        commande.setUser(wrapper.fromDTO(userEnCours));
        Commande commandeCreer;
        try {
       commandeCreer = serviceCommande.create(commande);

        commande.setId(commandeCreer.getId());
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        Map<String, String[]> map = request.getParameterMap();

        for (String key : map.keySet()) {

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


        //User user = (User)session.getAttribute("userConnected");


    }

}



// ----------
    /*   // commandeItemsDTO.setBurgerID(Integer.parseInt(key));

            // faire requette SQL pour rentrer l'id cle etrangere du burger, et la quantite + user name dans commande
*/
// key = burgerID , [0] = quantite    System.out.println(key + " : " + map.get(key)[0] );