package fr.greta.java.commande.facade;

import fr.greta.java.commande.domain.Commande;
import fr.greta.java.commande.domain.CommandeService;
import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.user.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/affichageCommande/affect")
public class AffectedCommandeServlet extends HttpServlet {

    CommandeService service = new CommandeService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String commandeId = request.getParameter("commande_id");

        HttpSession session = request.getSession();
        User userEnCours = (User)session.getAttribute("userConnected");

        request.setAttribute("commande_id", commandeId);
        request.setAttribute("userRole", userEnCours.getRole());
        try {
            Commande commande = new Commande();
            commande.setId(Integer.parseInt(commandeId));
            service.updateEtatCommande(userEnCours, commande);

            response.sendRedirect(request.getContextPath() + "/affichageCommande");
        } catch (ServiceException e) {
            e.printStackTrace();
        }


    }
}
