package fr.greta.java.commande.facade;


import fr.greta.java.burger.domain.Produit;
import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeEtat;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.commande.domain.CommandeTypeLivraison;
import fr.greta.java.commandeItems.domain.CommandeItems;
import fr.greta.java.commandeItems.domain.CommandeItemsService;
import fr.greta.java.generic.exception.RepositoryException;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.User;
import fr.greta.java.user.facade.UserDTOWrapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@WebServlet("/actionCommanderUserLivraison")
public class CommandeLivraisonServlet extends HttpServlet {

    private CommandeService serviceCommande = new CommandeService();
    private CommandeItemsService serviceCommandeItems = new CommandeItemsService();
    private UserDTOWrapper wrapper = new UserDTOWrapper();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LocalDateTime dateDebutCommande = LocalDateTime.now();

        Commande commande = new Commande();
        HttpSession session = request.getSession();
        User userEnCours = (User) session.getAttribute("userConnected");


        commande.setUser(userEnCours);
        commande.setStartDatePrep(dateDebutCommande);
        commande.setTypeLivraison(CommandeTypeLivraison.LIVRAISON);
        commande.setEtatCommande(CommandeEtat.EN_COURS_DE_TRAITEMENT);

        try {
            serviceCommande.calculDateFinEmporter(commande);
            serviceCommande.calculDateEstimationLivraison(commande);
            Commande commandeCreer;
            commandeCreer = serviceCommande.createLivraison(commande);
            commande.setId(commandeCreer.getId());
        } catch (RepositoryException | ServiceException e) {
            e.printStackTrace();
        }

        Map<String, String[]> map = request.getParameterMap();

        for (String key : map.keySet()) {

            if (!(map.get(key)[0]== null || map.get(key)[0].isEmpty()) ) {
                CommandeItems commandeItems = new CommandeItems();
                Produit produit = new Produit();
                produit.setId(Integer.parseInt(key));
                commandeItems.setProduit(produit);
                commandeItems.setQuantity(Integer.parseInt(map.get(key)[0]));
                commandeItems.setCommande(commande);

                try {
                    serviceCommandeItems.create(commandeItems);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }

            }

        }

        response.sendRedirect(request.getContextPath() + "/recapCommande");
           /*  ancienne servlet
           session.setAttribute("commande", commande);

         request.getRequestDispatcher("recapCommande").forward(request, response); */

    }
}
