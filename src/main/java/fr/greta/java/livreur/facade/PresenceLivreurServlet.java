package fr.greta.java.livreur.facade;


import fr.greta.java.generic.exception.ServiceException;
import fr.greta.java.livreur.domain.Livreur;
import fr.greta.java.livreur.domain.LivreurPresence;
import fr.greta.java.livreur.domain.LivreurService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/presenceLivreur")
public class PresenceLivreurServlet extends HttpServlet {

    private static final String LIVREUR_ID = "livreurId";
    private static final String PRESENCE = "presence_parameter";

    private static LivreurService service = new LivreurService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String livreurId = request.getParameter("livreur_id");
        request.setAttribute("livreur_id", livreurId);

        try {
            Livreur livreur = new Livreur();
            livreur.setId(Integer.parseInt(request.getParameter(LIVREUR_ID)));
            livreur.setPresence(LivreurPresence.valueOf(request.getParameter(PRESENCE)));
            service.updatePresence(livreur);

            response.sendRedirect(request.getContextPath() + "/gestionLivreur");
        } catch (ServiceException e) {
            e.printStackTrace();
        }

    }


}
